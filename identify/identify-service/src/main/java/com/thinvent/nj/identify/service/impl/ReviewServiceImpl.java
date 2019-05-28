/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewServiceImpl.java
 * 功能概要       :
 * 做成日期       : 2018-11-27・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.*;
import com.thinvent.nj.identify.service.ReviewService;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.service.DictService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 复核业务实体服务实现
 *
 * @author panqh
 * @date 2018-11-27
 */
@Service
public class ReviewServiceImpl extends BaseCURDServiceImpl<Review, String> implements ReviewService {

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private IdentifyMainMapper identifyMainMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ExpertAppointMapper expertAppointMapper;
    @Autowired
    private ReviewAcceptMapper reviewAcceptMapper;


    @Autowired
    private ReviewExpertMapper reviewExpertMapper;

    @Autowired
    private ReviewOpinionMapper reviewOpinionMapper;

    @Autowired
    private OpinionFileMapper opinionFileMapper;

    @Autowired
    private ReviewSendMapper reviewSendMapper;

    @Autowired
    private IdentifyReviewRecordMapper identifyReviewRecordMapper;
    @Autowired
    private DictService dictService;

    // 工作流服务注入
    @Resource(name = "reviewProcessService")
    private ProcessService reviewService;

    @Override
    public List<Review> findList(Map<String, Object> params) {

        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", params.get("curUserId"));
        Map<String, Object> map = MapperUtil.convertToMap(reviewService.getMyTasks(condition));

        Map<String, Object> keyTaskIdMap = (Map<String, Object>) map.get("data");
        params.put("reviewIdList", keyTaskIdMap.keySet());

        return super.findList(params);
    }

    /**
     * 保存复核业务申请信息
     *
     * @param mainId：鉴定主表ID
     * @param params：需要保存的参数
     * @author panqh
     * @date 2018-11-28
     */
    @Transactional
    @Override
    public void saveReviewRequestInfo(String mainId, Map<String, Object> params) {
        Map<String, Object> map = (Map<String, Object>) params.get("params");
        // 更新鉴定主表
        IdentifyMain identifyMain = identifyMainMapper.get(mainId);
        identifyMain.setIsReview(Constant.IDENTIFY_REVIEW_YES);
        identifyMainMapper.update(identifyMain);

        // 保存复核业务主表
        Review review = new Review();
        review.setSuspend(Constant.IDENTIFY_PROCESS_RUNNING);
        review.setMainId(mainId);
        review.setStatus(Constant.REVIEW_STATUS_YSQ);
        review.setReason((String) map.get("reason"));
        review.setOperatorTime(new Date());
        review.setRequestName((String) map.get("requestName"));
        review.setIdNum((String) map.get("idNum"));
        review.setContactName((String) map.get("contactName"));
        review.setPhone((String) map.get("phone"));
        review.setContactAddress((String) map.get("contactAddress"));
        review.setSheetId((String) map.get("applyFileId"));
        JSONArray reportDetailArray = (JSONArray) map.get("reportDetailId");
        String reportDetailIds = "";
        for (int j = 0; j < reportDetailArray.size(); j++) {
            String reportDetailId = reportDetailArray.getString(j);
            reportDetailIds += reportDetailId + ",";
        }
        if (!StringUtil.isNullOrEmpty(reportDetailIds)) {
            reportDetailIds = reportDetailIds.substring(0, reportDetailIds.length() - 1);
        }
        review.setReportDetailIds(reportDetailIds);
        JSONArray expertArray = (JSONArray) map.get("exceptExpert");
        String exceptExpertIds = "";
        if(expertArray !=null && expertArray.size()>0){
            for (int j = 0; j < expertArray.size(); j++) {
                String expertId = expertArray.getString(j);
                exceptExpertIds += expertId + ",";
            }
        }

        if (!StringUtil.isNullOrEmpty(exceptExpertIds)) {
            exceptExpertIds = exceptExpertIds.substring(0, exceptExpertIds.length() - 1);
        }
        review.setExceptExpertIds(exceptExpertIds);
        reviewMapper.insert(review);

        // 保存指定专家表
        List<ExpertAppoint> lstExpApp = new ArrayList<>();

        List<Integer> expertFields = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().startsWith("expertField")) {
                expertFields.add(Integer.parseInt(String.valueOf(entry.getValue())));

            }
        }
        for (int i = 0; i < expertFields.size(); i++) {
            ExpertAppoint expApp = new ExpertAppoint();
            expApp.setReviewId(review.getId());
            expApp.setExpertField(Integer.parseInt((String) map.get("expertField" + i)));
            expApp.setExpertNo(Integer.parseInt((String) map.get("expertNo" + i)));
            lstExpApp.add(expApp);
        }

        expertAppointMapper.insertExpertAppointList(lstExpApp);

        // 保存记录表
        IdentifyReviewRecord reviewRecord = new IdentifyReviewRecord();
        reviewRecord.setReviewId(review.getId());
        reviewRecord.setOperatorUserName((String) params.get("curUserName"));
        reviewRecord.setOperatorTime(new Date());
        reviewRecord.setStatus(Constant.REVIEW_STATUS_YSQ);
        reviewRecord.setSort(identifyReviewRecordMapper.generatorNextSort(review.getId()));
        identifyReviewRecordMapper.insert(reviewRecord);


        // 保存工作流信息
        Map<String, Object> param = new HashMap<>(2);
        param.put("businessKey", review.getId());
        param.put("startUserId", params.get("curUserId"));
        reviewService.start(param);
    }

    /**
     * 根据复核主键和专家领域获取专家领域列表
     *
     * @param reviewId
     * @return
     * @author panqh
     * @date 2018-11-29
     */
    @Override
    public List<ExpertBusinessArea> getExpertList(String reviewId, String field) {
        // 获取回避专家
        Review review = reviewMapper.get(reviewId);
        String exceptExpertIds = review.getExceptExpertIds();
        String[] excExpertIdS={};
        if(!StringUtil.isNullOrEmpty(exceptExpertIds)){
            excExpertIdS = exceptExpertIds.split(",");
        }
        // 获取除去回避专家的鉴定专家
        Map<String, Object> params = new HashMap<>();
        params.put("excExpertIdS", excExpertIdS);
        params.put("expertField", field);
        List<ExpertBusinessArea> lstExpertField = expertMapper.getExpertFieldByField(params);

        return lstExpertField;
    }

    /**
     * 根据复核业务主键和专家领域获取指定专家实体
     *
     * @param reviewId
     * @param field
     * @return
     * @author panqh
     * @date 2018-12-04
     */
    @Override
    public ExpertAppoint getExpertAppointByReviewIdAndField(String reviewId, String field) {
        if (StringUtil.isNullOrEmpty(reviewId) || StringUtil.isNullOrEmpty(field)) {
            return null;
        }

        return expertAppointMapper.getExpertAppoint(reviewId, field);
    }

    /**
     * 根据复核主键获取随机专家列表
     *
     * @param reviewId
     * @return map (key: expertField, value: expertId List)
     * @author panqh
     * @date 2018-11-29
     */
    @Override
    public Map<Integer, List<String>> getRandomExpertListByCondition(Map<String,Object> map) {
        String reviewId = (String)map.get("reviewId");
        // 删掉的ids
        String delIds = (String)map.get("delIds");
        // 剩余的ids
        String oldIds =(String)map.get("oldIds");
        List<ExpertAppoint> lstExpertApp =new ArrayList<>();

        // 本复核需要回避的专家列表
        Review review = reviewMapper.get(reviewId);
        String exceptExpertIds = review.getExceptExpertIds();
        List<String> expertIdExcepts = new ArrayList<>();
        if(!StringUtil.isNullOrEmpty(exceptExpertIds)){
            expertIdExcepts=Arrays.asList(exceptExpertIds.split(","));
        }

        if(StringUtil.isNullOrEmpty(delIds)){
            // 获取本复核需要的领域专家列表
            lstExpertApp = expertAppointMapper.getAppointExpertListByReviewId(reviewId);
        }else {
            String[] split = delIds.split("@");
            for(String s :split){
                ExpertAppoint expertAppoint = new ExpertAppoint();
                String[] split1 = s.split(":");
                expertAppoint.setExpertNo(split1[1].split(",").length);
                expertAppoint.setExpertField(Integer.parseInt(split1[0]));
                lstExpertApp.add(expertAppoint);
            }
            // 将已经有的id 和回避ids放在一起
            exceptExpertIds +=','+oldIds;
            expertIdExcepts =Arrays.asList(exceptExpertIds.split(","));


        }





        // 专家领域和专家ID列表的映射
        Map<Integer, List<String>> fieldExpertMap = Maps.newHashMap();


        if (lstExpertApp != null && lstExpertApp.size() > 0) {
            for (ExpertAppoint expertApp : lstExpertApp) {

                // 随机并选择合适领域的专家
                randomAndChooseExperts(expertApp, fieldExpertMap, expertIdExcepts);
            }
        }

        return fieldExpertMap;
    }

    /**
     * 随机并选择合适领域的专家
     * <p>
     * 基本实现思路如下：
     * <p>
     * 1）先从领域筛选出合适的专家，并去掉回避的专家
     * 2）随机一个专家，判断该专家是否已经被本领域选中，如果已被选中，重新随机选，否则执行第三步
     * 3）判断该专家是否已被其他领域选中，如果已被选中，重新随机选，否则执行第四步
     * 4）将该专家选中，纳入对应领域，如果专家数量已满足人数需要，停止，否则执行第二步
     *
     * @param fieldExpertMap
     */
    private void randomAndChooseExperts(ExpertAppoint expertAppoint,
                                        Map<Integer, List<String>> fieldExpertMap,
                                        List<String> expertIdExcepts) {
        Integer expertField = expertAppoint.getExpertField();
        Integer expertNum = expertAppoint.getExpertNo();

        // 领域对应专家
        List<String> experts = getExpertsByField(expertField);

        // 去除回避专家
        experts = experts.parallelStream().filter(t -> !expertIdExcepts.contains(t)).collect(Collectors.toList());

        // 不满足需要的专家数时，直接抛出异常
        if (experts.size() < expertNum) {
            throw new IllegalStateException("expect experts [" + expertNum + "], but actual totoal experts : [" + experts + "]");
        }

        int loopCount = 0;

        while (expertNum > 0) {
            // 随机一个专家序号
            int randomNum = randomNum(experts.size());
            List<String> chosenExperts = fieldExpertMap.get(expertField);

            if (chosenExperts == null) {
                chosenExperts = new ArrayList<>(10);
                fieldExpertMap.put(expertField, chosenExperts);
            }

            String randomExpertId = experts.get(randomNum);

            // 该专家尚未被当前领域选中
            boolean isCurNotChosen = chosenExperts.parallelStream().noneMatch(t -> t.equals(randomExpertId));

            // 该专家尚未被其他领域选中
            boolean isOtherNotChosen = fieldExpertMap.values().stream().flatMap(List::stream).noneMatch(t -> t.equals(randomExpertId));

            if (isCurNotChosen && isOtherNotChosen) {
                chosenExperts.add(randomExpertId);

                experts.remove(randomNum);
                expertNum--;
            }

            loopCount++;

            // 循环大于1000，说明该领域找不到合适的专家，抛出异常
            if (loopCount > 1000) {
                throw new IllegalStateException("cannot find experts for field : [" + expertField + "]");
            }
        }

    }

    /**
     * 随机一个整数，范围为0 ～ (size - 1)
     *
     * @param size
     * @return
     */
    private int randomNum(int size) {
        return new Random().nextInt(size);
    }


    /**
     * 获取指定领域的专家
     *
     * @param field
     * @return 领域专家ID列表
     */
    @Override
    public List<String> getExpertsByField(Integer field) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("expertField", field);

        List<ExpertBusinessArea> expertBusinessAreas = expertMapper.getExpertFieldByField(params);

        return expertBusinessAreas.parallelStream().map(ExpertBusinessArea::getExpertId).collect(Collectors.toList());
    }
    /**
     * @author : xujc
     * @date :2018/12/27
     * @Description : 跟据自动选中的领域专家找到详细
     */
    @Override
    public List<Map<String, Object>> getAutoChecked(String str) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        // update by xujc 2019/1/22 start
        String[] split = str.split("@");
        for(String allStr:split){
            String field = allStr.substring(0,allStr.indexOf(":"));
            DictItem dict = dictService.getItemByGroupKeyAndValue(Constant.EXPERT_FIELD_KEY, field);

            String expertIds = allStr.substring(allStr.indexOf(":")+1);
            String value = "";
            for(String expertId:expertIds.split(",")){
                Expert expert = expertMapper.get(expertId);
                value +=expert.getName() + "（" + (expert.getTitle() ==null?"":expert.getTitle()) + " " + expert.getPhone() + "）"+",";
            }
            if(!StringUtil.isNullOrEmpty(value)){
                value = value.substring(0,value.length() -1);
            }
            map = new HashMap<>();
            map.put(dict.getName(),value);
            list.add(map);

        }
        // update by xujc 2019/1/22 end
        return list;
    }
    /**
     * @author : xujc
     * @date :2018/12/28
     * @Description : 自动选择专家时，显示每个领域需要选出几个
     */
    @Override
    public List<ExpertAppoint> getExpertAppointsByReviewId(Map<String, Object> map) {
        return expertAppointMapper.getAppointExpertListByReviewId((String) map.get("reviewId"));
    }

    @Override
    public List<Map<String,Object>> getReviewExpertsByCondition(Map<String, Object> map) {
        List<Map<String,Object>> list =new ArrayList<>();
        Map<String,Object> mapMsg =null;
        // 得到选择专家集合
        List<ReviewExpert> reviewExperts =new ArrayList<>();

        // 如果传reviewId
        if(!StringUtil.isNullOrEmpty((String)map.get("reviewId"))){
            reviewExperts = reviewExpertMapper.findList(map);
        }else {
            // 如果传allIdStr
            String allIdStr = (String)map.get("allIdStr");
            String[] split = allIdStr.split("@");
            for(String s: split){
                if(!StringUtil.isNullOrEmpty(s)){
                    String[] split1 = s.split(":");
                    ReviewExpert reviewExpert =new ReviewExpert();
                    reviewExpert.setExpertIds(split1[1]);
                    reviewExpert.setExpertField(Integer.parseInt(split1[0]));
                    String fieldName = expertMapper.getFieldNameByField(split1[0]);
                    reviewExpert.setExpertFieldName(fieldName);
                    reviewExperts.add(reviewExpert);
                }

            }
        }
        // 对reviewExperts按照领域排序
        Collections.sort(reviewExperts,( ReviewExpert reviewExpert1, ReviewExpert reviewExpert2)-> reviewExpert1.getExpertField() -reviewExpert2.getExpertField());
        for(ReviewExpert reviewExpert:reviewExperts){
            List<Map<String,Object>> experts =new ArrayList<>();
            mapMsg = new HashMap<>();
            // mapMsg代表领域专家对象
            mapMsg.put("expertFieldName",reviewExpert.getExpertFieldName()+":");
            mapMsg.put("method",reviewExpert.getMethod());
            // 选择专家的id字符串
            String expertIds = reviewExpert.getExpertIds();
            // 选择专家的id数组
            String[] ids = expertIds.split(",");
            for(int i = 0 ; i<ids.length ; i++){
                Map<String,Object> newExpert = new HashMap<>();
                // 根据专家id得到专家
                Expert expert = expertMapper.get(ids[i]);
                String fullName =expert.getName() + "(" + (expert.getTitle() ==null?"":expert.getTitle()) + " " + expert.getPhone() + ")";
                if(i==0){
                    newExpert.put("expertFieldName",reviewExpert.getExpertFieldName()+":");
                }else {
                    newExpert.put("expertFieldName","");
                }
                newExpert.put("expertId",expert.getId());
                newExpert.put("fullName",fullName);
                newExpert.put("expertField",reviewExpert.getExpertField());
                experts.add(newExpert);
            }
            mapMsg.put("experts",experts);
            list.add(mapMsg);
        }
        return list;
    }

    /**
     * 选择专家保存前的验证
     *
     * @param params
     * @return
     * @author panqh
     * @date 2018-12-03
     */
    @Override
    public String validatorReviewExpert(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put("code", "0");
        result.put("msg", "成功！");


        String reviewId = (String) params.get("reviewId");
        String expertInfo = (String) params.get("expertInfo");
        List<ExpertAppoint> lstExpertApp = expertAppointMapper.getAppointExpertListByReviewId(reviewId);
        String fieldS = "";
        for (ExpertAppoint expertAppoint : lstExpertApp) {
            fieldS += expertAppoint.getExpertField() + ",";
        }

        String[] expertArray = expertInfo.split("@");
        if (lstExpertApp.size() != expertArray.length) {
            result.put("code", "1");
            result.put("msg", "专家领域与申请时的领域数量不等！");
            return JSON.toJSONString(result);
        }

        if (validatorExpertRepeat(expertArray)) {
            result.put("code", "2");
            result.put("msg", "存在重复的专家！");
            return JSON.toJSONString(result);
        }

        for (ExpertAppoint expertAppoint : lstExpertApp) {
            for (int i = 0; i < expertArray.length; i++) {
                String expertField = expertArray[i];
                String field = expertField.substring(0, expertField.indexOf(":"));

                if (!fieldS.contains(field)) {
                    result.put("code", "3");
                    result.put("msg", "选择的专家领域不在申请的专家领域里！");
                    break;
                }

                String expertIdS = expertField.substring(expertField.indexOf(":") + 1, expertField.length());
                String[] expertIdArray = expertIdS.split(",");
                if (String.valueOf(expertAppoint.getExpertField()).equals(field)) {
                    if (expertAppoint.getExpertNo() != expertIdArray.length) {
                        result.put("code", "4");
                        result.put("msg", "专家领域的专家数量与申请时的专家数量不等！");
                        break;
                    }
                }
            }
        }

        return JSON.toJSONString(result);
    }

    /**
     * 验证专家的重复性
     *
     * @param expertArray
     * @return
     * @author panqh
     * @date 2018-12-03
     */
    private boolean validatorExpertRepeat(String[] expertArray) {
        List<String> lstExpertId = new ArrayList<>();

        for (int i = 0; i < expertArray.length; i++) {
            String expertField = expertArray[i];
            String expertIdS = expertField.substring(expertField.indexOf(":") + 1, expertField.length());
            String[] expertIdArray = expertIdS.split(",");
            for (int j = 0; j < expertIdArray.length; j++) {
                String expertId = expertIdArray[j];

                lstExpertId.add(expertId);
            }
        }
        return new HashSet<>(lstExpertId).size() != lstExpertId.size();
    }

    /**
     * @author : xujc
     * @date :2018/12/25
     * @Description : 保存复核申请审核信息
     */
    @Transactional
    @Override
    public void saveReviewApplyCheckInfo(Map<String, Object> params) {
        String reviewId = (String) params.get("reviewId");
        Integer result = (Integer) params.get("result");
        String reason = (String) params.get("reason");
        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");
        // 修改主表状态
        Review review = reviewMapper.get(reviewId);

        ReviewAccept reviewAccept = new ReviewAccept();
        // 记录表
        IdentifyReviewRecord reviewRecord = new IdentifyReviewRecord();
        // 受理表
        if (result == 1) {
            reviewAccept.setReason(reason);
        }
        reviewAccept.setReviewId(reviewId);
        reviewAccept.setResult(result);
        reviewAcceptMapper.insert(reviewAccept);

        // 受理
        if (result == 1) {
            review.setStatus(Constant.REVIEW_STATUS_YSL);
            reviewRecord.setStatus(Constant.REVIEW_STATUS_YSL);
        } else {
            review.setStatus(Constant.REVIEW_STATUS_BSL);
            reviewRecord.setRemarks(reason);
            reviewRecord.setBusinessKey(reviewAccept.getId());
            reviewRecord.setStatus(Constant.REVIEW_STATUS_BSL);
        }
        reviewMapper.update(review);

        reviewRecord.setOperatorUserName(curUserName);
        reviewRecord.setSort(identifyReviewRecordMapper.generatorNextSort(reviewId));
        reviewRecord.setReviewId(reviewId);
        identifyReviewRecordMapper.insert(reviewRecord);

        // 保存工作流信息
        String taskId = reviewService.getTaskId(curUserId, reviewId);
        Map<String, Object> map = new HashMap<>(2);
        map.put("status",result);
        reviewService.complete(taskId,map);
    }

    /**
     * 保存复核业务选择专家信息
     *
     * @param params：需要保存的参数
     * @author panqh
     * @date 2018-12-03
     */
    @Transactional
    @Override
    public void saveReviewExpertInfo(Map<String, Object> params) {
        String reviewId = (String) params.get("reviewId");
        String expertInfo = (String) params.get("expertInfo");

        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");

        Review review = reviewMapper.get(reviewId);
        review.setStatus(Constant.REVIEW_STATUS_YXZZJ);
        review.setOperatorTime(new Date());
        reviewMapper.update(review);

        // 保存记录表
        IdentifyReviewRecord reviewRecord = new IdentifyReviewRecord();
        reviewRecord.setReviewId(review.getId());
        reviewRecord.setOperatorUserName(curUserName);
        reviewRecord.setOperatorTime(new Date());
        reviewRecord.setStatus(Constant.REVIEW_STATUS_YXZZJ);
        reviewRecord.setSort(identifyReviewRecordMapper.generatorNextSort(review.getId()));
        identifyReviewRecordMapper.insert(reviewRecord);

        // 保存ReviewExpert表
        List<ReviewExpert> lstReviewExpert = new ArrayList<>();
        ReviewExpert reviewExpert = null;
        String[] expertArray = expertInfo.split("@");
        if (expertArray != null && expertArray.length > 0) {
            for (int i = 0; i < expertArray.length; i++) {
                String expertField = expertArray[i];
                String field = expertField.substring(0, expertField.indexOf(":"));
                String expertIdS = expertField.substring(expertField.indexOf(":") + 1, expertField.length());
                reviewExpert = new ReviewExpert();
                reviewExpert.setExpertField(Integer.parseInt(field));
                reviewExpert.setExpertIds(expertIdS);
                reviewExpert.setType(1);
                reviewExpert.setMethod(Integer.parseInt((String)params.get("method")));
                reviewExpert.setReviewId(reviewId);
                reviewExpertMapper.insert(reviewExpert);
            }

        }

        // 保存工作流信息
        String taskId = reviewService.getTaskId(curUserId, reviewId);
        reviewService.complete(taskId, new HashMap<>(1));
    }



    /**
     * @author : xujc
     * @date :2019/1/2
     * @Description : 保存确认专家
     */
    @Transactional
    @Override
    public void saveReviewConfirmExpertInfo(Map<String, Object> params) {
        String reviewId = (String) params.get("reviewId");
        String expertInfo = (String) params.get("expertInfo");

        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");

        Review review = reviewMapper.get(reviewId);
        review.setStatus(Constant.REVIEW_STATUS_YQRZJ);
        review.setOperatorTime(new Date());
        reviewMapper.update(review);

        // 保存记录表
        IdentifyReviewRecord reviewRecord = new IdentifyReviewRecord();
        reviewRecord.setReviewId(review.getId());
        reviewRecord.setOperatorUserName(curUserName);
        reviewRecord.setOperatorTime(new Date());
        reviewRecord.setStatus(Constant.REVIEW_STATUS_YQRZJ);
        reviewRecord.setSort(identifyReviewRecordMapper.generatorNextSort(review.getId()));
        identifyReviewRecordMapper.insert(reviewRecord);

        // 保存ReviewExpert表
        List<ReviewExpert> lstReviewExpert = new ArrayList<>();
        ReviewExpert reviewExpert = null;
        String[] expertArray = expertInfo.split("@");
        if (expertArray != null && expertArray.length > 0) {
            for (int i = 0; i < expertArray.length; i++) {
                String expertField = expertArray[i];
                String field = expertField.substring(0, expertField.indexOf(":"));
                String expertIdS = expertField.substring(expertField.indexOf(":") + 1, expertField.length());
                reviewExpert = new ReviewExpert();
                reviewExpert.setExpertField(Integer.parseInt(field));
                reviewExpert.setExpertIds(expertIdS);
                reviewExpert.setType(2);
                reviewExpert.setMethod(Integer.parseInt((String)params.get("method")));
                reviewExpert.setReviewId(reviewId);
                reviewExpertMapper.insert(reviewExpert);
            }

        }

        // 保存工作流信息
        String taskId = reviewService.getTaskId(curUserId, reviewId);
        reviewService.complete(taskId, new HashMap<>(1));
    }

    /**
     * 保存复核业务专家意见信息
     *
     * @param params：需要保存的参数
     * @author panqh
     * @date 2018-12-04
     */
    @Transactional
    @Override
    public void saveReviewOpinionInfo(Map<String, Object> params) {
        Map<String,Object> map =  (Map<String, Object>) params.get("optionData");
        String reviewId = (String)map.get("reviewId");
        String opinion = (String) params.get("opinion");
        JSONArray fileIdS = (JSONArray) params.get("fileIdS");

        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");

        // 更新复核业务主表的状态
        Review review = reviewMapper.get(reviewId);

        review.setStatus(Constant.REVIEW_STATUS_YTXYJ);
        review.setOperatorTime(new Date());

        reviewMapper.update(review);
        // 保存记录表
        IdentifyReviewRecord reviewRecord = new IdentifyReviewRecord();
        reviewRecord.setReviewId(review.getId());
        reviewRecord.setOperatorUserName((String) params.get("curUserName"));
        reviewRecord.setOperatorTime(new Date());
        reviewRecord.setStatus(Constant.REVIEW_STATUS_YTXYJ);
        reviewRecord.setSort(identifyReviewRecordMapper.generatorNextSort(review.getId()));
        identifyReviewRecordMapper.insert(reviewRecord);

        // 保存专家意见信息
        ReviewOpinion reviewOpinion = new ReviewOpinion();
        MapperUtil.copy(map,reviewOpinion);
        reviewOpinionMapper.insert(reviewOpinion);

        // 保存专家意见附件信息
        List<OpinionFile> lstOpinionFile = new ArrayList<>();
        OpinionFile opinionFile = null;
        if (fileIdS != null && fileIdS.size() > 0) {
            for (int i = 0; i < fileIdS.size(); i++) {
                opinionFile = new OpinionFile();
                opinionFile.setOpinionId(reviewOpinion.getId());
                opinionFile.setFileId(fileIdS.getString(i));
                opinionFile.setSort(i + 1);

                lstOpinionFile.add(opinionFile);
            }
            opinionFileMapper.insertOpinionFileList(lstOpinionFile);
        }


        // 保存工作流信息
        String taskId = reviewService.getTaskId(curUserId, reviewId);
        reviewService.complete(taskId, new HashMap<>(1));
    }

    /**
     * 保存复核业务办结信息
     *
     * @param params：需要保存的参数
     * @author panqh
     * @date 2018-12-04
     */
    @Transactional
    @Override
    public void saveReviewSendInfo(Map<String, Object> params) {
        String reviewId = (String) params.get("reviewId");

        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");

        // 更新复核业务主表的状态
        Review review = reviewMapper.get(reviewId);
        review.setStatus(Constant.REVIEW_STATUS_YFF);
        review.setOperatorTime(new Date());

        reviewMapper.update(review);

        // 保存记录表
        IdentifyReviewRecord reviewRecord = new IdentifyReviewRecord();
        reviewRecord.setReviewId(review.getId());
        reviewRecord.setOperatorUserName((String) params.get("curUserName"));
        reviewRecord.setOperatorTime(new Date());
        reviewRecord.setStatus(Constant.REVIEW_STATUS_YFF);
        reviewRecord.setSort(identifyReviewRecordMapper.generatorNextSort(review.getId()));
        identifyReviewRecordMapper.insert(reviewRecord);
        // 保存复核业务办结表
        ReviewSend reviewSend = new ReviewSend();
        reviewSend.setReviewId(reviewId);

        reviewSendMapper.insert(reviewSend);
        // 保存工作流信息
        String taskId = reviewService.getTaskId(curUserId, reviewId);
        reviewService.complete(taskId, new HashMap<>(1));
    }
}