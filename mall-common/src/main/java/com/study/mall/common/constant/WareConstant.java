package com.study.mall.common.constant;

/**
 * @author Harlan
 * @date 2021 10 31 下午 06:43
 */
public class WareConstant {

    public enum PurchaseStatusEnum {

        CREATED(0, "created"),

        ASSIGNED(1, "assigned"),

        RECEIVED(2, "received"),

        FINISHED(3, "finished"),

        ERROR(4, "error");

        private final int value;

        private final String type;

        PurchaseStatusEnum(int value, String type) {
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

    public enum PurchaseDetailStatusEnum {

        CREATED(0, "created"),

        ASSIGNED(1, "assigned"),

        BUYING(2, "buying"),

        FINISHED(3, "finished"),

        ERROR(4, "error");

        private final int value;

        private final String type;

        PurchaseDetailStatusEnum(int value, String type) {
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
