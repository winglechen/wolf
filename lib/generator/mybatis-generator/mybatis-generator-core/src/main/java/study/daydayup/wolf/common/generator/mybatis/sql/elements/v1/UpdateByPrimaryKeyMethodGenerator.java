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
package study.daydayup.wolf.common.generator.mybatis.sql.elements.v1;

import study.daydayup.wolf.common.generator.mybatis.api.dom.java.FullyQualifiedJavaType;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Interface;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Method;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Parameter;
import study.daydayup.wolf.common.generator.mybatis.runtime.dynamic.sql.elements.AbstractMethodGenerator;
import study.daydayup.wolf.common.generator.mybatis.runtime.dynamic.sql.elements.FragmentGenerator;
import study.daydayup.wolf.common.generator.mybatis.runtime.dynamic.sql.elements.MethodAndImports;

import java.util.HashSet;
import java.util.Set;

public class UpdateByPrimaryKeyMethodGenerator extends AbstractMethodGenerator {
    private FullyQualifiedJavaType recordType;
    private FragmentGenerator fragmentGenerator;
    
    private UpdateByPrimaryKeyMethodGenerator(Builder builder) {
        super(builder);
        recordType = builder.recordType;
        fragmentGenerator = builder.fragmentGenerator;
    }

    @Override
    public MethodAndImports generateMethodAndImports() {
        if (!introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()
                && !introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
            return null;
        }

        Set<FullyQualifiedJavaType> imports = new HashSet<>();

        imports.add(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.update.UpdateDSL")); //$NON-NLS-1$
        imports.add(recordType);
        
        Method method = new Method("updateByPrimaryKey"); //$NON-NLS-1$
        method.setDefault(true);
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, imports);
        
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.addParameter(new Parameter(recordType, "record")); //$NON-NLS-1$

        method.addBodyLine("return UpdateDSL.updateWithMapper(this::update, " //$NON-NLS-1$
                + tableFieldName + ")"); //$NON-NLS-1$

        method.addBodyLines(fragmentGenerator.getSetEqualLines(introspectedTable.getNonPrimaryKeyColumns(), false));
        method.addBodyLines(fragmentGenerator.getPrimaryKeyWhereClauseForUpdate("        ")); //$NON-NLS-1$
        method.addBodyLine("        .build()"); //$NON-NLS-1$
        method.addBodyLine("        .execute();"); //$NON-NLS-1$

        return MethodAndImports.withMethod(method)
                .withImports(imports)
                .build();
    }

    @Override
    public boolean callPlugins(Method method, Interface interfaze) {
        return context.getPlugins().clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method,
                interfaze, introspectedTable);
    }

    public static class Builder extends BaseBuilder<Builder, UpdateByPrimaryKeyMethodGenerator> {
        private FullyQualifiedJavaType recordType;
        private FragmentGenerator fragmentGenerator;
        
        public Builder withRecordType(FullyQualifiedJavaType recordType) {
            this.recordType = recordType;
            return this;
        }
        
        public Builder withFragmentGenerator(FragmentGenerator fragmentGenerator) {
            this.fragmentGenerator = fragmentGenerator;
            return this;
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public UpdateByPrimaryKeyMethodGenerator build() {
            return new UpdateByPrimaryKeyMethodGenerator(this);
        }
    }
}
