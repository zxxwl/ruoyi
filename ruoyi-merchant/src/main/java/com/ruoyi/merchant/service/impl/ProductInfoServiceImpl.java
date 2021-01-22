package com.ruoyi.merchant.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.ProductInfoMapper;
import com.ruoyi.merchant.domain.ProductInfo;
import com.ruoyi.merchant.service.IProductInfoService;

/**
 * 商品Service业务层处理
 *
 * @author ruoyi
 * @date 2021-01-21
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService
{
    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 查询商品
     *
     * @param productId 商品ID
     * @return 商品
     */
    @Override
    public ProductInfo selectProductInfoById(String productId)
    {
        return productInfoMapper.selectProductInfoById(productId);
    }

    /**
     * 查询商品列表
     *
     * @param productInfo 商品
     * @return 商品
     */
    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo)
    {
        return productInfoMapper.selectProductInfoList(productInfo);
    }

    /**
     * 新增商品
     *
     * @param productInfo 商品
     * @return 结果
     */
    @Override
    public int insertProductInfo(ProductInfo productInfo)
    {
        productInfo.setCreateTime(DateUtils.getNowDate());
        return productInfoMapper.insertProductInfo(productInfo);
    }

    /**
     * 修改商品
     *
     * @param productInfo 商品
     * @return 结果
     */
    @Override
    public int updateProductInfo(ProductInfo productInfo)
    {
        productInfo.setUpdateTime(DateUtils.getNowDate());
        return productInfoMapper.updateProductInfo(productInfo);
    }

    /**
     * 批量删除商品
     *
     * @param productIds 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteProductInfoByIds(String[] productIds)
    {
        return productInfoMapper.deleteProductInfoByIds(productIds);
    }

    /**
     * 删除商品信息
     *
     * @param productId 商品ID
     * @return 结果
     */
    @Override
    public int deleteProductInfoById(String productId)
    {
        return productInfoMapper.deleteProductInfoById(productId);
    }

    /**
     * 检验用户是否有权限操作
     * @param productInfo
     */
    @Override
    public void checkProductAllowed(ProductInfo productInfo) {
        int result=productInfoMapper.checkProductAllowed(productInfo);
        if (result<=0){
            throw new CustomException("不允许操作非权限数据");
        }
    }

    /**
     * 修改商品状态
     * @param productInfo
     * @return
     */
    @Override
    public int changeProductStatus(ProductInfo productInfo) {
        return productInfoMapper.updateProductInfo(productInfo);
    }
}
