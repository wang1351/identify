/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ClientFileMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-16・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.ClientFile;
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
public interface ClientFileMapper extends CURDMapper<ClientFile, String> {

    void insertClientFileList(@Param("clientFileList") List<ClientFile> clientFileList);

    /**
     * 根据委托人表主键获取委托人附件列表
     * @author panqh
     * @date 2018-11-20
     * @param clientId
     * @return
     */
     List<ClientFile> getClientFileList(String clientId);
}
