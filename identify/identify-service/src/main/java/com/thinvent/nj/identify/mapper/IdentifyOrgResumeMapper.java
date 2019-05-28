package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyOrgResume;

/**
* 工作简历表 Mapper
* @author wangwj
* @date 2019-3-4
*/
//add by wangwj 20190305 start
@Repository
public interface IdentifyOrgResumeMapper extends CURDMapper<IdentifyOrgResume, String> {

	/**
	 * 根据法定代表人和技术负责人ID查询工作简历信息
	 */
		List<IdentifyOrgResume> getIdentifyOrgResumeListByPersonId(String personId);

	//add by wangwj 20190320 start
	/**
	 * 根据法定代表人和技术负责人逻辑删除工作简历表
 	 */
		void deleteOrgResumeByPersonId(String personId);


	//add by wangwj 20190320 end

}
//add by wangwj 20190305 end