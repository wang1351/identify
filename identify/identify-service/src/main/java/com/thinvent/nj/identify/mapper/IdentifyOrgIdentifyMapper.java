package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyOrgIdentify;

/**
* 鉴定人员信息表 Mapper
* @author wangwj
* @date 2019-3-4
*/
//add by wangwj 20190305 start
@Repository
public interface IdentifyOrgIdentifyMapper extends CURDMapper<IdentifyOrgIdentify, String> {
	/**
	 * 根据鉴定机构ID 查询鉴定人员信息表
	 */
	List<IdentifyOrgIdentify> getIdentifyOrgIdentifyListByMainId(String mainId);


	/**
	 * 逻辑删除人员信息表
	 */
	void deleteIdentifyOrgIdentifyList(String mainId);


}
//add by wangwj 20190305 end