package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* 企业信用级别 Mapper
* @author administrator
* @date 2018-10-31
*/
@Repository
public interface CreditGradeMapper extends CURDMapper<CreditGrade, String> {

	CreditGrade getByName(String name);
}
