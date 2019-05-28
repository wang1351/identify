/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewSendMapper.java
 * 功能概要       :
 * 做成日期       : 2018-12-04・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.ReviewOpinion;
import com.thinvent.nj.identify.entity.ReviewSend;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* 复核业务发送 Mapper
* @author panqh
* @date 2018-12-04
*/
@Repository
public interface ReviewOpinionMapper extends CURDMapper<ReviewOpinion, String> {


}
