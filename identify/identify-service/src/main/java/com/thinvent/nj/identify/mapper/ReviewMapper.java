/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-27・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.Review;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* 复核业务实体 Mapper
* @author panqh
* @date 2018-11-27
*/
@Repository
public interface ReviewMapper extends CURDMapper<Review, String> {
    Review getByMainId(String mainId);
}
