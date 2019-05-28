/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewService.java
 * 功能概要       :
 * 做成日期       : 2018-11-27・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.ExpertAppoint;
import com.thinvent.nj.identify.entity.ExpertBusinessArea;
import com.thinvent.nj.identify.entity.Review;
import com.thinvent.nj.identify.entity.ReviewExpert;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* 复核业务实体服务
* @author panqh
* @date 2018-11-27
*/
public interface ReviewService extends CURDService<Review, String> {

    /**
     * 保存复核业务申请信息
     * @author panqh
     * @date 2018-11-28
     * @param mainId：鉴定主表ID
     * @param params：需要保存的参数
     */
     void saveReviewRequestInfo(String mainId, Map<String,Object> params);

    /**
     * 根据复核主键和专家领域获取专家领域列表
     * @author panqh
     * @date 2018-11-29
     * @param reviewId
     * @return
     */
     List<ExpertBusinessArea> getExpertList(String reviewId, String field);

    /**
     * 根据复核业务主键和专家领域获取指定专家实体
     * @author panqh
     * @date 2018-12-04
     * @param reviewId
     * @param field
     * @return
     */
    ExpertAppoint getExpertAppointByReviewIdAndField(String reviewId, String field);

    /**
     * 根据复核主键获取随机专家列表
     * @author panqh
     * @date 2018-11-29
     * @return map (key: expertField, value: expertId List)
     */
     Map<Integer, List<String>> getRandomExpertListByCondition(Map<String,Object> map);

    /**
     * 选择专家保存前的验证
     * @author panqh
     * @date 2018-12-03
     * @param params
     * @return
     */
    String validatorReviewExpert(Map<String, Object> params);

    /**
     * 保存复核业务选择专家信息
     * @author panqh
     * @date 2018-12-03
     * @param params：需要保存的参数
     */
    void saveReviewExpertInfo(Map<String, Object> params);


    /**
     * @author : xujc
     * @date :2019/1/2
     * @Description : 保存确认专家信息
     */
   void saveReviewConfirmExpertInfo(Map<String, Object> params);

    /**
     * @author : xujc
     * @date :2018/12/25
     * @Description : 保存复核申请审核信息
     */
    void saveReviewApplyCheckInfo(Map<String, Object> params);

    /**
     * 保存复核业务专家意见信息
     * @author panqh
     * @date 2018-12-04
     * @param params：需要保存的参数
     */
    void saveReviewOpinionInfo(Map<String, Object> params);

    /**
     * 保存复核业务办结信息
     * @author panqh
     * @date 2018-12-04
     * @param params：需要保存的参数
     */
    void saveReviewSendInfo(Map<String, Object> params);


    List<String> getExpertsByField(Integer field);

    List<Map<String, Object>> getAutoChecked(String str);

    List<ExpertAppoint> getExpertAppointsByReviewId(Map<String,Object> map);
    /**
     * @author : xujc
     * @date :2018/12/28
     * @Description : 获取选择专家信息
     */
    List<Map<String,Object>> getReviewExpertsByCondition(Map<String,Object> map);
}
