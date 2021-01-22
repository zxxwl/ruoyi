package com.ruoyi.merchant.service;

import java.util.List;
import com.ruoyi.merchant.domain.ProductInfo;

/**
 * 商品Service接口
 *
 * @author ruoyi
 * @date 2021-01-21
 */
public interface IProductInfoService
{
    /**
     * 查询商品
     *
     * @param productId 商品ID
     * @return 商品
     */
    public ProductInfo selectProductInfoById(String productId);

    /**
     * 查询商品列表
     *
     * @param productInfo 商品
     * @return 商品集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);

    /**
     * 新增商品
     *
     * @param productInfo 商品
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);

    /**
     * 修改商品
     *
     * @param productInfo 商品
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 批量删除商品
     *
     * @param productIds 需要删除的商品ID
     * @return 结果
     */
    public int deleteProductInfoByIds(String[] productIds);

    /**
     * 删除商品信息
     *
     * @param productId 商品ID
     * @return 结果
     */
    public int deleteProductInfoById(String productId);

    /**
     * 校验是否允许操作
     * @param productInfo
     */
    void checkProductAllowed(ProductInfo productInfo);

    /**
     * 修改商品状态
     * @param productInfo
     * @return
     */
    int changeProductStatus(ProductInfo productInfo);
}
