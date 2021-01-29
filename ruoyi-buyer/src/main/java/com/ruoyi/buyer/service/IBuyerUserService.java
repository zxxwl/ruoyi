package com.ruoyi.buyer.service;

import java.util.List;
import com.ruoyi.buyer.domain.BuyerUser;

/**
 * 用户信息Service接口
 *
 * @author ztt
 * @date 2021-01-25
 */
public interface IBuyerUserService
{
    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    public BuyerUser selectBuyerUserById(Long id);

    /**
     * 查询用户信息列表
     *
     * @param buyerUser 用户信息
     * @return 用户信息集合
     */
    public List<BuyerUser> selectBuyerUserList(BuyerUser buyerUser);

    /**
     * 新增用户信息
     *
     * @param buyerUser 用户信息
     * @return 结果
     */
    public int insertBuyerUser(BuyerUser buyerUser);

    /**
     * 修改用户信息
     *
     * @param buyerUser 用户信息
     * @return 结果
     */
    public int updateBuyerUser(BuyerUser buyerUser);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户信息ID
     * @return 结果
     */
    public int deleteBuyerUserByIds(Long[] ids);

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteBuyerUserById(Long id);

    /**
     * 通过openid查询
     * @param openid
     * @return
     */
    int selectBuyerUserByOpenId(String openid);

    /**
     * 通过openid修改用户信息
     * @param buyerUser
     * @return
     */
    int updateBuyerByOpenid(BuyerUser buyerUser);
}
