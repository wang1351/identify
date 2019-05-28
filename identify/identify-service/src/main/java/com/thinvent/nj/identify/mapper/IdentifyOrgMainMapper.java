package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyOrg;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyOrgMain;

/**
* 鉴定机构名录库主表 Mapper
* @author wangwj
* @date 2019-3-4
*/
//update by wangwj 20190305 start
@Repository
public interface IdentifyOrgMainMapper extends CURDMapper<IdentifyOrgMain, String> {
	/**
	 * 鉴定机构的启用与禁用
	 * @author panqh
	 * @date 2018-11-02
	 * @param identifyOrg
	 */
	void OrgUseOrUnUse(IdentifyOrgMain identifyOrg);

	/**
	 * 根据条件获取鉴定机构列表
	 * @author panqh
	 * @date 2018-11-23
	 * @param condition
	 * @return
	 */
	List<IdentifyOrgMain> getIdentifyOrgListByCondition(Map<String, Object> condition);

	/**
	 * 根据鉴定机构ID获得鉴定机构业务类型关系列表
	 */


	/**
	 * 根据行政审批平台ORG_ID获取鉴定机构名录库信息
	 * @param platfromId
	 * @return
	 */
	public IdentifyOrgMain getOrgMainByPlatformId(String platfromId);

	/**
	 * 根据主表ID逻辑删除主表信息
	 */
	void deleteOrgMainByMainId(String mainId);

	/**
	 * @author : xujc
	 * @date :2019/4/8
	 * @Description : 根据鉴定类型找到鉴定机构信息(H5用)
	 */
	public List<IdentifyOrgMain> getIdentifyOrgsByTypeId(String identifyTypeId);



}
//update by wangwj 20190305 end