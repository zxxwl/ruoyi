package com.ruoyi.buyer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.buyer.mapper.CommentMapper;
import com.ruoyi.buyer.domain.Comment;
import com.ruoyi.buyer.service.ICommentService;

/**
 * 评论信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-28
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论信息
     * 
     * @param commentId 评论信息ID
     * @return 评论信息
     */
    @Override
    public Comment selectCommentById(Long commentId)
    {
        return commentMapper.selectCommentById(commentId);
    }

    /**
     * 查询评论信息列表
     * 
     * @param comment 评论信息
     * @return 评论信息
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论信息
     * 
     * @param comment 评论信息
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论信息
     * 
     * @param comment 评论信息
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论信息
     * 
     * @param commentIds 需要删除的评论信息ID
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(Long[] commentIds)
    {
        return commentMapper.deleteCommentByIds(commentIds);
    }

    /**
     * 删除评论信息信息
     * 
     * @param commentId 评论信息ID
     * @return 结果
     */
    @Override
    public int deleteCommentById(Long commentId)
    {
        return commentMapper.deleteCommentById(commentId);
    }
}
