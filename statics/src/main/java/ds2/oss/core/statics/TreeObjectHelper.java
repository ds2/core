/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.statics;

import ds2.oss.core.api.TreeObjectSetter;

/**
 * Created by dstrauss on 17.03.17.
 */
public interface TreeObjectHelper {
    /**
     * Adds a child to a given parent.
     *
     * @param parent     the parent node
     * @param child      the child node
     * @param <IDTYPE>   the key type for the IDs
     * @param <TREETYPE> the type of the tree object
     * @return TRUE if child could be added to one of the parents or childs, starting with the given parent, or FALSE
     * if the child could not be added to the tree.
     */
    static <IDTYPE, TREETYPE extends TreeObjectSetter<TREETYPE, IDTYPE>> boolean addToTree(TREETYPE parent, TREETYPE child) {
        IDTYPE childParent = child.getParentId();
        if (parent.getTreeObjectId().equals(childParent)) {
            parent.getChildren().add(child);
            child.setParent(parent);
            return true;
        }
        //recursive look within childs
        boolean rc = false;
        for (TREETYPE thisChild : parent.getChildren()) {
            rc = addToTree(thisChild, child);
            if (rc) {
                break;
            }
        }
        return rc;
    }

    static <IDTYPE, TREETYPE extends TreeObjectSetter<TREETYPE, IDTYPE>> String printTree(TREETYPE parent, int indent) {
        StringBuilder sb = new StringBuilder(200);
        if (indent > 0) {
            for (int i = 0; i < indent; ++i) {
                sb.append("|-");
            }
        }
        sb.append(parent.toString());
        if (Methods.size(parent.getChildren()) > 0) {
            parent.getChildren().forEach(c -> {
                sb.append("\n");
                sb.append(printTree(c, indent + 1));
            });
        }
        return sb.toString();
    }
}
