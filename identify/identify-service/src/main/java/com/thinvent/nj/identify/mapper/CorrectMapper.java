/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectMapper.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* 补正业务表 Mapper
* @author panqh
* @date 2018-12-05
*/
@Repository
public interface CorrectMapper extends CURDMapper<Correct, String> {
    Correct getByMainId(String mainId);
}
