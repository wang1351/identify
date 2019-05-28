package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyOrgPerson;
import com.thinvent.nj.identify.mapper.IdentifyOrgPersonMapper;
import com.thinvent.nj.identify.service.IdentifyOrgPersonService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangwj
 * @Date 2019/3/18 17:14
 */
@Service
public class IdentifyOrgPersonImpl extends BaseCURDServiceImpl<IdentifyOrgPerson, String> implements IdentifyOrgPersonService {
	@Autowired
	private IdentifyOrgPersonMapper identifyOrgPersonMapper;


	@Override
	public void saveIdentifyOrgPerson(IdentifyOrgPerson identifyOrgPerson) {

		identifyOrgPersonMapper.insert(identifyOrgPerson);
	}
}
