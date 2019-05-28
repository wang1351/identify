package com.thinvent.nj.identify.service;


import com.thinvent.nj.identify.entity.IdentifyOrgDevice;
import com.thinvent.nj.mybatis.service.CURDService;

/**
 * 使用设备信息表
 * @author wangwj
 * @date 2019-3-19
 */
public interface IdentifyOrgDeviceService extends CURDService<IdentifyOrgDevice,String> {
	/**
	 * 保存使用设备信息表
	 */
	public void saveIdentifyOrgDevice(IdentifyOrgDevice identifyOrgDevice);

}
