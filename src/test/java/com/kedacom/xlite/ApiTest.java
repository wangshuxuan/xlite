package com.kedacom.xlite;

import com.kedacom.xlite.meeting.api.adapter.ApiAdapter;
import com.kedacom.xlite.meeting.api.result.ValidationTokenResult;
import com.kedacom.xlite.meeting.bean.AccountInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author wangshuxuan
 * @date 2020/4/3 10:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ApiTest {

    @Resource
    private ApiAdapter apiAdapter;

    @Before
    public void init() {

    }

    @Test
    public void test() {
        String username = "wangshuxuan@kedacom.com";
        String password = "w88888888";
        String rememberToken = apiAdapter.api5Login(username, password, true);
        System.out.println(rememberToken);

        //ValidationTokenResult validationTokenResult = apiAdapter.validationToken(rememberToken);
        //System.out.println(validationTokenResult.getMoid());
        //String cookie = "SSO_REMEMBER_ME_COOKIE_KEY=" + rememberToken;

        //AccountInfo accountInfo1 = apiAdapter.getAccountInfo1("wangshuxuan@kedacom.com", cookie);
        //System.out.println(accountInfo1.getAccount());
        apiAdapter.keepHeartbeat(rememberToken);

    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
