package com.thinvent.nj.identify.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.identify.entity.OrgCredit;
import com.thinvent.nj.identify.entity.OrgCreditFile;
import com.thinvent.nj.identify.entity.OrgCreditScore;
import com.thinvent.nj.identify.mapper.OrgCreditMapper;
import com.thinvent.nj.identify.service.OrgCreditService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业信用表服务实现
 *
 * @author xujc
 * @date 2018-11-12
 */
@Service
public class OrgCreditServiceImpl extends BaseCURDServiceImpl<OrgCredit, String> implements OrgCreditService {

    @Autowired
    private OrgCreditMapper orgCreditMapper;
    /**
     * @author : xujc
     * @date :2019/1/9
     * @Description : orgId 机构id；type类型；status 状态
     */
    @Override
    public List<OrgCredit> getListByOrgIdAndType(String orgId, String type) {
        return orgCreditMapper.getListByOrgIdAndType(orgId, type);
    }

    @CacheEvict(cacheNames = "expert", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Map<String, Object> map) {
        // update by xujc 2019/3/18 start
        // 获取企业信用id
        String orgCreditId = (String) map.get("orgCreditId");
        // 这个是 1 评分 ，3 上传资料
        String type = (String) map.get("type");
        // 获取要保存的企业信用评分对象集合
        JSONArray array = (JSONArray) map.get("orgCreditScores");
        List<OrgCreditScore> orgCreditScores = JSONObject.parseArray(array.toJSONString(), OrgCreditScore.class);
        if(orgCreditScores.size() == 1){
            OrgCreditScore orgCreditScore = orgCreditScores.get(0);
            if(orgCreditScore.getOrgScoreType() == 0){
                return;
            }
        }

        // 获取机构id
        String identifyOrgId = (String) map.get("identifyOrgId");
        // 定义个变量用来存企业信用的总分
        int totalScore = (Integer) map.get("totalScore");
        //update by wangwj 20190111 start
        OrgCredit orgCredit = new OrgCredit();
        //update by wangwj 20190111 end
        // 用来判断是不是D级
        boolean flag = false;
        // 根据企业信用id的有无来判断企业信用表的增改
        // 如果企业信用id无，所有都是新增操作
        if ("".equals(orgCreditId)) {

                for (OrgCreditScore obj : orgCreditScores) {
                    // 如果保存的是基础信息
                    if (obj.getOrgScoreType() == 1) {
                        // 如果保存的总分是0，而且数量是1
                        if (obj.getScoreStandardTotal() == 0 && obj.getScoreStandardNum() ==1) {
                            flag = true;
                        }
                    }
                }

                // 根据评分找到等级
                CreditGrade byScore = getByScore(totalScore);
                //执行添加，先添加企业信用表，在添加企业信用评分表，然后是附件表（顺序不能乱）

                if (flag) {
                    orgCredit.setCreditGradeName("D");
                } else {
                    orgCredit.setCreditGradeName(byScore.getName());
                }

                orgCredit.setCreditGradeScore(totalScore);


            orgCredit.setIdentifyOrgId(identifyOrgId);

            orgCredit.setScoreYear(new Date());

            orgCreditMapper.insert(orgCredit);


            String newOrgCreditId = orgCredit.getId();
            addCreditScoreAndFile(newOrgCreditId,orgCreditScores);

            // 如果企业信用id有
        } else {

            // 判断等级是否直接为D级
            if (orgCreditScores.get(0).getOrgScoreType() != 1) {
                List<OrgCreditScore> byOrgCreditIdAndOrgScoreType = orgCreditMapper.getByOrgCreditIdAndOrgScoreType(orgCreditId, 1);

                // 根据基础信息判断有无直接为D
                if ("1".equals(type)) {
                    for (OrgCreditScore orgCreditScore : byOrgCreditIdAndOrgScoreType) {
                        if (orgCreditScore.getScoreStandardTotal()!= null && orgCreditScore.getScoreStandardTotal() == 0 && orgCreditScore.getScoreStandardNum() == 1) {
                            flag = true;
                            break;
                        }
                    }
                }
            }else{
                for (OrgCreditScore obj : orgCreditScores) {

                    // 上传资料不传ScoreStandardTotal
                    if ("1".equals(type)) {
                        if (obj.getScoreStandardTotal() == 0 && obj.getScoreStandardNum() == 1) {
                            flag = true;
                        }
                    }
                }
            }

            // 如果企业信用对象存在，就判断当前企业评分类型的企业评分对象是否存在，存在修改(先删除再添加)不存在添加
            List<OrgCreditScore> oldOrgCreditScores = orgCreditMapper.getByOrgCreditIdAndOrgScoreType(orgCreditId, orgCreditScores.get(0).getOrgScoreType());
            if (oldOrgCreditScores.size() == 0) {
                addCreditScoreAndFile(orgCreditId, orgCreditScores);
            } else {

                // 评分不需要合并，上传资料才要合并
               if(!"1".equals(type)){
                   mergeCreitScore(orgCreditScores, oldOrgCreditScores);
               }

                // 删除旧的
                for (OrgCreditScore orgCreditScore : oldOrgCreditScores) {
                      orgCreditMapper.deleteOrgCreditScore(orgCreditScore.getId());
                        List<String> orgCreditFileIds = orgCreditScore.getOrgCreditFileIds();
                        if(orgCreditFileIds != null){
                            for (String orgCreditFileId : orgCreditFileIds) {
                                orgCreditMapper.deleteOrgCreditFile(orgCreditFileId);
                            }
                        }

                }

                // 添加新的

                //update by wangwj 20190103 start
                addCreditScoreAndFile(orgCreditId, orgCreditScores);

            }

            //update by wangwj 20190103 start

            //控制上传资料时 不能更改分数和等级
            if ("1".equals(type)) {
                // 根据评分找到等级
                CreditGrade byScore = getByScore(totalScore);
                orgCredit = orgCreditMapper.get(orgCreditId);
                if (flag) {
                    orgCredit.setCreditGradeName("D");
                } else {
                    orgCredit.setCreditGradeName(byScore.getName());
                }
                orgCredit.setCreditGradeScore(totalScore);
                    orgCreditMapper.update(orgCredit);
                // update by xujc 2019/3/18 end
                //update by wangwj 20190103 end
            }
        }
    }
    /**
     * @author : xujc
     * @date :2019/1/11
     * @Description : 将原来有的信息合并到当前对象
     */
    private void mergeCreitScore(List<OrgCreditScore> orgCreditScores, List<OrgCreditScore> oldOrgCreditScores) {
        // update by xujc 2019/3/15 start
          for(OrgCreditScore oldObj:oldOrgCreditScores){
              boolean flag = false;
              for(OrgCreditScore newObj:orgCreditScores){
                  // 如果旧的对象在新的集合中找到了
                  if(oldObj.getScoreStandardId().equals(newObj.getScoreStandardId())){
                      flag=true;
                      // 如果新的没有旧的有就将旧的拿过来
                      if(newObj.getScoreStandardNum() == null && oldObj.getScoreStandardNum() != null){
                          newObj.setScoreStandardNum(oldObj.getScoreStandardNum());
                      }
                      if(newObj.getScoreStandardTotal() == null && oldObj.getScoreStandardTotal() != null){
                          newObj.setScoreStandardTotal(oldObj.getScoreStandardTotal());
                      }
                  }
              }
              // 如果旧的对象在新的集合中没有找到
              if(!flag){
                  //在新的集合中加入旧的对象
                  oldObj.setOrgCreditFileIds(null);
                  orgCreditScores.add(oldObj);
              }
          }
          // update by xujc 2019/3/15 end
    }

    /**
     * @author : xujc
     * @date :2019/1/11
     * @Description : 添加信用评分以及信用附件
     */
    private void addCreditScoreAndFile(String orgCreditId, List<OrgCreditScore> orgCreditScores) {
        for (OrgCreditScore obj : orgCreditScores) {
                obj.setOrgCreditId(orgCreditId);
                orgCreditMapper.addOrgCreditScore(obj);
                List<String> orgCreditFileIds = obj.getOrgCreditFileIds();
                if(orgCreditFileIds !=null){
                    for (String orgCreditFileId : orgCreditFileIds) {
                        OrgCreditFile orgCreditFile = new OrgCreditFile();
                        orgCreditFile.setOrgCreditFileId(orgCreditFileId);
                        orgCreditFile.setOrgCreditScoreId(obj.getId());
                        orgCreditMapper.addOrgCreditFile(orgCreditFile);
                    }
                }

        }
    }

    @Override
    public List<OrgCreditScore> getByOrgCreditIdAndOrgScoreType(String orgCreditId, Integer orgScoreType) {
        return orgCreditMapper.getByOrgCreditIdAndOrgScoreType(orgCreditId, orgScoreType);
    }

    @Override
    public CreditGrade getByScore(int score) {
        return orgCreditMapper.getByScore(score);
    }

    @Override
    public List<OrgCreditScore> getOrgCreditScoreList(Map map) {
        return orgCreditMapper.getOrgCreditScoreList(map);
    }

}
