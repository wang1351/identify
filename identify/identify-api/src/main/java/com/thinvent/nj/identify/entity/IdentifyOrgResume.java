package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 工作简历表
* @author wangwj
* @date 2019-3-4
*/
  //add by wangwj 20190304 start
public class IdentifyOrgResume extends BaseOracleEntity {

    // 人员ID
    private String personId;

    // 序号
    private Integer seq;

    // 在何单位、从事何工作、任何职
    private String workWhere;

    // 由何年何月至何年何月
    private String workWhen;



    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getWorkWhere() {
        return workWhere;
    }

    public void setWorkWhere(String workWhere) {
        this.workWhere = workWhere;
    }

    public String getWorkWhen() {
        return workWhen;
    }

    public void setWorkWhen(String workWhen) {
        this.workWhen = workWhen;
    }
}
  //add by wangwj 20190304 end