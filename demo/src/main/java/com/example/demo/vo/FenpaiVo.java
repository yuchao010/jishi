package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FenpaiVo {
    //工单id
    private Integer id;
    //部门id
    private Integer deptId;
    //部门名称
    private String deptName;
}
