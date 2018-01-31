package com.rill.enums;

/**
 * @author ZhangPeng
 * @date 2018/1/31
 */
public enum ExprTypeEnum {
    NORMAL(1,"常规"),
    LEFT_BRACKETS(2,"左边括号"),
    RIGHT_BRACKETS(3,"右边括号");

    private int type;
    private String name;

    ExprTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
