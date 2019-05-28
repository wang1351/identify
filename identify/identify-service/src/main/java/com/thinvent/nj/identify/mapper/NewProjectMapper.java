package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.NewProject;

/**
* 房屋新建工程实体 Mapper
* @author panqh
* @date 2018-11-13
*/
@Repository
public interface NewProjectMapper extends CURDMapper<NewProject, String> {
    /**
     * 根据安鉴定业务主键获取委托人实体
     * @author wangwj
     * @date 2018-11-20
     * @param mainId
     * @return
     */
    /*NewProject getNewProjectByMainId(String mainId);*/
}
