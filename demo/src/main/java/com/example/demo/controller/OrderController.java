package com.example.demo.controller;

import com.example.demo.dto.NumberAndProbabilityDto;
import com.example.demo.entity.Order;
import com.example.demo.exception.*;
import com.example.demo.result.Result;
import com.example.demo.service.DeptService;
import com.example.demo.service.OrderService;
import com.example.demo.vo.FenpaiVo;
import com.example.demo.vo.SelectPageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "工单模块")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeptService deptService;

    @GetMapping("/post/order/search")
    @ApiOperation("工单全部搜索(分页格式默认)")
    public Result<List<Order>> selectAll(SelectPageVo selectPageVo){
        return Result.success(orderService.selectAll(selectPageVo));
    }

    @GetMapping("/post/order/searchId/{id}")
    @ApiOperation("工单id搜索")
    public Result<Order> selectById(@PathVariable Integer id){
        Order order = Order.builder()
                .id(id)
                .build();
        return Result.success(orderService.selectById(order));
    }
    @GetMapping("/post/order/searchTitle/{title}")
    @ApiOperation("工单标题模糊搜索")
    public Result<List<Order>> selectByTitle(@PathVariable String title){
        Order order = Order.builder()
                .title(title)
                .build();
        return Result.success(orderService.selectByTitle(order));
    }
    @DeleteMapping("/post/order/delete/{id}")
    @ApiOperation("删除工单")
    public Result<String> delete(@PathVariable Integer id) throws DeleteNoIdException {
        Order order = Order.builder()
                .id(id)
                .build();
        orderService.delete(order);
        return Result.success("删除成功");
    }
    @PostMapping("/post/order/save")
    @ApiOperation("新建工单")
    public Result<String> create(Order order) throws CreateNoOperationException, CreateOperationRepeatException, CreateErrorOrderTypeException {
        if (order.getOrderNo() == null){
            throw new CreateNoOperationException("工单编号不能为空");
        }
        if (order.getOrderType() == null){
            throw new CreateNoOperationException("工单类型不能为空");
        }
        if (order.getTitle() == null || order.getTitle() == ""){
            throw new CreateNoOperationException("标题不能为空");
        }
        if (order.getContent() == null || order.getContent() == ""){
            throw new CreateNoOperationException("内容不能为空");
        }
        orderService.create(order);
        return Result.success("新增成功");
    }
    @PutMapping ("/post/order/update")
    @ApiOperation("修改工单")
    public Result<String> update(Order order) throws UpdateNoOperationException, UpdateErrorIdException, UpdateOperationRepeatException {
        if (order.getId() == null){
            throw new UpdateNoOperationException("工单id不能为空");
        }
        orderService.update(order);
        return Result.success("修改成功");
    }
    @PutMapping("/post/order/fenpai")
    @ApiOperation("分派工单")
    public Result<String> fenpai(FenpaiVo fenpaiVo) throws FenPaiNoOperationException, FenpaiNoOrderIdException, FenpaiNoDeptIdException, FenPaiHandleIdExistsException, FenpaiDeptNameErrorException {
        if (fenpaiVo.getId() == null){
            throw new FenPaiNoOperationException("工单id不能为空");
        }
        if (fenpaiVo.getDeptId() == null){
            throw new FenPaiNoOperationException("部门id不能为空");
        }
        if (fenpaiVo.getDeptName() == null || fenpaiVo.getDeptName() == ""){
            throw new FenPaiNoOperationException("部门名称不能为空");
        }
        orderService.fenpai(fenpaiVo);
        return Result.success("分派成功");
    }
    @GetMapping("/post/order/select/eachDayNumberAndExpiredProbability")
    @ApiOperation("查询7月每天的工单总量、超期率")
    public Result<List<NumberAndProbabilityDto>> eachDayExpiredProbability(){
        List<NumberAndProbabilityDto> numberAndProbabilityDtoList = orderService.eachDayExpiredProbability();
        for (NumberAndProbabilityDto num:numberAndProbabilityDtoList){
            System.out.println(num);
        }
        return Result.success(numberAndProbabilityDtoList);
    }
    @GetMapping("/post/order/select/eachDeptNumberAndExpiredProbability")
    @ApiOperation("查询7月每个部门的工单总量、超期率")
    public Result<List<NumberAndProbabilityDto>> eachDeptExpiredProbability(){
        List<NumberAndProbabilityDto> numberAndProbabilityDtoList = orderService.eachDeptExpiredProbability();
        for (NumberAndProbabilityDto num:numberAndProbabilityDtoList){
            System.out.println(num);
        }
        return Result.success(numberAndProbabilityDtoList);
    }
    @GetMapping("/post/order/select/eachOrderTypeNumberAndExpiredProbability")
    @ApiOperation("查询7月每个工单类型的工单总量、超期率")
    public Result<List<NumberAndProbabilityDto>> eachOrderTypeExpiredProbability(){
        List<NumberAndProbabilityDto> numberAndProbabilityDtoList = orderService.eachOrderTypeExpiredProbability();
        for (NumberAndProbabilityDto num:numberAndProbabilityDtoList){
            System.out.println(num);
        }
        return Result.success(numberAndProbabilityDtoList);
    }
}
