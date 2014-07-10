/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.elasticsearch.ap;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import ds2.oss.core.elasticsearch.api.FieldTypes;
import ds2.oss.core.elasticsearch.api.annotations.PropertyMapping;
import ds2.oss.core.elasticsearch.api.annotations.TypeMapping;

/**
 * The type mapping annotation processor.
 * 
 * @author dstrauss
 * @version 0.2
 */
@SupportedAnnotationTypes(value = "ds2.oss.core.elasticsearch.api.annotations.TypeMapping")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CreateTypeMappingsAP extends AbstractProcessor {
    /**
     * Inits the processor.
     */
    public CreateTypeMappingsAP() {
        super();
    }
    
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        final Messager log = processingEnv.getMessager();
        final Filer filer = processingEnv.getFiler();
        log.printMessage(Diagnostic.Kind.NOTE, "Starting ...");
        for (Element elem : roundEnv.getElementsAnnotatedWith(TypeMapping.class)) {
            final TypeMapping tm = elem.getAnnotation(TypeMapping.class);
            log.printMessage(Diagnostic.Kind.NOTE, "Element " + elem.getSimpleName() + " has type mappings: " + tm);
            scanType(log, elem, filer);
        }
        log.printMessage(Diagnostic.Kind.WARNING, "Done :D");
        return true;
    }
    
    /**
     * Performs a scan of the given type.
     * 
     * @param log
     *            the logger
     * @param elem
     *            the element to scan
     * @param filer
     *            a filer to create some mapping files
     */
    private static void scanType(final Messager log, final Element elem, final Filer filer) {
        final TypeMapping tm = elem.getAnnotation(TypeMapping.class);
        final TypeElement te = (TypeElement) elem;
        final PackageElement pe = (PackageElement) te.getEnclosingElement();
        final String pkg = pe.getQualifiedName().toString();
        try {
            final FileObject res =
                filer.createResource(StandardLocation.SOURCE_OUTPUT, pkg, elem.getSimpleName()
                    + "-elasticsearch.mapping.json", elem);
            res.delete();
            try (Writer writer = res.openWriter()) {
                final StringBuilder sb = new StringBuilder();
                final JsonObject mainJs = new JsonObject();
                final JsonObject typeJs = new JsonObject();
                // the type name structure
                mainJs.add(tm.value(), typeJs);
                scanType(typeJs, tm);
                final JsonObject properties = scanProperties(log, te.getEnclosedElements());
                if (properties != null) {
                    typeJs.add("properties", properties);
                }
                sb.append(mainJs.toString());
                writer.write(sb.toString());
            }
        } catch (final IOException e) {
            log.printMessage(Diagnostic.Kind.ERROR, "Error when writing the mapping file!", elem);
        }
    }
    
    /**
     * Adds some header data.
     * 
     * @param typeJs
     *            the json object to write into
     * @param tm
     *            the type mapping data
     */
    private static void scanType(final JsonObject typeJs, final TypeMapping tm) {
        final JsonObject sourceEnabled = new JsonObject();
        sourceEnabled.addProperty("enabled", Boolean.valueOf(tm.storeSource()));
        typeJs.add("_source", sourceEnabled);
        
        if (tm.ttl().length() > 0) {
            final JsonObject ttlEnabled = new JsonObject();
            ttlEnabled.addProperty("enabled", Boolean.TRUE);
            ttlEnabled.addProperty("default", tm.ttl());
            typeJs.add("_ttl", ttlEnabled);
        }
        if (tm.parentType().length() > 0) {
            final JsonObject parent = new JsonObject();
            parent.addProperty("type", tm.parentType());
            typeJs.add("_parent", parent);
        }
    }
    
    /**
     * Scans for properties.
     * 
     * @param log
     *            a logger
     * @param list
     *            the list of child elements to scan
     * @return a JsonObject having the properties, or null if not
     */
    private static JsonObject scanProperties(final Messager log, final List<? extends Element> list) {
        final JsonObject rc = new JsonObject();
        for (Element el : list) {
            if (!ElementKind.FIELD.equals(el.getKind())) {
                continue;
            }
            log.printMessage(Diagnostic.Kind.NOTE, "Element is " + el.getKind() + " or " + el);
            final PropertyMapping pm = el.getAnnotation(PropertyMapping.class);
            if (pm == null) {
                continue;
            }
            final String fieldName = el.getSimpleName().toString();
            final JsonObject fieldObj = scanField(el);
            if (fieldObj == null) {
                continue;
            }
            rc.add(fieldName, fieldObj);
        }
        return rc;
    }
    
    /**
     * Scans a field, returns any known field data.
     * 
     * @param fieldEl
     *            the field to scan
     * @return the JsonObject that contains any field data, or null if not
     */
    private static JsonObject scanField(final Element fieldEl) {
        final PropertyMapping pm = fieldEl.getAnnotation(PropertyMapping.class);
        if (pm == null) {
            // no specific data, ignore it
            return null;
        }
        final JsonObject rc = new JsonObject();
        rc.addProperty("type", pm.type().getTypeName());
        rc.addProperty("index", pm.index().getTypeName());
        if (pm.indexName().length() > 0) {
            rc.addProperty("index_name", pm.indexName());
        }
        rc.addProperty("store", Boolean.valueOf(pm.store()));
        if (!pm.dateFormat().equalsIgnoreCase(PropertyMapping.NULL)) {
            rc.addProperty("format", pm.dateFormat());
        }
        if (!pm.onNull().equalsIgnoreCase(PropertyMapping.NULL)) {
            rc.add("null_value", formatNullValue(pm.type(), pm.onNull()));
        }
        if (PropertyMapping.DEF_BOOST != pm.boost()) {
            rc.addProperty("boost", Float.valueOf(pm.boost()));
        }
        return rc;
    }
    
    /**
     * Formats a given element.
     * 
     * @param index
     *            the field type
     * @param onNull
     *            the null_value value
     * @return the json element best matching the given field type
     */
    private static JsonElement formatNullValue(final FieldTypes index, final String onNull) {
        JsonElement rc = new JsonPrimitive(onNull);
        switch (index) {
            case BOOLEAN:
                rc = new JsonPrimitive(Boolean.valueOf(onNull));
                break;
            case DOUBLE:
            case FLOAT:
                rc = new JsonPrimitive(Double.valueOf(onNull));
                break;
            case INTEGER:
            case LONG:
                rc = new JsonPrimitive(Long.valueOf(onNull));
                break;
            case DATE:
            case STRING:
                rc = new JsonPrimitive(onNull);
                break;
            default:
                break;
        }
        return rc;
    }
}
