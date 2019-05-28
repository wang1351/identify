package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.IdentifyOrgMain;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_MAIN Mapper
* @author administrator
* @date 2018-11-13
*/
@Repository
public interface IdentifyMainMapper extends CURDMapper<IdentifyMain, String> {

    /**
     * 获取鉴定业务主表中最大的编号
     * @author panqh
     * @date 2018-11-13
     * @param formatterDate
     * @return
     */
    String getMaxCaseNo(String formatterDate);

    /**
     * 通过ID查询房屋鉴定委托书
     * @author wangwj
     * @param id
     * @return
     */

    List<Map<Object,Object>> getType(String id);

    /**
     * 更新业务主表状态
     * @param status
     */
    void updateMainStatus(@Param("id") String id, @Param("status") Integer status);

    /**
     * 更新主表流程状态
     * @param id
     * @param processStatus
     */
    void updateMainProcessStatus(@Param("id") String id, @Param("processStatus") Integer processStatus);

}
