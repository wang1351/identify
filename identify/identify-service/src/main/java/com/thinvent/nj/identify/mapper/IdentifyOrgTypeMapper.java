/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : IdentifyOrgTypeMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-01・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.IdentifyOrgType;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 鉴定机构鉴定业务类型Mapper
 * @author panqh
 * @date 2018-11-01
 */
@Repository
public interface IdentifyOrgTypeMapper extends CURDMapper<IdentifyOrgType, String> {



    //add by wangwj 20190306 start
    /**
     * 根据鉴定机构ID 获取该鉴定机构的鉴定机构类型
     */
    List<IdentifyOrgType> getIdentifyOrgTypeListByMainId(String identifyMainId);
    //add by wangwj 20190306 end
}
