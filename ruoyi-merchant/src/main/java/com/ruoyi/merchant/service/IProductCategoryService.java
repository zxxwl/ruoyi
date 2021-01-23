package com.ruoyi.merchant.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.merchant.domain.ProductCategory;

/**
 * 类目Service接口
 *
 * @author ztt
 * @date 2021-01-21
 */
public interface IProductCategoryService
{
    /**
     * 查询类目
     *
     * @param categoryId 类目ID
     * @return 类目
     */
    public ProductCategory selectProductCategoryById(Long categoryId);

    /**
     * 查询类目列表
     *
     * @param productCategory 类目
     * @return 类目集合
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

    /**
     * 新增类目
     *
     * @param productCategory 类目
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改类目
     *
     * @param productCategory 类目
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 批量删除类目
     *
     * @param categoryIds 需要删除的类目ID
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] categoryIds);

    /**
     * 删除类目信息
     *
     * @param categoryId 类目ID
     * @return 结果
     */
    public int deleteProductCategoryById(Long categoryId);

    /**
     * 修改类目状态
     *
     * @param productCategory 类目信息
     * @return 结果
     */
    int updateCategoryStatus(ProductCategory productCategory);
}
