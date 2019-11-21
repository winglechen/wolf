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
package study.daydayup.wolf.common.generator.mybatis.plugins.dsql;

import study.daydayup.wolf.common.generator.mybatis.api.IntrospectedTable;
import study.daydayup.wolf.common.generator.mybatis.api.PluginAdapter;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Interface;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Method;

import java.util.List;

/**
 * Disables all insert methods in the MyBatisDynamicSQLV2 runtime.
 * 
 * @author Jeff Butler
 *
 */
public class DisableInsertPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientBasicInsertMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientBasicInsertMultipleMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientBasicInsertMultipleHelperMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMultipleMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        return false;
    }
}
