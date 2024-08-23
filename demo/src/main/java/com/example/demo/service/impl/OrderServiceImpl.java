package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.NumberAndProbabilityDto;
import com.example.demo.entity.Dept;
import com.example.demo.entity.Order;
import com.example.demo.exception.*;
import com.example.demo.mapper.DeptMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import com.example.demo.util.Utils;
import com.example.demo.vo.FenpaiVo;
import com.example.demo.vo.SelectPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Order> selectAll(SelectPageVo selectPageVo) {
        IPage<Order> orderIPage = new Page<>(selectPageVo.getPageNo(),selectPageVo.getPageSize());
        Page<Order> orderPage = (Page<Order>) orderMapper.selectPage(orderIPage, null);
        return orderPage.getRecords();
    }

    @Override
    public Order selectById(Order order) {
        return orderMapper.selectById(order.getId());
    }

    @Override
    public List<Order> selectByTitle(Order order) {
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Order::getTitle, order.getTitle());
        return orderMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void delete(Order order) throws DeleteNoIdException {
        if (orderMapper.selectById(order.getId()) == null){
            throw new DeleteNoIdException("删除的工单id不存在");
        }
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Order::getId, order.getId());
        orderMapper.delete(lambdaQueryWrapper);
    }

    @Override
    @Transactional
    public void create(Order order) throws CreateOperationRepeatException, CreateErrorOrderTypeException {
        //判断工单编号是否重复
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Order::getOrderNo, order.getOrderNo());
        if (orderMapper.selectList(lambdaQueryWrapper).size() != 0){
            throw new CreateOperationRepeatException("工单编号不能重复");
        }
        if (order.getOrderType() != 0 && order.getOrderType() != 1 && order.getOrderType() != 3){
            throw new CreateErrorOrderTypeException("工单类型只能是0/1/3");
        }
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insert(order);
    }

    @Override
    @Transactional
    public void update(Order order) throws UpdateErrorIdException, UpdateOperationRepeatException {
        //判断是否有该id的工单
        Order orderNew = orderMapper.selectById(order.getId());
        if (orderNew == null){
            throw new UpdateErrorIdException("没有该id的工单");
        }
        //如果修改的数据有工单编号 需要判断是否重复
        if (order.getOrderNo() != null){
            LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Order::getOrderNo, order.getOrderNo());
            if (orderMapper.selectList(lambdaQueryWrapper).size() != 0){
                throw new UpdateOperationRepeatException("工单编号不能重复");
            }
        }
        //处理部门修改说明要么从没有处理部门转换为有部门
        //或者有处理部门时修改处理部门
        //都需要修改分派时间
        if (order.getHandleDeptId() != null){
            order.setFenpaiTime(LocalDateTime.now());
        }
        //如果标题和内容没添加数据为"" 会导致原数据被清零 需要设置
        if (order.getTitle() == ""){
            order.setTitle(null);
        }
        if (order.getContent() == ""){
            order.setContent(null);
        }
        orderMapper.updateById(order);
    }

    @Override
    @Transactional
    public void fenpai(FenpaiVo fenpaiVo) throws FenpaiNoOrderIdException, FenPaiHandleIdExistsException, FenpaiNoDeptIdException, FenpaiDeptNameErrorException {
        //判断工单id是否有对应的工单
        Order order = orderMapper.selectById(fenpaiVo.getId());
        if (order == null){
            throw new FenpaiNoOrderIdException("工单id没有对应的工单");
        }
        //判断工单id是否已经有处理部门
        if (order.getHandleDeptId() != null){
            throw new FenPaiHandleIdExistsException("工单id已经有处理部门了");
        }
        //判断部门id是否存在
        Dept dept = deptMapper.selectById(fenpaiVo.getDeptId());
        if (dept == null){
            throw new FenpaiNoDeptIdException("部门id没有对应的部门");
        }
        //判断部门id是否对应部门名称
        if (!dept.getDeptName().equals(fenpaiVo.getDeptName())){
            throw new FenpaiDeptNameErrorException("部门id和部门名称不对应");
        }
        order.setHandleDeptId(fenpaiVo.getDeptId());
        order.setFenpaiTime(LocalDateTime.now());
        orderMapper.updateById(order);
    }

    @Override
    public List<NumberAndProbabilityDto> eachDayExpiredProbability() {
        int allNumber = 0;
        int expiredNumber = 0;
        Double expiredProbability = 0.0d;
        List<Order> orderList = new ArrayList<>();
        List<NumberAndProbabilityDto> numberAndProbabilityDtoList = new ArrayList<>();
        LambdaQueryWrapper<Order> lambdaQueryWrapperOrder = new LambdaQueryWrapper<>();
        LocalDateTime start = LocalDateTime.of(2024, 7, 1, 0, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //方式一 占用大量connection连接资源
        //从7月1日到31日循环查询31次
        //每次查询每天的工单总数 超期的工单总数 超期几率
        //封装为以日期为key属性值的NumberAndProbabilityDto对象泛型的集合
        /*
        for (int a = 1; a <= 31; a++){
            lambdaQueryWrapperOrder.ge(Order::getCreateTime, start.plusDays(a - 1));
            lambdaQueryWrapperOrder.le(Order::getCreateTime, start.plusDays(a));
            allNumber = orderMapper.selectList(lambdaQueryWrapperOrder).size();
            lambdaQueryWrapperOrder.eq(Order::getIsOverdue, 1);
            expiredNumber = orderMapper.selectList(lambdaQueryWrapperOrder).size();
            numberAndProbabilityDtoList.add(NumberAndProbabilityDto.builder()
                    .key(LocalDateTime.of(2024, 7, a, 0, 0, 0).format(formatter))
                    .allNumber(allNumber)
                    .expiredNumber(expiredNumber)
                    .expiredProbability(expiredProbability)
                    .build()
            );
        }
        return numberAndProbabilityDtoList;
         */

        //方式二 占用少量connection连接资源
        //查询7月之内的所有工单
        //按照天数遍历31次 每次遍历按照时间查询工单集合查询出当天的所有工单和超期工单
        lambdaQueryWrapperOrder.ge(Order::getCreateTime, LocalDateTime.of(2024, 7, 1, 0, 0, 0));
        lambdaQueryWrapperOrder.le(Order::getCreateTime, LocalDateTime.of(2024, 8, 1, 0, 0, 0));
        orderList = orderMapper.selectList(lambdaQueryWrapperOrder);
        for (int a = 1; a <= 31; a++){
            for (Order order:orderList){
                if (order.getCreateTime().getDayOfMonth() == a){
                    allNumber++;
                    if (order.getIsOverdue() != null && order.getIsOverdue() == 1){
                        expiredNumber++;
                    }
                }
            }
            expiredProbability = Utils.getExpiredProbability(expiredNumber, allNumber);
            numberAndProbabilityDtoList.add(NumberAndProbabilityDto.builder()
                    .key(LocalDateTime.of(2024, 7, a, 0, 0, 0).format(formatter))
                    .allNumber(allNumber)
                    .expiredNumber(expiredNumber)
                    .expiredProbability(expiredProbability)
                    .build()
            );
        }
        return numberAndProbabilityDtoList;
    }

    @Override
    public List<NumberAndProbabilityDto> eachDeptExpiredProbability() {
        int allNumber = 0;
        int expiredNumber = 0;
        Double expiredProbability = 0.0d;
        List<NumberAndProbabilityDto> numberAndProbabilityDtoList = new ArrayList<>();
        LambdaQueryWrapper<Order> lambdaQueryWrapperOrder = new LambdaQueryWrapper<>();
        //方式一 占用大量connection连接资源
        //获取所有部门的id
        //按照id个数循环 查询每个部门id在7月1日到30日时间之内的工单总数 超期的工单总数 超期几率
        //封装为以部门名称为key属性值的NumberAndProbabilityDto对象泛型的集合
        LambdaQueryWrapper<Dept> lambdaQueryWrapperDept = new LambdaQueryWrapper<>();
        lambdaQueryWrapperDept.orderBy(true,true,Dept::getDeptId);
        List<Dept> deptList = deptMapper.selectList(lambdaQueryWrapperDept);
        for (Dept dept:deptList){
            lambdaQueryWrapperOrder.eq(Order::getHandleDeptId, dept.getDeptId());
            lambdaQueryWrapperOrder.ge(Order::getCreateTime, LocalDateTime.of(2024, 7, 1, 0, 0, 0));
            lambdaQueryWrapperOrder.le(Order::getCreateTime, LocalDateTime.of(2024, 8, 1, 0, 0, 0));
            allNumber = orderMapper.selectList(lambdaQueryWrapperOrder).size();
            lambdaQueryWrapperOrder.eq(Order::getIsOverdue, 1);
            expiredNumber = orderMapper.selectList(lambdaQueryWrapperOrder).size();
            expiredProbability = Utils.getExpiredProbability(expiredNumber, allNumber);
            numberAndProbabilityDtoList.add(NumberAndProbabilityDto.builder()
                    .key(dept.getDeptName())
                    .allNumber(allNumber)
                    .expiredNumber(expiredNumber)
                    .expiredProbability(expiredProbability)
                    .build()
            );
        }
        return numberAndProbabilityDtoList;
    }

    @Override
    public List<NumberAndProbabilityDto> eachOrderTypeExpiredProbability() {
        int allNumber = 0;
        int expiredNumber = 0;
        Double expiredProbability = 0.0d;
        List<NumberAndProbabilityDto> numberAndProbabilityDtoList = new ArrayList<>();
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //方式一 占用大量connection连接资源
        //按照固定的三个类型
        //循环三次 查询每个类型在7月1日到30日时间之内的工单总数 超期的工单总数 超期几率
        //封装为以类型为key属性值的NumberAndProbabilityDto对象泛型的集合
        lambdaQueryWrapper.eq(Order::getOrderType, 0);
        lambdaQueryWrapper.ge(Order::getCreateTime, LocalDateTime.of(2024, 7, 1, 0, 0, 0));
        lambdaQueryWrapper.le(Order::getCreateTime, LocalDateTime.of(2024, 8, 1, 0, 0, 0));
        allNumber = orderMapper.selectList(lambdaQueryWrapper).size();
        lambdaQueryWrapper.eq(Order::getIsOverdue, 1);
        expiredNumber = orderMapper.selectList(lambdaQueryWrapper).size();
        expiredProbability = Utils.getExpiredProbability(expiredNumber, allNumber);
        numberAndProbabilityDtoList.add(NumberAndProbabilityDto.builder()
                        .key(0)
                        .allNumber(allNumber)
                        .expiredNumber(expiredNumber)
                        .expiredProbability(expiredProbability)
                        .build()
        );

        lambdaQueryWrapper.eq(Order::getOrderType, 1);
        lambdaQueryWrapper.ge(Order::getCreateTime, LocalDateTime.of(2024, 7, 1, 0, 0, 0));
        lambdaQueryWrapper.le(Order::getCreateTime, LocalDateTime.of(2024, 8, 1, 0, 0, 0));
        allNumber = orderMapper.selectList(lambdaQueryWrapper).size();
        lambdaQueryWrapper.eq(Order::getIsOverdue, 1);
        expiredNumber = orderMapper.selectList(lambdaQueryWrapper).size();
        expiredProbability = Utils.getExpiredProbability(expiredNumber, allNumber);
        numberAndProbabilityDtoList.add(NumberAndProbabilityDto.builder()
                .key(1)
                .allNumber(allNumber)
                .expiredNumber(expiredNumber)
                .expiredProbability(expiredProbability)
                .build()
        );

        lambdaQueryWrapper.eq(Order::getOrderType, 3);
        lambdaQueryWrapper.ge(Order::getCreateTime, LocalDateTime.of(2024, 7, 1, 0, 0, 0));
        lambdaQueryWrapper.le(Order::getCreateTime, LocalDateTime.of(2024, 8, 1, 0, 0, 0));
        allNumber = orderMapper.selectList(lambdaQueryWrapper).size();
        lambdaQueryWrapper.eq(Order::getIsOverdue, 1);
        expiredNumber = orderMapper.selectList(lambdaQueryWrapper).size();
        expiredProbability = Utils.getExpiredProbability(expiredNumber, allNumber);
        numberAndProbabilityDtoList.add(NumberAndProbabilityDto.builder()
                .key(3)
                .allNumber(allNumber)
                .expiredNumber(expiredNumber)
                .expiredProbability(expiredProbability)
                .build()
        );
        return numberAndProbabilityDtoList;
    }
}
