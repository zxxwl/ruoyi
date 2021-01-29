package com.ruoyi.merchant.controller;

import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.merchant.domain.ProductCategory;
import com.ruoyi.merchant.domain.ProductInfo;
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
import com.ruoyi.merchant.service.IProductInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品Controller
 *
 * @author ruoyi
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/merchant/commodity")
public class ProductInfoController extends BaseController
{
    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ServerConfig serverConfig;
    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductInfo productInfo)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        productInfo.setSellerId(loginUser.getUser().getUserId());
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        return getDataTable(list);
    }
    /**
     * 微信查询商品列表
     */
    @GetMapping("/buyerList")
    public List<ProductInfo> buyerList(ProductInfo productInfo)
    {
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        return list;
    }
    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductInfo productInfo)
    {
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        ExcelUtil<ProductInfo> util = new ExcelUtil<ProductInfo>(ProductInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") String productId)
    {
        return AjaxResult.success(productInfoService.selectProductInfoById(productId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductInfo productInfo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        productInfo.setSellerId(user.getUserId());
        productInfo.setCreateBy(user.getNickName());
        return toAjax(productInfoService.insertProductInfo(productInfo));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductInfo productInfo)
    {
        return toAjax(productInfoService.updateProductInfo(productInfo));
    }

    /**
     * 修改商品状态
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:edit')")
    @Log(title = "商品状态", businessType = BusinessType.UPDATE)
    @PutMapping("/changeProductStatus")
    public AjaxResult changeProductStatus(@RequestBody ProductInfo productInfo)
    {
        return toAjax(productInfoService.changeProductStatus(productInfo));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('merchant:commodity:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable String[] productIds)
    {
        return toAjax(productInfoService.deleteProductInfoByIds(productIds));
    }
}
