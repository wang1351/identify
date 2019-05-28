package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyOrgResume;
import com.thinvent.nj.mybatis.service.CURDService;


/**
 * 鉴定机构法定代表人及技术负责人
 * @author wangwj
 * @date 2019-3-19
 */
public interface IdentifyOrgResumeService extends CURDService<IdentifyOrgResume,String> {
	/**
	 * 保存法定代表人以及技术负责人信息
	 */
	void saveIdentifyOrgResume(IdentifyOrgResume identifyOrgResume);



}
