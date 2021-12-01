package com.github.webhook.client.param;

import lombok.Data;

/**
 * TODO
 *
 * @author lonecloud
 * @version v1.0
 * @date 2021/12/111:38 下午
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
}
