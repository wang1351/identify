package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.identify.entity.CorrectVerify;
import com.thinvent.nj.identify.entity.IdentifyReport;
import com.thinvent.nj.identify.mapper.CorrectMapper;
import com.thinvent.nj.identify.mapper.CorrectVerifyMapper;
import com.thinvent.nj.identify.service.CorrectMainService;
import com.thinvent.nj.identify.service.IdentifyReportService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* T_IDENTIFY_MAIN服务实现
* @author xujc
* @date 2018-12-06
*/
@Service
public class CorrectMainServiceImpl extends BaseCURDServiceImpl<Correct, String> implements CorrectMainService {

    @Autowired
    private CorrectMapper correctMapper;
    @Autowired
    private CorrectVerifyMapper correctVerifyMapper;
    @Autowired
    private IdentifyReportService reportService;


    @Override
    public Correct getByMainId(String mainId) {
        return correctMapper.getByMainId(mainId);
    }

    @Override
    public Correct getById(String id) {
        Correct correct = correctMapper.get(id);
        // 给编制报告对象赋值
        String mainId = correct.getMainId();
        IdentifyReport report = reportService.getByMainId(mainId);
        correct.setIdentifyReport(report);
        return correct;
    }

    @Override
    public CorrectVerify getCheckByCorrectId(String correctId) {
        return correctVerifyMapper.getCheckByCorrectId(correctId);
    }
}
