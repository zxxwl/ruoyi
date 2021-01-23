package com.ruoyi.merchant.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.ProductCategoryMapper;
import com.ruoyi.merchant.domain.ProductCategory;
import com.ruoyi.merchant.service.IProductCategoryService;

/**
 * 类目Service业务层处理
 *
 * @author ztt
 * @date 2021-01-21
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService
{
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询类目
     *
     * @param categoryId 类目ID
     * @return 类目
     */
    @Override
    public ProductCategory selectProductCategoryById(Long categoryId)
    {
        return productCategoryMapper.selectProductCategoryById(categoryId);
    }

    /**
     * 查询类目列表
     *
     * @param productCategory 类目
     * @return 类目
     */
    @Override
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
    {
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 新增类目
     *
     * @param productCategory 类目
     * @return 结果
     */
    @Override
    public int insertProductCategory(ProductCategory productCategory)
    {
        productCategory.setCreateTime(DateUtils.getNowDate());
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    /**
     * 修改类目
     *
     * @param productCategory 类目
     * @return 结果
     */
    @Override
    public int updateProductCategory(ProductCategory productCategory)
    {
        productCategory.setUpdateTime(DateUtils.getNowDate());
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 批量删除类目
     *
     * @param categoryIds 需要删除的类目ID
     * @return 结果
     */
    @Override
    public int deleteProductCategoryByIds(Long[] categoryIds)
    {
        return productCategoryMapper.deleteProductCategoryByIds(categoryIds);
    }

    /**
     * 删除类目信息
     *
     * @param categoryId 类目ID
     * @return 结果
     */
    @Override
    public int deleteProductCategoryById(Long categoryId)
    {
        return productCategoryMapper.deleteProductCategoryById(categoryId);
    }

    @Override
    public int updateCategoryStatus(ProductCategory productCategory) {
        return productCategoryMapper.updateProductCategory(productCategory);
    }
}
