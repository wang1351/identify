package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyOrgIdentify;
import com.thinvent.nj.identify.mapper.IdentifyOrgIdentifyMapper;
import com.thinvent.nj.identify.service.IdentifyOrgIdentifyService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangwj
 * @Date 2019/3/19 9:12
 */
@Service
public class IdentifyOrgIdentifyServiceImpl extends BaseCURDServiceImpl<IdentifyOrgIdentify,String> implements IdentifyOrgIdentifyService{
	@Autowired
	private IdentifyOrgIdentifyMapper identifyOrgIdentifyMapper;

	@Override
	public void saveIdentifyOrgIdentify(IdentifyOrgIdentify identifyOrgIdentify) {
		identifyOrgIdentifyMapper.insert(identifyOrgIdentify);
	}
}
