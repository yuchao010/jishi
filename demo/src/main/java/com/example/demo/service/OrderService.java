package com.example.demo.service;

import com.example.demo.dto.NumberAndProbabilityDto;
import com.example.demo.entity.Order;
import com.example.demo.exception.*;
import com.example.demo.vo.FenpaiVo;
import com.example.demo.vo.SelectPageVo;

import java.util.List;

public interface OrderService {
    List<Order> selectAll(SelectPageVo selectPageVo);
    Order selectById(Order order);
    List<Order> selectByTitle(Order order);
    void delete(Order order) throws DeleteNoIdException;

    void create(Order order) throws CreateOperationRepeatException, CreateNoOperationException, CreateErrorOrderTypeException;

    void update(Order order) throws UpdateErrorIdException, UpdateOperationRepeatException;

    void fenpai(FenpaiVo fenpaiVo) throws FenpaiNoOrderIdException, FenPaiHandleIdExistsException, FenpaiNoDeptIdException, FenpaiDeptNameErrorException;

    List<NumberAndProbabilityDto> eachDayExpiredProbability();

    List<NumberAndProbabilityDto> eachDeptExpiredProbability();
    List<NumberAndProbabilityDto> eachOrderTypeExpiredProbability();
}
