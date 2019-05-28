/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ScoreRuleMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-06・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.ScoreRule;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
 * 评分细则Mapper
 * @author panqh
 * @date 2018-11-06
 */
@Repository
public interface ScoreRuleMapper extends CURDMapper<ScoreRule, String> {

}
