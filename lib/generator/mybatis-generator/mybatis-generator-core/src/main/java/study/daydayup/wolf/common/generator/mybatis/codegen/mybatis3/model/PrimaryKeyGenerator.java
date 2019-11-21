/**
 *    Copyright 2006-2019 the original author or authors.
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
package study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.model;

import study.daydayup.wolf.common.generator.mybatis.api.CommentGenerator;
import study.daydayup.wolf.common.generator.mybatis.api.FullyQualifiedTable;
import study.daydayup.wolf.common.generator.mybatis.api.IntrospectedColumn;
import study.daydayup.wolf.common.generator.mybatis.api.Plugin;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.*;
import study.daydayup.wolf.common.generator.mybatis.codegen.AbstractJavaGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.RootClassInfo;

import java.util.ArrayList;
import java.util.List;

import static study.daydayup.wolf.common.generator.mybatis.internal.util.JavaBeansUtil.*;
import static study.daydayup.wolf.common.generator.mybatis.internal.util.messages.Messages.getString;

public class PrimaryKeyGenerator extends AbstractJavaGenerator {

    public PrimaryKeyGenerator(String project) {
        super(project);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.7", table.toString())); //$NON-NLS-1$
        Plugin plugins = context.getPlugins();
        CommentGenerator commentGenerator = context.getCommentGenerator();

        TopLevelClass topLevelClass = new TopLevelClass(introspectedTable
                .getPrimaryKeyType());
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        String rootClass = getRootClass();
        if (rootClass != null) {
            FullyQualifiedJavaType rootType = new FullyQualifiedJavaType(rootClass);
            topLevelClass.setSuperClass(rootType);
            topLevelClass.addImportedType(rootType);
        }

        if (introspectedTable.isConstructorBased()) {
            addParameterizedConstructor(topLevelClass);

            if (!introspectedTable.isImmutable()) {
                addDefaultConstructor(topLevelClass);
            }
        }

        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            if (RootClassInfo.getInstance(rootClass, warnings)
                    .containsProperty(introspectedColumn)) {
                continue;
            }

            Field field = getJavaBeansField(introspectedColumn, context, introspectedTable);
            if (plugins.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.PRIMARY_KEY)) {
                topLevelClass.addField(field);
                topLevelClass.addImportedType(field.getType());
            }

            Method method = getJavaBeansGetter(introspectedColumn, context, introspectedTable);
            if (plugins.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.PRIMARY_KEY)) {
                topLevelClass.addMethod(method);
            }

            if (!introspectedTable.isImmutable()) {
                method = getJavaBeansSetter(introspectedColumn, context, introspectedTable);
                if (plugins.modelSetterMethodGenerated(method, topLevelClass,
                        introspectedColumn, introspectedTable,
                        Plugin.ModelClassType.PRIMARY_KEY)) {
                    topLevelClass.addMethod(method);
                }
            }
        }

        List<CompilationUnit> answer = new ArrayList<>();
        if (context.getPlugins().modelPrimaryKeyClassGenerated(
                topLevelClass, introspectedTable)) {
            answer.add(topLevelClass);
        }
        return answer;
    }

    private void addParameterizedConstructor(TopLevelClass topLevelClass) {
        Method method = new Method(topLevelClass.getType().getShortName());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(),
                    introspectedColumn.getJavaProperty()));
            sb.setLength(0);
            sb.append("this."); //$NON-NLS-1$
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" = "); //$NON-NLS-1$
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        topLevelClass.addMethod(method);
    }
}
