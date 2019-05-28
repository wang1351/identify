/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectUploadMapper.java
 * 功能概要       :
 * 做成日期       : 2018-12-20・xujc
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.CorrectUpload;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* 补正变更业务附件上传 Mapper
* @author xujc
* @date 2018-12-20
*/
@Repository
public interface CorrectUploadMapper extends CURDMapper<CorrectUpload, String> {
    public CorrectUpload getByCorrectId(String correctId);
}
