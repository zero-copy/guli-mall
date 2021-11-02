package com.study.mall.common.constant;

/**
 * @author Harlan
 * @date 2021 10 24 下午 05:27
 */
public class ProductConstant {

    public enum AttrEnum {

        ATTR_TYPE_BASE(1, "base"),

        ATTR_TYPE_SALE(0, "sale");

        private final int value;

        private final String type;

        AttrEnum(int value, String type) {
            this.value = value;
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public String getType() {
            return type;
        }
    }

    public enum StatusEnum {

        NEW_SPU(0, "new"),

        UP(1, "up"),

        DOWN(2, "down");

        private final int value;

        private final String type;

        StatusEnum(int value, String type) {
            this.value = value;
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public String getType() {
            return type;
        }
    }
}
