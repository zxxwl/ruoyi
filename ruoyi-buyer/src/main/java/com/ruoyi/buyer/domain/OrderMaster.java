package com.ruoyi.buyer.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 order_master
 *
 * @author ruoyi
 * @date 2021-01-27
 */
public class OrderMaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String orderId;

    /** 买家名字 */
    @Excel(name = "买家名字")
    private String buyerName;

    /** 买家电话 */
    @Excel(name = "买家电话")
    private String buyerPhone;

    /** 买家桌号 */
    @Excel(name = "买家桌号")
    private String buyerAddress;

    /** 买家微信openid */
    @Excel(name = "买家微信openid")
    private String buyerOpenid;

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private BigDecimal orderAmount;

    /** 订单状态, 默认为新下单 */
    @Excel(name = "订单状态, 默认为新下单")
    private Integer orderStatus;

    /** 支付状态, 默认未支付 */
    @Excel(name = "支付状态, 默认未支付")
    private Integer payStatus;

    /** 用餐人数 */
    @Excel(name = "用餐人数")
    private String peoples;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 商户编号为sys_user表中的user_id */
    @Excel(name = "商户标识")
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /** 订单商品信息 */
    private List<OrderDetail> orderDetailList;

    public String getPeoples() {
        return peoples;
    }

    public void setPeoples(String peoples) {
        this.peoples = peoples;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setBuyerName(String buyerName)
    {
        this.buyerName = buyerName;
    }

    public String getBuyerName()
    {
        return buyerName;
    }
    public void setBuyerPhone(String buyerPhone)
    {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerPhone()
    {
        return buyerPhone;
    }
    public void setBuyerAddress(String buyerAddress)
    {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerAddress()
    {
        return buyerAddress;
    }
    public void setBuyerOpenid(String buyerOpenid)
    {
        this.buyerOpenid = buyerOpenid;
    }

    public String getBuyerOpenid()
    {
        return buyerOpenid;
    }
    public void setOrderAmount(BigDecimal orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount()
    {
        return orderAmount;
    }
    public void setOrderStatus(Integer orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus()
    {
        return orderStatus;
    }
    public void setPayStatus(Integer payStatus)
    {
        this.payStatus = payStatus;
    }

    public Integer getPayStatus()
    {
        return payStatus;
    }

    public List<OrderDetail> getOrderDetailList()
    {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList)
    {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("buyerName", getBuyerName())
            .append("buyerPhone", getBuyerPhone())
            .append("buyerAddress", getBuyerAddress())
            .append("buyerOpenid", getBuyerOpenid())
            .append("orderAmount", getOrderAmount())
            .append("sellerId", getSellerId())
            .append("orderStatus", getOrderStatus())
            .append("payStatus", getPayStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("people", getPeoples())
            .append("orderDetailList", getOrderDetailList())
            .append("remarks", getRemarks())
            .toString();
    }
}
