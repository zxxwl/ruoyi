package com.ruoyi.buyer.controller;

import com.ruoyi.buyer.domain.BuyerUser;
import com.ruoyi.buyer.service.IBuyerUserService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息Controller
 *
 * @author ztt
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/buyer/user")
public class BuyerUserController extends BaseController
{
    @Autowired
    private IBuyerUserService buyerUserService;

    /**
     * 查询用户信息列表
     */
    @GetMapping("/list")
    public List<BuyerUser> list(BuyerUser buyerUser)
    {
        List<BuyerUser> list = buyerUserService.selectBuyerUserList(buyerUser);
        return list;
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('buyer:buyer:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BuyerUser buyerUser)
    {
        List<BuyerUser> list = buyerUserService.selectBuyerUserList(buyerUser);
        ExcelUtil<BuyerUser> util = new ExcelUtil<BuyerUser>(BuyerUser.class);
        return util.exportExcel(list, "buyer");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('buyer:buyer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(buyerUserService.selectBuyerUserById(id));
    }

    /**
     * 微信用户授权信息保存
     */
    @Log(title = "微信用户授权信息保存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BuyerUser buyerUser)
    {
        int result=buyerUserService.selectBuyerUserByOpenId(buyerUser.getOpenid());
        if (result>0){
            return toAjax(buyerUserService.updateBuyerByOpenid(buyerUser));
        }
        return toAjax(buyerUserService.insertBuyerUser(buyerUser));
    }

    /**
     * 修改微信用户信息
     */
    @PreAuthorize("@ss.hasPermi('buyer:buyer:edit')")
    @Log(title = "修改微信用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BuyerUser buyerUser)
    {
        return toAjax(buyerUserService.updateBuyerUser(buyerUser));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('buyer:buyer:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(buyerUserService.deleteBuyerUserByIds(ids));
    }
}
