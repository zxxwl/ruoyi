package com.ruoyi.merchant.mapper;

import java.util.List;
import com.ruoyi.merchant.domain.ProductCategory;

/**
 * 类目Mapper接口
 *
 * @author ztt
 * @date 2021-01-21
 */
public interface ProductCategoryMapper
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
     * 删除类目
     *
     * @param categoryId 类目ID
     * @return 结果
     */
    public int deleteProductCategoryById(Long categoryId);

    /**
     * 批量删除类目
     *
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] categoryIds);
}
