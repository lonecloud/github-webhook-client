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
package com.github.webhook.client.handler.def;

import com.github.webhook.client.WebHookAction;
import com.github.webhook.client.WebHookClientConfig;
import com.github.webhook.client.cts.GlobalCts;
import com.github.webhook.client.expection.GithubWebHookException;
import com.github.webhook.client.handler.CheckParamHandler;
import com.github.webhook.client.param.WebHookParam;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * 检查Header正确性
 *
 * @author lonecloud
 */
@WebHookAction
public class CheckHeaderHandler implements CheckParamHandler {

    /**
     * 检查Header是否正常
     *
     * @param param
     */
    private void checkHeader(WebHookParam param) {
        WebHookClientConfig clientConfig = param.getWebHookClientConfig();
        WebHookParam.WebHookHeaderParam webHookHeaderParam = param.getWebHookHeaderParam();
        if (!StringUtils.startsWith(webHookHeaderParam.getUserAgent(), GlobalCts.HEADER_NAME_GITHUB_PREFIX)) {
            throw new GithubWebHookException("this userAgent not Accept");
        }
        if (clientConfig.getCheckSign()) {
            String signature = webHookHeaderParam.getSignature();
            if (StringUtils.isBlank(signature)) {
                throw new GithubWebHookException("No signature");
            }
            String computed = String.format("sha1=%s", new HmacUtils(HmacAlgorithms.HMAC_SHA_1, clientConfig.getSecretKey()).hmacHex(param.getData()));
            if (!MessageDigest.isEqual(signature.getBytes(), computed.getBytes())) {
                throw new GithubWebHookException("Invalid signature.");
            }
            String signature256 = webHookHeaderParam.getSignature256();
            if (StringUtils.isBlank(signature256)) {
                throw new GithubWebHookException("No signature");
            }
            String computed256 = String.format("sha256=%s", new HmacUtils(HmacAlgorithms.HMAC_SHA_256, clientConfig.getSecretKey()).hmacHex(param.getData()));
            if (!MessageDigest.isEqual(signature256.getBytes(), computed256.getBytes())) {
                throw new GithubWebHookException("Invalid signature.");
            }
        }
    }

    @Override
    public boolean check(WebHookParam param) {
        checkHeader(param);
        return true;
    }
}
