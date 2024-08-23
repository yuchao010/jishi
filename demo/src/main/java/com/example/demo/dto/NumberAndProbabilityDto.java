package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberAndProbabilityDto<T>{
    //查询横坐标元素类型与名称 例如某一天 某一部门名称 某一工单类型
    private T key;
    //该元素对应的所有工单数量
    private Integer allNumber;
    //该元素对应的所有超期工单数量
    private Integer expiredNumber;
    //该元素对应的超期几率
    private Double expiredProbability;
}
