package com.github.webhook.client.enums;

public enum ActionEnum {

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
