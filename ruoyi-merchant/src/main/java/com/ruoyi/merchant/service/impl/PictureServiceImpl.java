package com.ruoyi.merchant.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.PictureMapper;
import com.ruoyi.merchant.domain.Picture;
import com.ruoyi.merchant.service.IPictureService;

/**
 * 轮播图Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-28
 */
@Service
public class PictureServiceImpl implements IPictureService 
{
    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 查询轮播图
     * 
     * @param picId 轮播图ID
     * @return 轮播图
     */
    @Override
    public Picture selectPictureById(Long picId)
    {
        return pictureMapper.selectPictureById(picId);
    }

    /**
     * 查询轮播图列表
     * 
     * @param picture 轮播图
     * @return 轮播图
     */
    @Override
    public List<Picture> selectPictureList(Picture picture)
    {
        return pictureMapper.selectPictureList(picture);
    }

    /**
     * 新增轮播图
     * 
     * @param picture 轮播图
     * @return 结果
     */
    @Override
    public int insertPicture(Picture picture)
    {
        return pictureMapper.insertPicture(picture);
    }

    /**
     * 修改轮播图
     * 
     * @param picture 轮播图
     * @return 结果
     */
    @Override
    public int updatePicture(Picture picture)
    {
        return pictureMapper.updatePicture(picture);
    }

    /**
     * 批量删除轮播图
     * 
     * @param picIds 需要删除的轮播图ID
     * @return 结果
     */
    @Override
    public int deletePictureByIds(Long[] picIds)
    {
        return pictureMapper.deletePictureByIds(picIds);
    }

    /**
     * 删除轮播图信息
     * 
     * @param picId 轮播图ID
     * @return 结果
     */
    @Override
    public int deletePictureById(Long picId)
    {
        return pictureMapper.deletePictureById(picId);
    }
}
