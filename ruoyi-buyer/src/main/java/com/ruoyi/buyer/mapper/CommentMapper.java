package com.ruoyi.buyer.mapper;

import java.util.List;
import com.ruoyi.buyer.domain.Comment;

/**
 * 评论信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-01-28
 */
public interface CommentMapper
{
    /**
     * 查询评论信息
     *
     * @param commentId 评论信息ID
     * @return 评论信息
     */
    public Comment selectCommentById(Long commentId);

    /**
     * 查询评论信息列表
     *
     * @param comment 评论信息
     * @return 评论信息集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 新增评论信息
     *
     * @param comment 评论信息
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改评论信息
     *
     * @param comment 评论信息
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 删除评论信息
     *
     * @param commentId 评论信息ID
     * @return 结果
     */
    public int deleteCommentById(Long commentId);

    /**
     * 批量删除评论信息
     *
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentByIds(Long[] commentIds);

    /**
     * 通过openid查询评论列表
     * @param openid
     * @return
     */
    List<Comment> selectCommentByOpenId(String openid);
}
