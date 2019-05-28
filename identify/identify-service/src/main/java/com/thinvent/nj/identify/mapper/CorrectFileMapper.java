/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectFileMapper.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.CorrectFile;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 补正变更业务附件 Mapper
* @author panqh
* @date 2018-12-05
*/
@Repository
public interface CorrectFileMapper extends CURDMapper<CorrectFile, String> {

    /**
     * 批量插入补正变更附件信息
     * @author panqh
     * @date 2018-12-05
     * @param correctFileList
     */
    void insertCorrectFileList(@Param("correctFileList") List<CorrectFile> correctFileList);

}
