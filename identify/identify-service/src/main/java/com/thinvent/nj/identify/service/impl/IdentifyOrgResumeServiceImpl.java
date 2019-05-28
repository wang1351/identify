package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyOrgResume;
import com.thinvent.nj.identify.mapper.IdentifyOrgResumeMapper;
import com.thinvent.nj.identify.service.IdentifyOrgResumeService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangwj
 * @Date 2019/3/19 8:48
 */
@Service
public class IdentifyOrgResumeServiceImpl extends BaseCURDServiceImpl<IdentifyOrgResume,String> implements IdentifyOrgResumeService{
	@Autowired
	private IdentifyOrgResumeMapper identifyOrgResumeMapper;

	@Override
	public void saveIdentifyOrgResume(IdentifyOrgResume identifyOrgResume) {
		identifyOrgResumeMapper.insert(identifyOrgResume);

	}
}
