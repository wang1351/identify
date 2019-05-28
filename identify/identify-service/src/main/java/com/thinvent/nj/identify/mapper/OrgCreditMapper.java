package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.identify.entity.OrgCreditFile;
import com.thinvent.nj.identify.entity.OrgCreditScore;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.OrgCredit;

/**
* 企业信用表 Mapper
* @author xujc
* @date 2018-11-12
*/
@Repository
public interface OrgCreditMapper extends CURDMapper<OrgCredit, String> {
    // 根据企业信用id获取企业信用评分集合

    List<OrgCreditScore> getListByOrgCreditId(String OrgCreditId);
    // 根据鉴定机构id和type（1是评分，2看历史）获取企业信用对象
    List<OrgCredit> getListByOrgIdAndType(@Param("orgId") String orgId, @Param("type") String type);
    // 根据企业信用id和企业信用评分类型获取企业信用评分集合
    List<OrgCreditScore> getByOrgCreditIdAndOrgScoreType(@Param("orgCreditId")String orgCreditId,@Param("orgScoreType")Integer  orgScoreType);

    // 根据积分得到等级
    CreditGrade getByScore(int score);

    // 添加org_credit_Score表
    void addOrgCreditScore(OrgCreditScore orgCreditScore);
    // 添加org_credit_File表
    void addOrgCreditFile(OrgCreditFile orgCreditFile);
    // 删除org_credit_Score表
    void deleteOrgCreditScore(String id);
    // 删除org_credit_File表
    void deleteOrgCreditFile(String id);
    // 根据你年份机构id、信用评分类型获取信用评分集合
    List<OrgCreditScore> getOrgCreditScoreList(Map map);
}
