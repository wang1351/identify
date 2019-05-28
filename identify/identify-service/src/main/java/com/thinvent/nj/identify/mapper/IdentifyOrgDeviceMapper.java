package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyOrgDevice;

/**
* 使用设备信息表 Mapper
* @author wangwj
* @date 2019-3-5
*/
//add by wangwj 20190305 start
@Repository
public interface IdentifyOrgDeviceMapper extends CURDMapper<IdentifyOrgDevice, String> {
	/**
	 * 根据鉴定机构ID 查询设备信息表
	 */
	List<IdentifyOrgDevice>getIdentifyOrgDeviceListByMainId(String mainId);

	/**
	 * 根据鉴定机构ID 删除使用设备信息表
	 */
	void deleteIdentifyOrgDevice(String mainId);

}
//add by wangwj 20190305 end