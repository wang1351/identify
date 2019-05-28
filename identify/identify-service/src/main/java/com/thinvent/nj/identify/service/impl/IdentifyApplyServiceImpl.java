package com.thinvent.nj.identify.service.impl;


import com.thinvent.nj.common.util.DateUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.IdentifyApply;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.mapper.IdentifyApplyMapper;
import com.thinvent.nj.identify.mapper.IdentifyMainMapper;
import com.thinvent.nj.identify.service.IdentifyApplyService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author  xujc
 * @date  2019/4/9
 * @Description 手机申请实现服务类
 */
@Service
public class IdentifyApplyServiceImpl extends BaseCURDServiceImpl<IdentifyApply,String> implements IdentifyApplyService {
    @Autowired
    private IdentifyApplyMapper applyMapper;
    @Autowired
    private IdentifyMainMapper identifyMainMapper;
    /**
     * @author : xujc
     * @date :2019/4/10
     * @Description : 获取案件编号
     */
    @Override
    public String generateCaseNumber() {
        String formatterDate = DateUtil.toChar(new Date(), "yyyyMMdd");
        String caseNo = applyMapper.getMaxCaseNo(formatterDate);

        String result;
        if (StringUtil.isNullOrEmpty(caseNo)) {
            result = formatterDate + "001";
        } else {
            if (caseNo.length() > 11) {
                caseNo = caseNo.substring(0, 11);
            }
            result = String.valueOf(Long.parseLong(caseNo) + 1);
        }

        return result;
    }

    @Transactional
    @Override
    public void refuseApply(String id) {
        // 修改手机申请表的状态
        IdentifyApply identifyApply = applyMapper.get(id);
        identifyApply.setIdentifyStatue(Constant.PHONE_APPLY_YJJ);
        applyMapper.update(identifyApply);
        //在鉴定主表中加入一条数据
        IdentifyMain identifyMain= new IdentifyMain();
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_PHONE_JJSQ);
        Integer identifySource = identifyApply.getIdentifySource();
        if(identifySource == 1){
            identifyMain.setMethod(Constant.IDENTIFY_METHOD_WDNJ);
        }else {
            identifyMain.setMethod(Constant.IDENTIFY_METHOD_FCZW);
        }
        identifyMain.setSuspend(Constant.IDENTIFY_PROCESS_RUNNING);
        identifyMain.setCaseNo(identifyApply.getIdentifyCode());
        identifyMain.setOrgId(identifyApply.getIdentifyOrgId());
        identifyMain.setRequestTime(identifyApply.getCreateTime());
        identifyMain.setContent(identifyApply.getTypeValue());
        identifyMain.setOperatorTime(new Date());
        identifyMainMapper.insert(identifyMain);
    }

}
