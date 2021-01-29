package com.ruoyi.buyer.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单商品对象 order_detail
 *
 * @author ruoyi
 * @date 2021-01-27
 */
public class OrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer detailId;

    /** $column.columnComment */
    @Excel(name = "修改时间")
    private String orderId;

    /** $column.columnComment */
    @Excel(name = "修改时间")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 当前价格,单位分 */
    @Excel(name = "当前价格,单位分")
    private BigDecimal productPrice;

    /** 数量 */
    @Excel(name = "数量")
    private Long productQuantity;

    /** 小图 */
    @Excel(name = "小图")
    private String productIcon;

    /** 商户编号为sys_user表中的user_id */
    @Excel(name = "商户标识")
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public void setDetailId(Integer detailId)
    {
        this.detailId = detailId;
    }

    public Integer getDetailId()
    {
        return detailId;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductId()
    {
        return productId;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice()
    {
        return productPrice;
    }
    public void setProductQuantity(Long productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    public Long getProductQuantity()
    {
        return productQuantity;
    }
    public void setProductIcon(String productIcon)
    {
        this.productIcon = productIcon;
    }

    public String getProductIcon()
    {
        return productIcon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productPrice", getProductPrice())
            .append("sellerId", getSellerId())
            .append("productQuantity", getProductQuantity())
            .append("productIcon", getProductIcon())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
