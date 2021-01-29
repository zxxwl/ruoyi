package com.ruoyi.buyer.mapper;

import com.ruoyi.buyer.domain.OrderDetail;
import com.ruoyi.buyer.domain.OrderMaster;

import java.util.List;

/**
 * 订单Mapper接口
 *
 * @author ruoyi
 * @date 2021-01-27
 */
public interface OrderMasterMapper
{
    /**
     * 查询订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    public OrderMaster selectOrderMasterById(String orderId);

    /**
     * 查询订单列表
     *
     * @param orderMaster 订单
     * @return 订单集合
     */
    public List<OrderMaster> selectOrderMasterList(OrderMaster orderMaster);

    /**
     * 新增订单
     *
     * @param orderMaster 订单
     * @return 结果
     */
    public int insertOrderMaster(OrderMaster orderMaster);

    /**
     * 修改订单
     *
     * @param orderMaster 订单
     * @return 结果
     */
    public int updateOrderMaster(OrderMaster orderMaster);

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteOrderMasterById(String orderId);

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderMasterByIds(String[] orderIds);

    /**
     * 批量删除订单商品
     *
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderDetailByOrderIds(String[] orderIds);

    /**
     * 批量新增订单商品
     *
     * @param orderDetailList 订单商品列表
     * @return 结果
     */
    public int batchOrderDetail(List<OrderDetail> orderDetailList);


    /**
     * 通过订单ID删除订单商品信息
     *
     * @param orderId 角色ID
     * @return 结果
     */
    public int deleteOrderDetailByOrderId(String orderId);

    /**
     * wx查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> selectOrderDetailList(String orderId);

    /**
     * 修改订单状态
     * @param orderStatus
     * @return
     */
    int updateOrderMasterStatus(int orderStatus);
}
