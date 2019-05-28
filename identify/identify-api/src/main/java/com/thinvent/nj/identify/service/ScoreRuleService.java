/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ScoreRuleService.java
 * 功能概要       :
 * 做成日期       : 2018-11-06・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.ScoreRule;
import com.thinvent.nj.identify.entity.ScoreRuleStandard;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;

/**
 * 评分细则服务
 * @author panqh
 * @date 2018-11-06
 */
public interface ScoreRuleService extends CURDService<ScoreRule, String> {

    /**
     * 根据评分类型获取评分细则列表
     * @author panqh
     * @date 2018-11-06
     * @param scoreType
     * @return
     */
    List<ScoreRuleStandard> getScoreRuleStandardListByScoreType(String scoreType);

    /**
     * 根据评分细则评分标准主键（ID）获取实体类
     * @author panqh
     * @date 2018-11-07
     * @param id
     * @return
     */
    ScoreRuleStandard getScoreRuleStandardById(String id);

    /**
     * 保存评分细则评分标准信息
     * @author panqh
     * @date 2018-11-07
     * @param scoreRuleStandard
     */
    void saveScoreValue(ScoreRuleStandard scoreRuleStandard);

}
