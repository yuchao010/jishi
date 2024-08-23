package com.example.demo.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectPageVo {
    //查询页数
    private Integer pageNo;
    //查询每页个数
    private Integer pageSize;
}
