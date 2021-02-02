package com.ruoyi.buyer.controller;

import java.util.List;

import com.ruoyi.buyer.domain.Comment;
import com.ruoyi.buyer.domain.OrderMaster;
import com.ruoyi.buyer.service.IBuyerUserService;
import com.ruoyi.buyer.service.IOrderMasterService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.OrderStatusEnum;
import com.ruoyi.common.enums.ResultEnum;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.buyer.service.ICommentService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论信息Controller
 *
 * @author ruoyi
 * @date 2021-01-28
 */
@RestController
@RequestMapping("/buyer/comment")
public class CommentController extends BaseController
{
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IBuyerUserService buyerUserService;

    @Autowired
    private IOrderMasterService orderMasterService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询评论信息列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:comment:query')")
    @GetMapping("/list")
    public TableDataInfo list(Comment comment)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        comment.setSellerId(loginUser.getUser().getUserId());
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }
    /**
     * wx查询所有评论信息列表
     */
    @GetMapping("/wxList")
    public List<Comment> wxList(Comment comment)
    {
        return commentService.selectCommentList(comment);
    }

    /**
     * 导出评论信息列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:comment:export')")
    @Log(title = "评论信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        ExcelUtil<Comment> util = new ExcelUtil<Comment>(Comment.class);
        return util.exportExcel(list, "buyer");
    }

    /**
     * 获取评论信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:comment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return AjaxResult.success(commentService.selectCommentById(commentId));
    }
    /**
     * wx获取评论信息详细信息
     */
    @GetMapping("/getWxInfo")
    public AjaxResult getWxInfo(Comment comment)
    {
        String openid = comment.getOpenid();
        if(StringUtils.isBlank(openid)){
            throw new CustomException(ResultEnum.PARAM_ERROR.getMessage(),ResultEnum.PARAM_ERROR.getCode());
        }
        return AjaxResult.success(commentService.selectCommentList(comment));
    }

    /**
     * wx新增评论信息
     */
    @Log(title = "wx评论信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Comment comment)
    {
        int result=buyerUserService.selectBuyerUserByOpenId(comment.getOpenid());
        if (result>0){
            //评论后修改订单状态
            OrderMaster orderMaster=new OrderMaster();
            orderMaster.setBuyerOpenid(comment.getOpenid());
            orderMaster.setOrderStatus(OrderStatusEnum.COMMENT.getCode());
            orderMasterService.updateOrderMaster(orderMaster);
            return toAjax(commentService.insertComment(comment));
        }
        return AjaxResult.error("请先授权登录!");
    }

    /**
     * 修改评论信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:comment:edit')")
    @Log(title = "评论信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Comment comment)
    {
        return toAjax(commentService.updateComment(comment));
    }

    /**
     * 删除评论信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:comment:remove')")
    @Log(title = "评论信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(commentService.deleteCommentByIds(commentIds));
    }
}
