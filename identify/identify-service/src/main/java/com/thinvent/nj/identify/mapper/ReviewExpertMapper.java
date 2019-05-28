/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewExpertMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-28・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.ReviewExpert;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 复核业务专家 Mapper
* @author panqh
* @date 2018-11-28
*/
@Repository
public interface ReviewExpertMapper extends CURDMapper<ReviewExpert, String> {






}
