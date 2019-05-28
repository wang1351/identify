package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.identify.mapper.CreditGradeMapper;
import com.thinvent.nj.identify.service.CreditGradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* 企业信用级别服务实现
* @author administrator
* @date 2018-10-31
*/
@Service
public class CreditGradeServiceImpl extends BaseCURDServiceImpl<CreditGrade, String> implements CreditGradeService {

	@Autowired
	private CreditGradeMapper creditGradeMapper;


	@Override
	public void updateFrom(Map<String, Object> data) {

		// 更新A
		CreditGrade aCreditGrade = creditGradeMapper.getByName("A");
		aCreditGrade.setLet(null);
		aCreditGrade.setGet((Integer)data.get("aGet"));
		creditGradeMapper.update(aCreditGrade);

		CreditGrade bCreditGrade =creditGradeMapper.getByName("B");
		bCreditGrade.setLet((Integer)data.get("bLet"));
		bCreditGrade.setGet((Integer)data.get("bGet"));
		creditGradeMapper.update(bCreditGrade);

		CreditGrade cCreditGrade =creditGradeMapper.getByName("C");
		cCreditGrade.setLet((Integer)data.get("cLet"));
		cCreditGrade.setGet((Integer)data.get("cGet"));
		creditGradeMapper.update(cCreditGrade);

		CreditGrade dCreditGrade=creditGradeMapper.getByName("D");
		dCreditGrade.setGet(null);
		dCreditGrade.setLet((Integer)data.get("dLet"));
		creditGradeMapper.update(dCreditGrade);
	}

	//编写方法：用于输入一个integer类型（分数）返回相应的String类型（等级）
	public String getLevel(Integer score) {


		CreditGrade acreditGrade = creditGradeMapper.getByName("A");
		Integer aGet = acreditGrade.getGet();

		CreditGrade bcreditGrade = creditGradeMapper.getByName("B");
		Integer bGet = bcreditGrade.getGet();
		Integer bLet = bcreditGrade.getLet();

		CreditGrade ccreditGrade = creditGradeMapper.getByName("C");
		Integer cGet = ccreditGrade.getGet();
		Integer cLet = ccreditGrade.getLet();

		CreditGrade dcreditGrade = creditGradeMapper.getByName("D");
		Integer dLet = dcreditGrade.getLet();

		if (score >= aGet) {
			return "A";
		} else if (score <= bLet && score >= bGet) {
			return "B";
		} else if (score <= cLet && score >= cGet) {
			return "C";
		} else {
			return "D";
		}


	}




}
