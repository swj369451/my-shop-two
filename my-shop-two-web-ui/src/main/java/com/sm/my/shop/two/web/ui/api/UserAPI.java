package com.sm.my.shop.two.web.ui.api;

import com.sm.my.shop.two.commons.utils.HttpClientUtils;
import com.sm.my.shop.two.commons.utils.MapperUtils;
import com.sm.my.shop.two.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class UserAPI {

    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        params.add(new BasicNameValuePair("email",tbUser.getEmail()));
        params.add(new BasicNameValuePair("phone",tbUser.getPhone()));
        String json = HttpClientUtils.post(API.USER_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2PojoByTree(json, "data", TbUser.class);
        return user;
    }
}
