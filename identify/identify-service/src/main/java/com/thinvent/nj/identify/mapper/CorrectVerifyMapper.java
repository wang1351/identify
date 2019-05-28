/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectVerifyMapper.java
 * 功能概要       :
 * 做成日期       : 2018-12-17・xujc
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.CorrectFile;
import com.thinvent.nj.identify.entity.CorrectVerify;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : xujc
 * @date :2018/12/17
 * @Description : CorrectVerifyMapper 补正审核
 */
@Repository
public interface CorrectVerifyMapper extends CURDMapper<CorrectVerify, String> {

    /**
     * @author : xujc
     * @date :2018/12/20
     * @Description : 补正审核
     */
    CorrectVerify getCheckByCorrectId(String correctId);

}
