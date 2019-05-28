package com.thinvent.nj.identify.service;


import com.thinvent.nj.identify.entity.Expert;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* 鉴定专家服务
* @author xujc
* @date 2018-10-31
*/
public interface ExpertService extends CURDService<Expert, String>  {


    // 添加专家信息（包括专家基本信息和所属领域表信息）
    void addExpert(Map<String,Object> params) throws Exception;

    // 删除专家信息（包括专家基本信息和所属领域表信息）
    void deleteExpert(Expert expert);

    // 修改专家信息（包括专家基本信息和所属领域表信息）
    void updateExpert(String id,Map<String,Object> params) throws Exception;

}
