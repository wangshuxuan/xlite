package com.kedacom.xlite.dao;

import com.kedacom.xlite.model.XUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * XUserDao
 * @author wangshuxuan
 * @date 2020/4/2 14:53
 */
@Repository
public class XUserDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<XUser> xUserRowMapper;

    static {
        xUserRowMapper = (resultSet, i) -> {
            XUser xUser = new XUser();
            xUser.setMoid(resultSet.getString("moid"));
            xUser.setOpenId(resultSet.getString("open_id"));
            return xUser;
        };
    }

    /**
     * 根据moid 查询用户
     * @param moid
     * @return
     */
    public XUser findUserByMoid(String moid) {
        String sql = "select * from x_user where moid = ?";
        try {
            logger.debug("findUserByMoid:{}", sql);
            return jdbcTemplate.queryForObject(sql, xUserRowMapper, moid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 查询user
     * @param user
     */
    public void saveUser(XUser user) {
        String sql = "insert into x_user (moid, open_id) value (?, ?)";
        logger.debug("saveUser:{}", sql);
        jdbcTemplate.update(sql, user.getMoid(), user.getOpenId());
    }
}
