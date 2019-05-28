/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : OpinionFileMapper.java
 * 功能概要       :
 * 做成日期       : 2018-12-04・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.OpinionFile;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 复审业务专家意见附件 Mapper
* @author panqh
* @date 2018-12-04
*/
@Repository
public interface OpinionFileMapper extends CURDMapper<OpinionFile, String> {

    /**
     * 批量插入专家意见附件列表信息
     * @author panqh
     * @date 2018-12-04
     * @param opinionFileList
     */
    void insertOpinionFileList(@Param("opinionFileList") List<OpinionFile> opinionFileList);

}
