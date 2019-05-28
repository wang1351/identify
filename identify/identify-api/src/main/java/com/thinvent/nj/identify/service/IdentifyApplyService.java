package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyApply;
import com.thinvent.nj.mybatis.service.CURDService;

/**
 * @author : xujc
 * @date :2019/4/9
 * @Description : 手机端申请服务
 */
public interface IdentifyApplyService extends CURDService<IdentifyApply, String> {

    String generateCaseNumber();
    void refuseApply(String id);
}
