/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ClientMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-16・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.Client;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author panqh
 * @date 2018-11-16
 */
@Repository
public interface ClientMapper extends CURDMapper<Client, String> {

    /**
     * 根据安鉴定业务主键获取委托人实体
     * @author panqh
     * @date 2018-11-20
     * @param mainId
     * @return
     */
     Client getClientByMainId(String mainId);

}
