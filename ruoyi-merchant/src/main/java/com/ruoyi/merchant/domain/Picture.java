package com.ruoyi.merchant.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图对象 picture
 * 
 * @author ruoyi
 * @date 2021-01-28
 */
public class Picture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long picId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String picUrl;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String picMessage;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date picCreate;

    /** 商户编号为sys_user表中的user_id */
    @Excel(name = "商户编号为sys_user表中的user_id")
    private Long sellerId;

    public void setPicId(Long picId) 
    {
        this.picId = picId;
    }

    public Long getPicId() 
    {
        return picId;
    }
    public void setPicUrl(String picUrl) 
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl() 
    {
        return picUrl;
    }
    public void setPicMessage(String picMessage) 
    {
        this.picMessage = picMessage;
    }

    public String getPicMessage() 
    {
        return picMessage;
    }
    public void setPicCreate(Date picCreate) 
    {
        this.picCreate = picCreate;
    }

    public Date getPicCreate() 
    {
        return picCreate;
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
            .append("picId", getPicId())
            .append("picUrl", getPicUrl())
            .append("picMessage", getPicMessage())
            .append("picCreate", getPicCreate())
            .append("sellerId", getSellerId())
            .toString();
    }
}
