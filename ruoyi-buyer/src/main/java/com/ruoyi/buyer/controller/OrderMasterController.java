package com.ruoyi.buyer.controller;

import java.util.List;

import com.ruoyi.buyer.domain.OrderDetail;
import com.ruoyi.buyer.service.IBuyerUserService;
import com.ruoyi.common.enums.ResultEnum;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
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
import com.ruoyi.buyer.domain.OrderMaster;
import com.ruoyi.buyer.service.IOrderMasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2021-01-27
 */
@RestController
@RequestMapping("/buyer/order")
public class OrderMasterController extends BaseController
{
    @Autowired
    private IOrderMasterService orderMasterService;

    @Autowired
    private IBuyerUserService buyerUserService;
    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('buyer:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderMaster orderMaster)
    {
        startPage();
        List<OrderMaster> list = orderMasterService.selectOrderMasterList(orderMaster);
        return getDataTable(list);
    }
    /**
     * 微信端查询订单列表
     */
    @GetMapping("/listByStatus")
    public AjaxResult listByStatus(OrderMaster orderMaster)
    {
        String openid = orderMaster.getBuyerOpenid();
        if (StringUtils.isEmpty(openid)){
            throw new CustomException(ResultEnum.PARAM_ERROR.getMessage(),ResultEnum.PARAM_ERROR.getCode());
        }
        int result=buyerUserService.selectBuyerUserByOpenId(openid);
        if (result>0){
            List<OrderMaster> list = orderMasterService.selectOrderMasterList(orderMaster);
            list.stream().forEach(order->{
                String orderId = order.getOrderId();
                List<OrderDetail> orderDetails=orderMasterService.selectOrderDetailList(orderId);
                order.setOrderDetailList(orderDetails);
            });
            return AjaxResult.success(list);
        }
        return AjaxResult.error("请先授权登录!");
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('buyer:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OrderMaster orderMaster)
    {
        List<OrderMaster> list = orderMasterService.selectOrderMasterList(orderMaster);
        ExcelUtil<OrderMaster> util = new ExcelUtil<OrderMaster>(OrderMaster.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('buyer:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return AjaxResult.success(orderMasterService.selectOrderMasterById(orderId));
    }

    /**
     * 新增订单
     */
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderMaster orderMaster)
    {
        String openid = orderMaster.getBuyerOpenid();
        if (StringUtils.isEmpty(openid)){
            throw new CustomException(ResultEnum.PARAM_ERROR.getMessage(),ResultEnum.PARAM_ERROR.getCode());
        }
        int result=buyerUserService.selectBuyerUserByOpenId(openid);
        if(result>0){
            //订单号
            orderMaster.setOrderId(IdUtils.fastUUID());
            return toAjax(orderMasterService.insertOrderMaster(orderMaster));
        }
        return AjaxResult.error("请先授权登录!");
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('buyer:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderMaster orderMaster)
    {
        return toAjax(orderMasterService.updateOrderMaster(orderMaster));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('buyer:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(orderMasterService.deleteOrderMasterByIds(orderIds));
    }
}
