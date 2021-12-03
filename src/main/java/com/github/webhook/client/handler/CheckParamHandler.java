package com.github.webhook.client.handler;

import com.github.webhook.client.param.WebHookParam;

/**
 * TODO
 *
 * @author lonecloud
 * @version v1.0
 * @date 2021/12/111:43 下午
 */
public interface CheckParamHandler {

    boolean check(WebHookParam param);

}
