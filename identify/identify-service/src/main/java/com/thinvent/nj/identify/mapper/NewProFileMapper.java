/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : NewProFileMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-16・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.NewProFile;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author panqh
 * @date 2018-11-16
 */
@Repository
public interface NewProFileMapper extends CURDMapper<NewProFile, String> {

    void insertNewProFileList(@Param("newProFileList") List<NewProFile> newProFileList);

    List<NewProFile> getNewProFileListByNewProjectId(String newProjectId);
}
