package com.thinvent.nj.identify.service;


import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* ReviewMain服务
* @author xujc
* @date 2018-11-13
*/
public interface ReviewMainService extends CURDService<Review, String> {

    /**
     * @author : xujc
     * @date :2018/12/4
     * @Description : 根据复核表id得到选择专家集合
     */
    List<Map<String,Object>> getReviewExpertsByCondition(Map<String,Object> map);

    /**
     * @author : xujc
     * @date :2019/1/4
     * @Description : 根据复核id获取专家意见对象
     */
    ReviewOpinion getReviewOpinionByReviewId(Map<String,Object> map);
    /**
     * @author : xujc
     * @date :2018/12/4
     * @Description : 根据复核id获取复核发放对象
     */
    ReviewSend getReviewSendByReviewId(String reviewId);

    /**
     * @author : xujc
     * @date :2018/12/13
     * @Description : 根据mainId获取review
     */
    Review getByMainId(String mainId);
    /**
     * @author : xujc
     * @date :2019/1/4
     * @Description : 获取复核申请信息
     */
    Review getById(String id);

    ReviewAccept getByReviewId(String id);


}
