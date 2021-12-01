package com.github.webhook.client.enums;

public enum ActionEnum {

    /**
     * 分支控制规则事件
     */
    branch_protection_rule,


    ping,
    /**
     * push
     */
    push,

    defaultAction;


    public static ActionEnum getEnum(String name) {
        for (ActionEnum value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return defaultAction;
    }

}
