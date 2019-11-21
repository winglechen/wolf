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
package study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.javamapper;

import study.daydayup.wolf.common.generator.mybatis.api.CommentGenerator;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.CompilationUnit;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.FullyQualifiedJavaType;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.JavaVisibility;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.TopLevelClass;
import study.daydayup.wolf.common.generator.mybatis.codegen.AbstractJavaGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3.javamapper.elements.sqlprovider.*;

import java.util.ArrayList;
import java.util.List;

import static study.daydayup.wolf.common.generator.mybatis.internal.util.messages.Messages.getString;

public class SqlProviderGenerator extends AbstractJavaGenerator {

    private boolean useLegacyBuilder;

    public SqlProviderGenerator(String project, boolean useLegacyBuilder) {
        super(project);
        this.useLegacyBuilder = useLegacyBuilder;
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.18", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3SqlProviderType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        boolean addApplyWhereMethod = false;
        addApplyWhereMethod |= addCountByExampleMethod(topLevelClass);
        addApplyWhereMethod |= addDeleteByExampleMethod(topLevelClass);
        addInsertSelectiveMethod(topLevelClass);
        addApplyWhereMethod |= addSelectByExampleWithBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addSelectByExampleWithoutBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByExampleSelectiveMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByExampleWithBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByExampleWithoutBLOBsMethod(topLevelClass);
        addUpdateByPrimaryKeySelectiveMethod(topLevelClass);

        if (addApplyWhereMethod) {
            addApplyWhereMethod(topLevelClass);
        }

        List<CompilationUnit> answer = new ArrayList<>();
        
        if (!topLevelClass.getMethods().isEmpty()
                && context.getPlugins().providerGenerated(topLevelClass, introspectedTable)) {
            answer.add(topLevelClass);
        }

        return answer;
    }

    protected boolean addCountByExampleMethod(TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateCountByExample()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderCountByExampleMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addDeleteByExampleMethod(TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateDeleteByExample()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderDeleteByExampleMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderInsertSelectiveMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
        }
    }

    protected boolean addSelectByExampleWithBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderSelectByExampleWithBLOBsMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addSelectByExampleWithoutBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderSelectByExampleWithoutBLOBsMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByExampleSelectiveMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByExampleSelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderUpdateByExampleSelectiveMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByExampleWithBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderUpdateByExampleWithBLOBsMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByExampleWithoutBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderUpdateByExampleWithoutBLOBsMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(
            TopLevelClass topLevelClass) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator =
                    new ProviderUpdateByPrimaryKeySelectiveMethodGenerator(useLegacyBuilder);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
        }
    }

    protected void addApplyWhereMethod(TopLevelClass topLevelClass) {
        AbstractJavaProviderMethodGenerator methodGenerator = new ProviderApplyWhereMethodGenerator(useLegacyBuilder);
        initializeAndExecuteGenerator(methodGenerator, topLevelClass);
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaProviderMethodGenerator methodGenerator,
            TopLevelClass topLevelClass) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addClassElements(topLevelClass);
    }
}
