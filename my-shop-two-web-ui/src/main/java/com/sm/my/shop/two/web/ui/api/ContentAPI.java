package com.sm.my.shop.two.web.ui.api;

import com.sm.my.shop.two.commons.utils.HttpClientUtils;
import com.sm.my.shop.two.commons.utils.MapperUtils;
import com.sm.my.shop.two.web.ui.dto.TbContent;

import java.util.List;

public class ContentAPI {
    /**
     * 请求广告内容ppt
     * @return
     * @throws Exception
     */
    public static List<TbContent> ppt(){
        List<TbContent> tbContents = null;
        try {
            String result = HttpClientUtils.get(API.CONTENT+"120");
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
