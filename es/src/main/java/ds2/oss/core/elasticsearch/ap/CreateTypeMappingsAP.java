/*
 * Copyright 2012-2013 Dirk Strauss
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
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.google.gson.Gson;

import ds2.oss.core.elasticsearch.api.TypeMapping;

/**
 * The type mapping annotation processor.
 * 
 * @author dstrauss
 * @version 0.2
 */
@SupportedAnnotationTypes(value = "ds2.oss.core.elasticsearch.api.TypeMapping")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CreateTypeMappingsAP extends AbstractProcessor {
    /**
     * Inits the processor.
     */
    public CreateTypeMappingsAP() {
        super();
    }
    
    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
        final RoundEnvironment roundEnv) {
        final Messager log = processingEnv.getMessager();
        Filer filer = processingEnv.getFiler();
        log.printMessage(Diagnostic.Kind.NOTE, "Starting ...");
        for (Element elem : roundEnv
            .getElementsAnnotatedWith(TypeMapping.class)) {
            final TypeMapping tm = elem.getAnnotation(TypeMapping.class);
            log.printMessage(Diagnostic.Kind.NOTE,
                "Element " + elem.getSimpleName() + " has type mappings: " + tm);
            scanType(elem, filer);
        }
        log.printMessage(Diagnostic.Kind.WARNING, "Done :D");
        return true;
    }
    
    private void scanType(final Element elem, final Filer filer) {
        final TypeMapping tm = elem.getAnnotation(TypeMapping.class);
        final Gson gson = new Gson();
        TypeElement te = (TypeElement) elem;
        PackageElement pe = (PackageElement) te.getEnclosingElement();
        final String pkg = pe.getQualifiedName().toString();
        try {
            final FileObject res =
                filer.createResource(StandardLocation.SOURCE_OUTPUT, pkg,
                    elem.getSimpleName() + "-elasticsearch.mapping.json", elem);
            res.delete();
            final Writer writer = res.openWriter();
            writer.write("hello");
            writer.flush();
            writer.close();
        } catch (final IOException e) {
        }
    }
}
