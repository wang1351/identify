/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : HouseMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-16・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.House;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author panqh
 * @date 2018-11-16
 */
@Repository
public interface HouseMapper extends CURDMapper<House, String> {

    /**
     * 根据鉴定业务主表ID获取房屋实体
     * @param mainId
     * @return
     */
    House getHouseByMainId(String mainId);

}
