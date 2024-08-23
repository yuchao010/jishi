package com.example.demo.controller;

import com.example.demo.service.DeptService;
import com.example.demo.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "部门模块")
public class DeptController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeptService deptService;
}
