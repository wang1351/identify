package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyOrgDevice;
import com.thinvent.nj.identify.mapper.IdentifyOrgDeviceMapper;
import com.thinvent.nj.identify.service.IdentifyOrgDeviceService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 鉴定机构设备信息表
 * @author wangwj
 * @Date 2019/3/19 9:29
 */
@Service
public class IdentifyOrgDeviceServiceImpl  extends BaseCURDServiceImpl<IdentifyOrgDevice,String> implements IdentifyOrgDeviceService{
	@Autowired
	private IdentifyOrgDeviceMapper identifyOrgDeviceMapper;

	@Override
	public void saveIdentifyOrgDevice(IdentifyOrgDevice identifyOrgDevice) {
		identifyOrgDeviceMapper.insert(identifyOrgDevice);

	}
}
