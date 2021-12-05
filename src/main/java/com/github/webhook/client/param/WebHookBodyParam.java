/*
 * Copyright © ${project.inceptionYear} organization lonecloud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.webhook.client.param;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author lonecloud
 * @version v1.0
 */
@Data
public class WebHookBodyParam {

    /**
     * 仓库信息
     */
    private RepositoryParam repository;


    /**
     * action
     */
    private String action;

    /**
     * 发送人
     */
    private String sender;

    /************push*****************/

    private String ref;

    private String before;

    private String after;
    /**
     * 推送状态
     */
    private boolean created;
    private boolean deleted;
    private boolean forced;
    /**
     * 提交信息
     */
    private String[] added;
    private String[] removed;
    private String[] modified;

    /**
     * 提交明细
     */
    private HeadCommitParam headCommit;
    /**
     * 提交人
     */
    private UserParam pusher;


    private String baseRef;

    private String compare;

    @Data
    public static class HeadCommitParam {
        private String id;

        private String treeId;

        private boolean distinct;

        private String message;

        private String timestamp;

        private String url;

        private UserParam author;

        private UserParam committer;

        private List<String> added;

        private List<String> removed;

        private List<String> modified;
    }


    @Data
    public static class UserParam {

        private String name;

        private String email;

        private String username;
    }

}
