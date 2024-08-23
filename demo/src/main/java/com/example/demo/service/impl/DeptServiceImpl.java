package com.example.demo.service.impl;

import com.example.demo.mapper.DeptMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DeptMapper deptMapper;
}
