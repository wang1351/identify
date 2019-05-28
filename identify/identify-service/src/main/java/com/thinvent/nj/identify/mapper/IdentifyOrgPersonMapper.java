package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyOrgPerson;

/**
* 法定代表人&技术负责人表 Mapper
* @author wangwj
* @date 2019-3-4
*/
//add by wangwj 20190305 start
@Repository
public interface IdentifyOrgPersonMapper extends CURDMapper<IdentifyOrgPerson, String> {

	/**
	 * 根据鉴定机构名录库Id查找法定代表人和技术负责人表
	 */
	List<IdentifyOrgPerson> getIdentifyOrgPersonListByMainId(String mainId);


	//add by wangwj 20190320 start
	/**
	 * 根据主表ID逻辑删除法定代表人&技术负责人表对象
	 */
	void deleteIdentifyOrgPersonListByMainId(String mainId);
	//add by wangwj 20190320 end

}
//add by wangwj 20190305 end