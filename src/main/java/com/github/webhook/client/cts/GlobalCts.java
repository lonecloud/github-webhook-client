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
package com.github.webhook.client.cts;

public class GlobalCts {
    public static final String USER_AGENT = "User-Agent";
    public static final String HEADER_NAME_Event="X-GitHub-Event";
    public static final String HEADER_NAME_SIGN="X-Hub-Signature";
    public static final String HEADER_NAME_SIGN_256="X-Hub-Signature-256";
    public static final String HEADER_NAME_HOOK_ID="X-GitHub-Hook-ID";
    public static final String HEADER_NAME_TARGET_ID="X-GitHub-Hook-Installation-Target-ID";
    public static final String HEADER_NAME_TARGET_TYPE="X-GitHub-Hook-Installation-Target-Type";
    /**
     * GitHub版本
     */
    public static final String HEADER_NAME_GITHUB_VERSION="X-Github-Webhook-Client-Version";
    /**
     * Github头部信息
     */
    public static final String HEADER_NAME_GITHUB_PREFIX="GitHub-Hookshot";
}
