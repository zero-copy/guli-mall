package com.study.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 07 上午 02:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catelog2Vo implements Serializable {

    private String catalog1Id;

    private List<Catelog3Vo> catalog3List;

    private String id;

    private String name;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Catelog3Vo implements Serializable {

        private String catalog2Id;

        private String id;

        private String name;
    }
}
