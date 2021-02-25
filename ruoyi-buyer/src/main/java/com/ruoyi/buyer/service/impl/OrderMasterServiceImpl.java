package com.ruoyi.buyer.service.impl;

import java.util.List;

import com.ruoyi.buyer.socket.WebSocket;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.buyer.domain.OrderDetail;
import com.ruoyi.buyer.mapper.OrderMasterMapper;
import com.ruoyi.buyer.domain.OrderMaster;
import com.ruoyi.buyer.service.IOrderMasterService;

/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2021-01-27
 */
@Service
public class OrderMasterServiceImpl implements IOrderMasterService
{
    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private WebSocket webSocket;
    /**
     * 查询订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    @Override
    public OrderMaster selectOrderMasterById(String orderId)
    {
        return orderMasterMapper.selectOrderMasterById(orderId);
    }

    /**
     * 查询订单列表
     *
     * @param orderMaster 订单
     * @return 订单
     */
    @Override
    public List<OrderMaster> selectOrderMasterList(OrderMaster orderMaster)
    {
        return orderMasterMapper.selectOrderMasterList(orderMaster);
    }

    /**
     * 新增订单
     *
     * @param orderMaster 订单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertOrderMaster(OrderMaster orderMaster)
    {
        orderMaster.setCreateTime(DateUtils.getNowDate());
        int rows = orderMasterMapper.insertOrderMaster(orderMaster);
        insertOrderDetail(orderMaster);
        webSocket.sendOneMessage("admin",orderMaster.toString());
        return rows;
    }

    /**
     * 修改订单
     *
     * @param orderMaster 订单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateOrderMaster(OrderMaster orderMaster)
    {
        orderMaster.setUpdateTime(DateUtils.getNowDate());
        orderMasterMapper.deleteOrderDetailByOrderId(orderMaster.getOrderId());
        insertOrderDetail(orderMaster);
        return orderMasterMapper.updateOrderMaster(orderMaster);
    }

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteOrderMasterByIds(String[] orderIds)
    {
        orderMasterMapper.deleteOrderDetailByOrderIds(orderIds);
        return orderMasterMapper.deleteOrderMasterByIds(orderIds);
    }

    /**
     * 删除订单信息
     *
     * @param orderId 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderMasterById(String orderId)
    {
        orderMasterMapper.deleteOrderDetailByOrderId(orderId);
        return orderMasterMapper.deleteOrderMasterById(orderId);
    }

    /**
     * wx查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public List<OrderDetail> selectOrderDetailList(String orderId) {
        return orderMasterMapper.selectOrderDetailList(orderId);
    }

    /**
     * 完成评价后 修改订单状态
     * @param orderStatus
     * @return
     */
    @Override
    public int updateOrderMasterStatus(int orderStatus) {
        return orderMasterMapper.updateOrderMasterStatus(orderStatus);
    }

    /**
     * 新增订单商品信息
     *
     * @param orderMaster 订单对象
     */
    public void insertOrderDetail(OrderMaster orderMaster)
    {
        List<OrderDetail> orderDetailList = orderMaster.getOrderDetailList();
        String orderId = orderMaster.getOrderId();
        if (StringUtils.isNotNull(orderDetailList))
        {
            List<OrderDetail> list = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetail : orderDetailList)
            {
                orderDetail.setOrderId(orderId);
                list.add(orderDetail);
            }
            if (list.size() > 0)
            {
                orderMasterMapper.batchOrderDetail(list);
            }
        }
    }
}
