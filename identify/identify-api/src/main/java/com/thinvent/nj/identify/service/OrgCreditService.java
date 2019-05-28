package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.identify.entity.OrgCreditScore;
import com.thinvent.nj.mybatis.service.CURDService;
import com.thinvent.nj.identify.entity.OrgCredit;

import java.util.List;
import java.util.Map;

/**
* 企业信用表服务
* @author xujc
* @date 2018-11-12
*/
public interface OrgCreditService extends CURDService<OrgCredit, String> {



    // 根据鉴定机构id和type（1是评分，2看历史）获取企业信用对象
    List<OrgCredit> getListByOrgIdAndType(String orgId,String type);
    // 保存操作
    void  save(Map<String,Object> map);
    // 根据企业信用id和企业信用评分类型获取企业信用评分集合
    List<OrgCreditScore> getByOrgCreditIdAndOrgScoreType(String orgCreditId,Integer  orgScoreType);
    // 根据积分得到等级
    CreditGrade getByScore(int score);

    // 根据你年份机构id、信用评分类型获取信用评分集合
    List<OrgCreditScore> getOrgCreditScoreList(Map map);


}
