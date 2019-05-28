package com.thinvent.nj.identify.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.entity.Expert;
import com.thinvent.nj.identify.entity.ExpertBusinessArea;
import com.thinvent.nj.identify.mapper.ExpertMapper;
import com.thinvent.nj.identify.service.ExpertService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author  xujc
 * @date  2018/11/30
 * @Description 专家实现服务类
 */
@Service
public class ExpertServiceImpl extends BaseCURDServiceImpl<Expert,String> implements ExpertService {
    @Autowired
    private ExpertMapper expertMapper;


    /**
     * @author : xujc
     * @date :2018/11/8
     * @Description : 添加专家（包括专家业务领域）
     */
    @CacheEvict(cacheNames = "expert", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addExpert(Map<String, Object> params) throws Exception {
        // 取出身份证号
        String identifiedCode = (String) params.get("identifiedCode");
        // 截取生日部分
        String birth = identifiedCode.substring(6,10) + "-" + identifiedCode.substring(10,12)+
                "-" + identifiedCode.substring(12,14);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birth);
        params.put("birthday",birthday);

        Expert target = new Expert();
        MapperUtil.copy(params, target);
        insert(target);

        // 在专家业务领域表添加
       JSONArray expertBusinessAreas = (JSONArray) params.get("expertBusinessArea");

       String expertId = target.getId();
        addExpertBusinessAreas(expertBusinessAreas,expertId);

    }
    /**
     * @author : xujc
     * @date :2018/11/9
     * @Description : 删除专家信息包括所属领域
     */
    @CacheEvict(cacheNames = "expert", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteExpert(Expert expert) {
        expertMapper.deleteLogic(expert);
        for (ExpertBusinessArea expertBusinessArea: expert.getList()) {
            expertMapper.deleteExpertBusinessArea(expertBusinessArea);
        }
    }

    /**
     * @author : xujc
     * @date :2018/11/9
     * @Description : 修改专家信息（包括所属领域以及单独的状态修改）
     */
    @CacheEvict(cacheNames = "expert", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateExpert(String id,Map<String,Object> map) {
        Expert expert = get(id);
        // 得到专家领域集合，里面是领域value
        JSONArray expertBusinessAreas = (JSONArray) map.get("expertBusinessArea");

        // 修改状态不需要改领域
        if(expertBusinessAreas !=null){
            // 所属领域的修改原则是先删除再添加,以下是删除操作
            for (ExpertBusinessArea expertBusinessArea: expert.getList()) {
                expertMapper.deleteExpertBusinessArea(expertBusinessArea);
            }
            // 在专家业务领域表添加
            addExpertBusinessAreas(expertBusinessAreas,id);
        }


        MapperUtil.copy(map, expert);
        update(expert);



    }

    /**
    * @author : xujc
    * @date :2018/11/8
    * @Description : 添加专家的业务领域
    * @param :   expertBusinessAreas 业务领域value集合
    * @param :    expertId 专家id
    */
    public void addExpertBusinessAreas(JSONArray expertBusinessAreas, String expertId) {

        for (int i = 0;i < expertBusinessAreas.size(); i++) {
            ExpertBusinessArea expertBusinessArea =new ExpertBusinessArea();
            expertBusinessArea.setExpertId(expertId);
            expertBusinessArea.setExpertField(Integer.parseInt((String)expertBusinessAreas.get(i)));
            expertMapper.addExpertBusinessArea(expertBusinessArea);
        }

    }
}
