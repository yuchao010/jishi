package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("jishi_order")
public class Order {
    //工单id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //工单编号
    private Integer orderNo;
    //工单类型 0交办 1直接答复 3无效工单
    private Integer orderType;
    //标题
    private String title;
    //内容
    private String content;
    //处理部门
    private Integer handleDeptId;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //分派时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fenpaiTime;
    //是否超期 0否 1是
    private Integer isOverdue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(orderNo, order.orderNo) && Objects.equals(orderType, order.orderType) && Objects.equals(title, order.title) && Objects.equals(content, order.content) && Objects.equals(handleDeptId, order.handleDeptId) && Objects.equals(createTime, order.createTime) && Objects.equals(fenpaiTime, order.fenpaiTime) && Objects.equals(isOverdue, order.isOverdue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNo, orderType, title, content, handleDeptId, createTime, fenpaiTime, isOverdue);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo=" + orderNo +
                ", orderType=" + orderType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", handleDeptId=" + handleDeptId +
                ", createTime=" + createTime +
                ", fenpaiTime=" + fenpaiTime +
                ", isOverdue=" + isOverdue +
                '}';
    }
}
