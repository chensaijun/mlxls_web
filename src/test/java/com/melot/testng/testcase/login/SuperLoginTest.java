package com.melot.testng.testcase.login;

import com.melot.testng.constants.Constants;
import com.melot.testng.data.params.LoginParserBean;
import com.melot.testng.utils.LoginUtils;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: chensaijun
 * @Date: 2022/4/2
 **/

public class SuperLoginTest {
    @Test(dataProvider = "datas")
    @Description("登录接口")
    public static void superLogin(String username, String password, String p) throws IOException {
        LoginParserBean loginParserBean = LoginUtils.loginResquest(username, password, p);
        assert loginParserBean.isSuccess();

    }

    @DataProvider(name = "datas")
    public static Object[][] datas() {
        Object[][] datas = new Object[][]{
                new Object[]{Constants.getName(), Constants.getPassword(), Constants.getP()}
        };
        return datas;
    }
}
