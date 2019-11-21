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
package study.daydayup.wolf.common.generator.mybatis.plugins;

import study.daydayup.wolf.common.generator.mybatis.api.IntrospectedTable;
import study.daydayup.wolf.common.generator.mybatis.api.IntrospectedTable.TargetRuntime;
import study.daydayup.wolf.common.generator.mybatis.api.PluginAdapter;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.FullyQualifiedJavaType;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Interface;

import java.util.List;

public class MapperAnnotationPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {

        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
            // don't need to do this for MYBATIS3_DSQL as that runtime already adds this annotation 
            interfaze.addImportedType(
                    new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper")); //$NON-NLS-1$
            interfaze.addAnnotation("@Mapper"); //$NON-NLS-1$
        }
        return true;
    }
}
