package com.github.webhook.client;

import com.github.webhook.client.enums.ActionEnum;

import java.lang.annotation.*;

/**
 * WebHook 注解
 *
 * @author lonecloud
 * @date 2021/11/29 23:28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebHookAction {

    ActionEnum value() default ActionEnum.defaultAction;
}
