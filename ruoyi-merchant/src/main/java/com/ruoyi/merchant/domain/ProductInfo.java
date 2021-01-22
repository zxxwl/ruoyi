package com.ruoyi.merchant.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品对象 product_info
 *
 * @author ruoyi
 * @date 2021-01-21
 */
public class ProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal productPrice;

    /** 库存 */
    @Excel(name = "库存")
    private Long productStock;

    /** 描述 */
    @Excel(name = "描述")
    private String productDescription;

    /** 小图 */
    @Excel(name = "小图")
    private String productIcon;

    /** 商品状态,0正常1下架 */
    @Excel(name = "商品状态,0正常1下架")
    private String productStatus;

    /** 类目编号 */
    @Excel(name = "类目编号")
    private Long categoryType;

    /** 商户编号 */
    @Excel(name = "商户编号")
    private Long sellerId;

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
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
    public void setProductStock(Long productStock)
    {
        this.productStock = productStock;
    }

    public Long getProductStock()
    {
        return productStock;
    }
    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public String getProductDescription()
    {
        return productDescription;
    }
    public void setProductIcon(String productIcon)
    {
        this.productIcon = productIcon;
    }

    public String getProductIcon()
    {
        return productIcon;
    }
    public void setProductStatus(String productStatus)
    {
        this.productStatus = productStatus;
    }

    public String getProductStatus()
    {
        return productStatus;
    }
    public void setCategoryType(Long categoryType)
    {
        this.categoryType = categoryType;
    }

    public Long getCategoryType()
    {
        return categoryType;
    }
    public void setSellerId(Long sellerId)
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId()
    {
        return sellerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productPrice", getProductPrice())
            .append("productStock", getProductStock())
            .append("productDescription", getProductDescription())
            .append("productIcon", getProductIcon())
            .append("productStatus", getProductStatus())
            .append("categoryType", getCategoryType())
            .append("sellerId", getSellerId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
