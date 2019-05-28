/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ExpertAppointMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-28・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.ExpertAppoint;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 复核业务指定专家 Mapper
* @author panqh
* @date 2018-11-28
*/
@Repository
public interface ExpertAppointMapper extends CURDMapper<ExpertAppoint, String> {

    /**
     * 根据外键获取指定专家列表
     * @author panqh
     * @date 2018-11-29
     * @param reviewId
     * @return
     */
    List<ExpertAppoint> getAppointExpertListByReviewId(String reviewId);

    /**
     * 批量插入指定专家表
     * @author panqh
     * @date 2018-11-28
     * @param expertAppointList
     */
    void insertExpertAppointList(@Param("expertAppointList") List<ExpertAppoint> expertAppointList);

    /**
     * 根据复审主键和领域获取指定专家实体
     * @author panqh
     * @date 2018-12-04
     * @param reviewId
     * @param expertField
     * @return
     */
    ExpertAppoint getExpertAppoint(@Param("reviewId") String reviewId, @Param("expertField") String expertField);

}
