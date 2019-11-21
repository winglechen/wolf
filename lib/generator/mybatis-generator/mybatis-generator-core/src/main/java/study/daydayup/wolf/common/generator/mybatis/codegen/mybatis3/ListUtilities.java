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
package study.daydayup.wolf.common.generator.mybatis.codegen.mybatis3;

import study.daydayup.wolf.common.generator.mybatis.api.IntrospectedColumn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Couple of little utility methods to make dealing with generated always
 * columns easier.  If a column is GENERATED ALWAYS, it should not
 * be referenced on an insert or update method.
 * 
 * <p>If a column is identity, it should not be referenced on an insert method.
 *  
 * @author Jeff Butler
 *
 */
public class ListUtilities {
    
    private ListUtilities() {}

    public static List<IntrospectedColumn> removeGeneratedAlwaysColumns(List<IntrospectedColumn> columns) {
        return columns.stream()
                .filter(ic -> !ic.isGeneratedAlways())
                .collect(Collectors.toList());
    }

    public static List<IntrospectedColumn> removeIdentityAndGeneratedAlwaysColumns(List<IntrospectedColumn> columns) {
        return columns.stream()
                .filter(ic -> !ic.isGeneratedAlways() && !ic.isIdentity())
                .collect(Collectors.toList());
    }
}
