/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ScoreRuleServiceImpl.java
 * 功能概要       :
 * 做成日期       : 2018-11-06・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.ScoreRule;
import com.thinvent.nj.identify.entity.ScoreRuleStandard;
import com.thinvent.nj.identify.mapper.ScoreRuleMapper;
import com.thinvent.nj.identify.mapper.ScoreRuleStandardMapper;
import com.thinvent.nj.identify.service.ScoreRuleService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 评分细则服务实现
* @author panqh
* @date 2018-11-06
*/
@Service
public class ScoreRuleServiceImpl extends BaseCURDServiceImpl<ScoreRule, String> implements ScoreRuleService {

    @Autowired
    private ScoreRuleMapper scoreRuleMapper;
    @Autowired
    private ScoreRuleStandardMapper scoreRuleStandardMapper;

    /**
     * 根据评分类型获取评分细则列表
     * @author panqh
     * @date 2018-11-06
     * @param scoreType
     * @return
     */
    @Override
    public List<ScoreRuleStandard> getScoreRuleStandardListByScoreType(String scoreType) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("scoreType", Integer.valueOf(scoreType));
        return scoreRuleStandardMapper.findList(params);
    }

    /**
     * 根据评分细则评分标准主键（ID）获取实体类
     * @author panqh
     * @date 2018-11-07
     * @param id
     * @return
     */
    @Override
    public ScoreRuleStandard getScoreRuleStandardById(String id) {
        return scoreRuleStandardMapper.get(id);
    }

    /**
     * 保存评分细则评分标准信息
     * @author panqh
     * @date 2018-11-07
     * @param scoreRuleStandard
     */
    @Override
    public void saveScoreValue(ScoreRuleStandard scoreRuleStandard) {
        scoreRuleStandardMapper.update(scoreRuleStandard);
    }

}
