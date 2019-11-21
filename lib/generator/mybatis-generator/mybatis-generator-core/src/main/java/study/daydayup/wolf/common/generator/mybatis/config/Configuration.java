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
package study.daydayup.wolf.common.generator.mybatis.config;

import study.daydayup.wolf.common.generator.mybatis.exception.InvalidConfigurationException;

import java.util.ArrayList;
import java.util.List;

import static study.daydayup.wolf.common.generator.mybatis.internal.util.StringUtility.stringHasValue;
import static study.daydayup.wolf.common.generator.mybatis.internal.util.messages.Messages.getString;

public class Configuration {

    private List<Context> contexts;

    private List<String> classPathEntries;

    public Configuration() {
        super();
        contexts = new ArrayList<>();
        classPathEntries = new ArrayList<>();
    }

    public void addClasspathEntry(String entry) {
        classPathEntries.add(entry);
    }

    public List<String> getClassPathEntries() {
        return classPathEntries;
    }

    /**
     * This method does a simple validate, it makes sure that all required fields have been filled in and that all
     * implementation classes exist and are of the proper type. It does not do any more complex operations such as:
     * validating that database tables exist or validating that named columns exist
     *
     * @throws InvalidConfigurationException
     *             the invalid configuration exception
     */
    public void validate() throws InvalidConfigurationException {
        List<String> errors = new ArrayList<>();

        for (String classPathEntry : classPathEntries) {
            if (!stringHasValue(classPathEntry)) {
                errors.add(getString("ValidationError.19")); //$NON-NLS-1$
                // only need to state this error once
                break;
            }
        }

        if (contexts.isEmpty()) {
            errors.add(getString("ValidationError.11")); //$NON-NLS-1$
        } else {
            for (Context context : contexts) {
                context.validate(errors);
            }
        }

        if (!errors.isEmpty()) {
            throw new InvalidConfigurationException(errors);
        }
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void addContext(Context context) {
        contexts.add(context);
    }

    public Context getContext(String id) {
        for (Context context : contexts) {
            if (id.equals(context.getId())) {
                return context;
            }
        }

        return null;
    }
}
