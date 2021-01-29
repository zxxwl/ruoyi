package com.ruoyi.merchant.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.merchant.domain.ProductInfo;
import com.ruoyi.merchant.service.IProductInfoService;
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
import com.ruoyi.merchant.domain.ProductCategory;
import com.ruoyi.merchant.service.IProductCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 类目Controller
 *
 * @author ztt
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/merchant/category")
public class ProductCategoryController extends BaseController
{
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IProductInfoService productInfoService;
    /**
     * 查询类目列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductCategory productCategory)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        productCategory.setSellerId(loginUser.getUser().getUserId());
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        return getDataTable(list);
    }
    /**
     * 微信查询类目列表
     */
    @GetMapping("/buyerList")
    public List<ProductCategory> buyerList(ProductCategory productCategory)
    {
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        list.stream().forEach(category -> {
            Long categoryType = category.getCategoryType();
            ProductInfo productInfo=new ProductInfo();
            productInfo.setCategoryType(categoryType);
            List<ProductInfo> lists = productInfoService.selectProductInfoList(productInfo);
            category.setProductInfos(lists);
        });
        return list;
    }

    /**
     * 导出类目列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:export')")
    @Log(title = "类目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductCategory productCategory)
    {
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        ExcelUtil<ProductCategory> util = new ExcelUtil<ProductCategory>(ProductCategory.class);
        return util.exportExcel(list, "category");
    }

    /**
     * 获取类目详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(productCategoryService.selectProductCategoryById(categoryId));
    }

    /**
     * 新增类目
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:add')")
    @Log(title = "类目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategory productCategory)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        productCategory.setSellerId(user.getUserId());
        productCategory.setCreateBy(user.getNickName());
        return toAjax(productCategoryService.insertProductCategory(productCategory));
    }

    /**
     * 修改类目
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:edit')")
    @Log(title = "类目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategory productCategory)
    {
        return toAjax(productCategoryService.updateProductCategory(productCategory));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody ProductCategory productCategory)
    {
        return toAjax(productCategoryService.updateCategoryStatus(productCategory));
    }

    /**
     * 删除类目
     */
    @PreAuthorize("@ss.hasPermi('merchant:category:remove')")
    @Log(title = "类目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(productCategoryService.deleteProductCategoryByIds(categoryIds));
    }
}
