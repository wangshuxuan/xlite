package com.kedacom.xlite.service;

import com.kedacom.xlite.model.XUser;
import com.kedacom.xlite.vo.XUserVO;

/**
 * UserService
 * @author wangshuxuan
 * @date 2020/4/2 9:59
 */
public interface XUserService {

    /**
     * 返回用户信息和token
     * @return
     */
    XUserVO checkToken();

    /**
     * 根据moid 获取
     * @param moid
     * @return
     */
    XUser findUserByMoid(String moid);

    /**
     * 插入XUser
     * @param user
     */
    void saveUser(XUser user);
}
