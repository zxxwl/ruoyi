package com.ruoyi.buyer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论信息对象 comment
 *
 * @author ruoyi
 * @date 2021-01-28
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long commentId;

    /** 评论者姓名 */
    @Excel(name = "评论者姓名")
    private String name;

    /** 评论者姓名 */
    @Excel(name = "评论者姓名")
    private String openid;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;


    /** $column.columnComment */
    @Excel(name = "评论内容")
    private String avatarUrl;

    /** 商户编号为sys_user表中的user_id */
    @Excel(name = "商户标识")
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public Long getCommentId()
    {
        return commentId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return openid;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("name", getName())
            .append("openid", getOpenid())
            .append("content", getContent())
            .append("sellerId", getSellerId())
            .append("createTime", getCreateTime())
            .append("avatarUrl", getAvatarUrl())
            .toString();
    }
}
