package com.ruoyi.buyer.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.buyer.domain.OrderDetail;
import com.ruoyi.buyer.listener.OrderEvent;
import com.ruoyi.buyer.sdk.*;
import com.ruoyi.buyer.service.IBuyerUserService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.ResultEnum;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.framework.web.service.TokenService;
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

    @Autowired
    private TokenService tokenService;

    /**
     * 注入事件发布类
     */
    @Autowired
    ApplicationEventPublisher eventPublisher;
    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('buyer:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderMaster orderMaster)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        orderMaster.setSellerId(loginUser.getUser().getUserId());
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
    public AjaxResult add(@RequestBody OrderMaster orderMaster) throws Exception {
        String openid = orderMaster.getBuyerOpenid();
        if (StringUtils.isEmpty(openid)){
            throw new CustomException(ResultEnum.PARAM_ERROR.getMessage(),ResultEnum.PARAM_ERROR.getCode());
        }
        int result=buyerUserService.selectBuyerUserByOpenId(openid);
        if(result>0){
            //订单号
            String orderId = IdUtils.fastUUID();
            orderMaster.setOrderId(orderId);
            BigDecimal orderAmount = orderMaster.getOrderAmount();
            WXPay wxPay=new WXPay(new MyConfig(),WxProgramPayConfig.getNotify_url(),true,true);
            //登录地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            Map<String,String> payMent=new HashMap<>();
            //商品描述
            payMent.put("body", "点餐下单-微信小程序");
            //订单号
            payMent.put("out_trade_no",orderId);
            //设备号 可以为终端设备号(门店号或收银设备ID)
//            payMent.put("device_info", "");
            //标价币种 非必填
//            payMent.put("fee_type", "CNY");
            //订单总金额
            payMent.put("total_fee", orderAmount.multiply(BigDecimal.valueOf(100)).intValue()+"");
            //终端ip
            payMent.put("spbill_create_ip", ip);
            //异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
            payMent.put("notify_url", WxProgramPayConfig.getNotify_url());
            // 交易类型 JSAPI
            payMent.put("trade_type", WXPayConstants.JSAPI);
            //商品ID
            payMent.put("product_id", orderId);
            Map<String, String> resultMap = wxPay.unifiedOrder(payMent);
//            //预付单信息
//            String prepay_id = stringStringMap.get("prepay_id");
//            /*
//             *小程序调起支付数据签名
//             */
//            Map<String, String> packageParams = new HashMap<>();
//            packageParams.put("appId", WxProgramPayConfig.getAppId());
//            packageParams.put("timeStamp", WXPayUtil.getCurrentTimestamp()+"");
//            packageParams.put("nonceStr", WXPayUtil.generateNonceStr());
//            packageParams.put("package", "prepay_id=" + prepay_id);
//            packageParams.put("signType", WXPayConstants.MD5);
//            String packageSign = WXPayUtil.generateSignature(packageParams, WxProgramPayConfig.getMchKey());
//            packageParams.put("paySign", packageSign);
            orderMasterService.insertOrderMaster(orderMaster);
            //发布消息
            eventPublisher.publishEvent(new OrderEvent(this, orderMaster));
            return AjaxResult.success(resultMap);
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
