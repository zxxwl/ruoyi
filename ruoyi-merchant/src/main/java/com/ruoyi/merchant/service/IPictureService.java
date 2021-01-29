package com.ruoyi.merchant.service;

import java.util.List;
import com.ruoyi.merchant.domain.Picture;

/**
 * 轮播图Service接口
 * 
 * @author ruoyi
 * @date 2021-01-28
 */
public interface IPictureService 
{
    /**
     * 查询轮播图
     * 
     * @param picId 轮播图ID
     * @return 轮播图
     */
    public Picture selectPictureById(Long picId);

    /**
     * 查询轮播图列表
     * 
     * @param picture 轮播图
     * @return 轮播图集合
     */
    public List<Picture> selectPictureList(Picture picture);

    /**
     * 新增轮播图
     * 
     * @param picture 轮播图
     * @return 结果
     */
    public int insertPicture(Picture picture);

    /**
     * 修改轮播图
     * 
     * @param picture 轮播图
     * @return 结果
     */
    public int updatePicture(Picture picture);

    /**
     * 批量删除轮播图
     * 
     * @param picIds 需要删除的轮播图ID
     * @return 结果
     */
    public int deletePictureByIds(Long[] picIds);

    /**
     * 删除轮播图信息
     * 
     * @param picId 轮播图ID
     * @return 结果
     */
    public int deletePictureById(Long picId);
}
