package com.thinvent.nj.identify.service.impl;


import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.*;
import com.thinvent.nj.identify.service.IdentifyReportService;
import com.thinvent.nj.identify.service.ReviewMainService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * T_IDENTIFY_REVIEW_MAIN服务实现
 *
 * @author xujc
 * @date 2018-11-13
 */
@Service
public class ReviewMainServiceImpl extends BaseCURDServiceImpl<Review, String> implements ReviewMainService {

    @Autowired
    private ReviewExpertMapper reviewExpertMapper;

    @Autowired
    private ReviewSendMapper reviewSendMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ExpertMapper expertMapper;
    @Autowired
    private IdentifyReportService reportService;
    @Autowired
    private ReviewAcceptMapper reviewAcceptMapper;
    @Autowired
    private ReviewOpinionMapper reviewOpinionMapper;

    @Override
    public List<Map<String, Object>> getReviewExpertsByCondition(Map<String, Object> map) {

        List<Map<String, Object>> reviewExperts = new ArrayList<>();

        List<ReviewExpert> list = reviewExpertMapper.findList(map);
        for (ReviewExpert reviewExpert : list) {
            map = new HashMap<>();
            String fieldName = expertMapper.getFieldNameByField(reviewExpert.getExpertField().toString());
            String expertIds = reviewExpert.getExpertIds();
            String[] ids = expertIds.split(",");
            String finalExpert = "";
            for (String id : ids) {
                Expert expert = expertMapper.get(id);
                finalExpert += expert.getName() + " (" + (expert.getTitle() == null ? "" : expert.getTitle()) + "  " + expert.getPhone() + ")、";
            }
            finalExpert = finalExpert.substring(0, finalExpert.length() - 1);
            map.put("fieldName", fieldName);
            map.put("expertMsg", finalExpert);
            reviewExperts.add(map);
        }
        return reviewExperts;
    }

    @Override
    public ReviewOpinion getReviewOpinionByReviewId(Map<String, Object> map) {
        List<ReviewOpinion> list = reviewOpinionMapper.findList(map);
        return list.get(0);
    }


    @Override
    public ReviewSend getReviewSendByReviewId(String reviewId) {
        return reviewSendMapper.getReviewSendByReviewId(reviewId);
    }

    @Override
    public Review getByMainId(String mainId) {
        return reviewMapper.getByMainId(mainId);
    }

    @Override
    public Review getById(String id) {
        Review review = reviewMapper.get(id);
        // 给回避专家集合赋值
        String ids = review.getExceptExpertIds();

        List<Expert> list = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                Expert expert = expertMapper.get(s);
                list.add(expert);
            }
        }


        review.setExperts(list);
        // 给编制报告对象赋值
        String mainId = review.getMainId();
        IdentifyReport report = reportService.getByMainId(mainId);
        review.setIdentifyReport(report);
        return review;
    }

    @Override
    public ReviewAccept getByReviewId(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("reviewId", id);
        List<ReviewAccept> list = reviewAcceptMapper.findList(map);
        return list.get(0);
    }


}
