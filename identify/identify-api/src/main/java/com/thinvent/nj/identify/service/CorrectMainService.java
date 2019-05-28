package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;

/**
* 补正变更服务
* @author xujc
* @date 2018-12-06
*/
public interface CorrectMainService extends CURDService<Correct, String> {
    /**
     * @author : xujc
     * @date :2018/12/13
     * @Description : 根据mainId获取Correct
     */
    Correct getByMainId(String mainId);

    /**
     * @author : xujc
     * @date :2018/12/18
     * @Description : 补正详情页的方法
     */
    Correct getById(String id);

    /**
     * @author : xujc
     * @date :2018/12/20
     * @Description : 补正审核
     */
     CorrectVerify getCheckByCorrectId(String correctId);

}
