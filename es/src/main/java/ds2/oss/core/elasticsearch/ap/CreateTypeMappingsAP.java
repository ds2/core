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

import ds2.oss.core.elasticsearch.api.TypeMapping;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * The type mapping annotation processor.
 * @author dstrauss
 */
@SupportedAnnotationTypes(value = "ds2.oss.core.elasticsearch.api.TypeMapping")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CreateTypeMappingsAP extends AbstractProcessor{
	/**
	 * Inits the processor.
	 */
    public CreateTypeMappingsAP(){
        super();
    }
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        Messager LOG=processingEnv.getMessager();
        LOG.printMessage(Diagnostic.Kind.NOTE, "Starting ...");
        for (Element elem : roundEnv.getElementsAnnotatedWith(TypeMapping.class)) {
            TypeMapping tm = elem.getAnnotation(TypeMapping.class);
            LOG.printMessage(Diagnostic.Kind.NOTE, "Element "+elem.getSimpleName()+" has type mappings: "+tm);
        }
        LOG.printMessage(Diagnostic.Kind.WARNING, "Done :D");
        return true;
    }
}
