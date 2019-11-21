/**
 *    Copyright 2006-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3;

import study.daydayup.wolf.common.generator.mybatis.api.ProgressCallback;
import study.daydayup.wolf.common.generator.mybatis.codegen.AbstractJavaClientGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.AbstractJavaGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.javamapper.SimpleAnnotatedClientGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.javamapper.SimpleJavaClientGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.model.SimpleModelGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;
import study.daydayup.wolf.common.generator.mybatis.internal.ObjectFactory;

import java.util.List;

/**
 * Introspected table implementation for generating simple MyBatis3 artifacts (no "by example" methods,
 * flat model, etc.)
 * 
 * @author Jeff Butler
 * 
 */
public class IntrospectedTableMyBatis3SimpleImpl extends IntrospectedTableMyBatis3Impl {
    public IntrospectedTableMyBatis3SimpleImpl() {
        super();
    }

    @Override
    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, 
            List<String> warnings,
            ProgressCallback progressCallback) {
        if (javaClientGenerator == null) {
            if (context.getSqlMapGeneratorConfiguration() != null) {
                xmlMapperGenerator = new SimpleXMLMapperGenerator();
            }
        } else {
            xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
        }
        
        initializeAbstractGenerator(xmlMapperGenerator, warnings,
                progressCallback);
    }

    @Override
    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }
        
        String type = context.getJavaClientGeneratorConfiguration()
                .getConfigurationType();

        AbstractJavaClientGenerator javaGenerator;
        if ("XMLMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new SimpleJavaClientGenerator(getClientProject());
        } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new SimpleAnnotatedClientGenerator(getClientProject());
        } else if ("MAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new SimpleJavaClientGenerator(getClientProject());
        } else {
            javaGenerator = (AbstractJavaClientGenerator) ObjectFactory
                    .createInternalObject(type);
        }

        return javaGenerator;
    }

    @Override
    protected void calculateJavaModelGenerators(List<String> warnings,
            ProgressCallback progressCallback) {

        AbstractJavaGenerator javaGenerator = new SimpleModelGenerator(getModelProject());
        initializeAbstractGenerator(javaGenerator, warnings,
                progressCallback);
        javaGenerators.add(javaGenerator);
    }
}
