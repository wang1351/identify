package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyOrgIdentify;
import com.thinvent.nj.mybatis.service.CURDService;

/**
 * 鉴定人员信息表
 * @author wangwj
 * @date 2019-3-19
 */

public interface IdentifyOrgIdentifyService extends CURDService<IdentifyOrgIdentify,String> {
	/**
	 * 保存鉴定人员信息表
	 */
	void saveIdentifyOrgIdentify(IdentifyOrgIdentify identifyOrgIdentify);




}
