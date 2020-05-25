package com.kedacom.xlite.service;

import com.kedacom.xlite.dao.XUserDao;
import com.kedacom.xlite.model.XUser;
import com.kedacom.xlite.security.context.SessionUser;
import com.kedacom.xlite.security.context.UserContext;
import com.kedacom.xlite.vo.XUserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 * @author wangshuxuan
 * @date 2020/4/2 10:01
 */
@Service
@Transactional
public class XUserServiceImpl implements XUserService {

    @Resource
    private XUserDao xUserDao;

    /**
     * 返回XUserVO
     * @return
     */
    @Override
    public XUserVO checkToken() {
        //security 获取sessionUser
        SessionUser user = (SessionUser) UserContext.getCurrentUser();

        XUserVO xUserVO = new XUserVO();
        xUserVO.setUserName(user.getName());
        xUserVO.setMoid(user.getMoid());
        xUserVO.setDepartment(user.getDepartment());
        xUserVO.setMobilephone(user.getMobile());
        xUserVO.setUserImg(user.getPortrait256());
        xUserVO.setEmail(user.getEmail());
        xUserVO.setToken(user.getToken());
        //转换
        return xUserVO;
    }

    /**
     * 根据moid 查询user
     * @param moid
     * @return
     */
    @Override
    public XUser findUserByMoid(String moid) {
        return xUserDao.findUserByMoid(moid);
    }

    /**
     * 插入user
     * @param user
     */
    @Override
    public void saveUser(XUser user) {
        xUserDao.saveUser(user);
    }

}
