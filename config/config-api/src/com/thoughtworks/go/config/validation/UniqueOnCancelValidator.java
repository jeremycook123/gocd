/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.config.validation;

import java.util.List;

import com.thoughtworks.go.config.registry.ConfigElementImplementationRegistry;
import org.jdom.Element;
import org.jdom.xpath.XPath;
import com.thoughtworks.go.util.ConfigUtil;

public class UniqueOnCancelValidator implements GoConfigXMLValidator {
    public void validate(Element element, ConfigElementImplementationRegistry registry) throws Exception {
        List<String> tasks = ConfigUtil.allTasks(registry);
        for (String task : tasks) {
            List taskNodes = XPath.selectNodes(element, "//" + task);
            for (Object taskNode : taskNodes) {
                List list = XPath.selectNodes(taskNode, "oncancel");
                if (list.size() > 1) {
                    throw new Exception("Task [" + task + "] should not contain more than 1 oncancel task");
                }
            }
        }
    }
}
