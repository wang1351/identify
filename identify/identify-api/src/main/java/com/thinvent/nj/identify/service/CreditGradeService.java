package com.thinvent.nj.identify.service;

import com.thinvent.nj.mybatis.service.CURDService;
import com.thinvent.nj.identify.entity.CreditGrade;

import java.util.Map;

/**
* 企业信用级别服务
* @author wangwj
* @date 2018-10-31
*/
public interface CreditGradeService extends CURDService<CreditGrade, String> {
	void updateFrom(Map<String, Object> data);

// void updateCreditGrade(CreditGrade );


}
