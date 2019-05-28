package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.Expert;
import com.thinvent.nj.identify.entity.ExpertBusinessArea;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 鉴定专家 Mapper
* @author xujc
* @date 2018-10-31
*/
@Repository
public interface ExpertMapper extends CURDMapper<Expert, String> {



    // 添加专家业务领域
    void addExpertBusinessArea(ExpertBusinessArea expertBusinessArea);
    // 删除专家业务领域
    void deleteExpertBusinessArea(ExpertBusinessArea expertBusinessArea);

    /**
     * 根据领域获取专家领域列表
     * @author panqh
     * @date 2018-11-29
     * @param params
     * @return
     */
    List<ExpertBusinessArea> getExpertFieldByField(Map<String, Object> params);
    String getFieldNameByField(String field);
}
