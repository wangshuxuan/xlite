package com.kedacom.xlite.controller;

import com.kedacom.xlite.enums.ResultStatusEnum;
import com.kedacom.xlite.meeting.bean.User;
import com.kedacom.xlite.security.context.SessionUser;
import com.kedacom.xlite.security.context.UserContext;
import com.kedacom.xlite.service.XUserService;
import com.kedacom.xlite.vo.ResultVO;
import com.kedacom.xlite.vo.XUserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserManageController
 * @author wangshuxuan
 * @date 2020/4/2 9:20
 */
@RestController
@RequestMapping("/userManage")
public class UserManageController {

    @Resource
    private XUserService XUserService;

    /**
     * 检查Token
     */
    @GetMapping("/checkToken")
    public ResultVO checkToken() {
        XUserVO xUserVO = XUserService.checkToken();
        return new ResultVO<>(ResultStatusEnum.SUCCESS.getCode(), xUserVO);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getUserInformation")
    public ResultVO getUserInformation() {

        SessionUser sessionUser = (SessionUser) UserContext.getCurrentUser();
        XUserVO xUserVO = new XUserVO();
        xUserVO.setUserName(sessionUser.getName());
        xUserVO.setMoid(sessionUser.getMoid());
        xUserVO.setDepartment(sessionUser.getDepartment());
        xUserVO.setMobilephone(sessionUser.getMobile());
        xUserVO.setUserImg(sessionUser.getPortrait256());
        xUserVO.setToken(sessionUser.getToken());

        return new ResultVO<>(ResultStatusEnum.SUCCESS.getCode(), xUserVO);
    }
}
