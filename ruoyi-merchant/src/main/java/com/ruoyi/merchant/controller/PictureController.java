package com.ruoyi.merchant.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.merchant.domain.Picture;
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
import com.ruoyi.merchant.service.IPictureService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 轮播图Controller
 *
 * @author ruoyi
 * @date 2021-01-28
 */
@RestController
@RequestMapping("/merchant/picture")
public class PictureController extends BaseController
{
    @Autowired
    private IPictureService pictureService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询轮播图列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:picture:list')")
    @GetMapping("/list")
    public TableDataInfo list(Picture picture)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        picture.setSellerId(loginUser.getUser().getUserId());
        List<Picture> list = pictureService.selectPictureList(picture);
        return getDataTable(list);
    }
    /**
     * wx查询轮播图列表
     */
    @GetMapping("/wxList")
    public AjaxResult wxList(Picture picture)
    {
        List<Picture> list = pictureService.selectPictureList(picture);
        return AjaxResult.success(list);
    }

    /**
     * 导出轮播图列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:picture:export')")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Picture picture)
    {
        List<Picture> list = pictureService.selectPictureList(picture);
        ExcelUtil<Picture> util = new ExcelUtil<Picture>(Picture.class);
        return util.exportExcel(list, "merchant");
    }

    /**
     * 获取轮播图详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:picture:query')")
    @GetMapping(value = "/{picId}")
    public AjaxResult getInfo(@PathVariable("picId") Long picId)
    {
        return AjaxResult.success(pictureService.selectPictureById(picId));
    }

    /**
     * 新增轮播图
     */
    @PreAuthorize("@ss.hasPermi('merchant:picture:add')")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Picture picture)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        picture.setSellerId(user.getUserId());
        return toAjax(pictureService.insertPicture(picture));
    }

    /**
     * 修改轮播图
     */
    @PreAuthorize("@ss.hasPermi('merchant:picture:edit')")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Picture picture)
    {
        return toAjax(pictureService.updatePicture(picture));
    }

    /**
     * 删除轮播图
     */
    @PreAuthorize("@ss.hasPermi('merchant:picture:remove')")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{picIds}")
    public AjaxResult remove(@PathVariable Long[] picIds)
    {
        return toAjax(pictureService.deletePictureByIds(picIds));
    }
}
