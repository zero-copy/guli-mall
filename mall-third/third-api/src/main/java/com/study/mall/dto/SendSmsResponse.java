package com.study.mall.dto;

import lombok.Data;
import java.util.Map;

/**
 * @author Harlan
 * @date 2022 06 09 下午 08:16
 */
@Data
public class SendSmsResponse {

    private Map<String, String> headers;

    private SendSmsResponseBody body;

    @Data
    static class SendSmsResponseBody {

        public String bizId;

        public String code;

        public String message;

        public String requestId;
    }
}
