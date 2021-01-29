package com.ruoyi.buyer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.buyer.mapper.BuyerUserMapper;
import com.ruoyi.buyer.domain.BuyerUser;
import com.ruoyi.buyer.service.IBuyerUserService;

/**
 * 用户信息Service业务层处理
 *
 * @author ztt
 * @date 2021-01-25
 */
@Service
public class BuyerUserServiceImpl implements IBuyerUserService
{
    @Autowired
    private BuyerUserMapper buyerUserMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    @Override
    public BuyerUser selectBuyerUserById(Long id)
    {
        return buyerUserMapper.selectBuyerUserById(id);
    }

    /**
     * 查询用户信息列表
     *
     * @param buyerUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<BuyerUser> selectBuyerUserList(BuyerUser buyerUser)
    {
        return buyerUserMapper.selectBuyerUserList(buyerUser);
    }

    /**
     * 新增用户信息
     *
     * @param buyerUser 用户信息
     * @return 结果
     */
    @Override
    public int insertBuyerUser(BuyerUser buyerUser)
    {
        buyerUser.setCreateTime(DateUtils.getNowDate());
        return buyerUserMapper.insertBuyerUser(buyerUser);
    }

    /**
     * 修改用户信息
     *
     * @param buyerUser 用户信息
     * @return 结果
     */
    @Override
    public int updateBuyerUser(BuyerUser buyerUser)
    {
        buyerUser.setUpdateTime(DateUtils.getNowDate());
        return buyerUserMapper.updateBuyerUser(buyerUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户信息ID
     * @return 结果
     */
    @Override
    public int deleteBuyerUserByIds(Long[] ids)
    {
        return buyerUserMapper.deleteBuyerUserByIds(ids);
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteBuyerUserById(Long id)
    {
        return buyerUserMapper.deleteBuyerUserById(id);
    }

    /**
     * 通过openid查询微信用户信息
     * @param openid
     * @return
     */
    @Override
    public int selectBuyerUserByOpenId(String openid) {
        return buyerUserMapper.selectBuyerUserByOpenId(openid);
    }

    /**
     * 通过openid修改用户信息
     * @param buyerUser
     * @return
     */
    @Override
    public int updateBuyerByOpenid(BuyerUser buyerUser) {
        return buyerUserMapper.updateBuyerByOpenid(buyerUser);
    }
}
