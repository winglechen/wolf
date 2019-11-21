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
package study.daydayup.wolf.common.generator.mybatis.api.dom.java.render;

import study.daydayup.wolf.common.generator.mybatis.api.dom.java.CompilationUnit;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Field;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.JavaDomUtils;

import java.util.ArrayList;
import java.util.List;

public class FieldRenderer {

    public List<String> render(Field field, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(field.getJavaDocLines());
        lines.addAll(field.getAnnotations());
        lines.add(renderField(field, compilationUnit));
        
        return lines;
    }
    
    private String renderField(Field field, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();
        sb.append(field.getVisibility().getValue());

        if (field.isStatic()) {
            sb.append("static "); //$NON-NLS-1$
        }

        if (field.isFinal()) {
            sb.append("final "); //$NON-NLS-1$
        }

        if (field.isTransient()) {
            sb.append("transient "); //$NON-NLS-1$
        }

        if (field.isVolatile()) {
            sb.append("volatile "); //$NON-NLS-1$
        }

        sb.append(JavaDomUtils.calculateTypeName(compilationUnit, field.getType()));
        sb.append(' ');
        sb.append(field.getName());
        sb.append(renderInitializationString(field));
        sb.append(';');

        return sb.toString();
    }
    
    private String renderInitializationString(Field field) {
        return field.getInitializationString()
                .map(is -> " = " + is) //$NON-NLS-1$
                .orElse(""); //$NON-NLS-1$
    }
}
