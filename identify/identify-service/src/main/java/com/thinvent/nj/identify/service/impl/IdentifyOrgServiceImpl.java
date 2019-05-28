package com.thinvent.nj.identify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.*;
import com.thinvent.nj.identify.service.IdentifyOrgIdentifyService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.identify.service.IdentifyOrgService;

import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.entity.Org;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* 鉴定机构名录库服务实现
* @author panqh
* @date 2018-11-01
*/
@Service
public class IdentifyOrgServiceImpl extends BaseCURDServiceImpl<IdentifyOrgMain, String> implements IdentifyOrgService {

    @Autowired
    private IdentifyOrgMainMapper identifyOrgMapper;
    @Autowired
    private IdentifyOrgTypeMapper identifyOrgTypeMapper;
    @Autowired
    private IdentifyOrgPersonMapper identifyOrgPersonMapper;
    @Autowired
    private IdentifyOrgIdentifyMapper identifyOrgIdentifyMapper;
    @Autowired
    private IdentifyOrgDeviceMapper identifyOrgDeviceMapper;
    @Autowired
    private IdentifyOrgResumeMapper identifyOrgResumeMapper;

    @Autowired
    private DictService dictService;

    @Autowired
    private OrgService orgService;

   /* @Autowired
    private IdentifyOrgMapper orgMapper;*/

    /**
     * 保存鉴定机构鉴定类型
     *
     * @param identifyOrgId
     * @param typeArray
     * @author panqh
     * @date 2018-11-02
     */
    @Override
    public void saveIdentifyOrgType(String identifyOrgId, JSONArray typeArray) {

        List<IdentifyOrgType> listIdentifyOrgType = identifyOrgMapper.get(identifyOrgId).getIdentifyOrgTypeList();
        if (listIdentifyOrgType != null && listIdentifyOrgType.size() > 0) {
            for (IdentifyOrgType identifyOrgType : listIdentifyOrgType) {
                identifyOrgTypeMapper.delete(identifyOrgType.getId());
            }
        }

        for (int i = 0; i < typeArray.size(); i++) {
            IdentifyOrgType ideOrgType = new IdentifyOrgType();
            ideOrgType.setIdentifyOrgId(identifyOrgId);
            ideOrgType.setIdentifyTypeKey(typeArray.getString(i));

            identifyOrgTypeMapper.insert(ideOrgType);


        }
    }
        /**
         * 鉴定机构的启用与禁用
         * @author panqh
         * @date 2018-11-02
         * @param type
         * @param idS
         */
        @Override
        public void identifyOrgUseOrUnUse (String type, String idS){
            String[] idArray = idS.split(",");
            for (int i = 0; i < idArray.length; i++) {
                IdentifyOrgMain identifyOrg = identifyOrgMapper.get(idArray[i]);
                identifyOrg.setUseStatus(Integer.valueOf(type));

                identifyOrgMapper.OrgUseOrUnUse(identifyOrg);
            }
        }

    @Override
    public IdentifyOrgMain getOrgMainByPlatformId(String platfromId) {
        return identifyOrgMapper.getOrgMainByPlatformId(platfromId);
    }

    @Override
    public List<IdentifyOrgMain> getIdentifyOrgsByTypeId(String identifyTypeId) {
        return identifyOrgMapper.getIdentifyOrgsByTypeId(identifyTypeId);
    }

    /**
         * 保存鉴定机构信息
         * @author panqh
         * @date 2018-11-23
         * @param identifyOrg
         * @return
         */
        @Transactional
        @Override
        public IdentifyOrgMain saveIdentifyOrg (IdentifyOrgMain identifyOrg){

            //判断同步UC机构编码是否为空

            // 获取鉴定科实体
            Org identifyDep = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);

            Map<String, Object> condition = new HashMap<>();

            condition.put("pId", identifyDep.getId());

            List<Org> lstOrg = orgService.getOrgListByCondition(condition);

            Org org = new Org();
            org.setPId(identifyDep.getId());
            org.setFullName(identifyOrg.getName());
            org.setSimpleName(identifyOrg.getName());
            org.setSort(lstOrg.size() + 1);
            orgService.insert(org);
            identifyOrgMapper.insert(identifyOrg);
            return identifyOrg;
        }

    /**
     * 保存鉴定机构信息
     * @author wangwj
     * @date 2019-3-18
     * @param
     * @return
     */
    @Override
        public void saveIdentifyOrgList(Map<String, Object> params) {
        // 单位信息
        JSONObject unitInfo = (JSONObject) params.get("unitinformation");
        //相关人员信息
        JSONArray userArrayInfo1 = (JSONArray) params.get("xgryxx");
        //工作简历表信息
        JSONArray resumeArrayInfo1 = (JSONArray) params.get("gzjl");
        //鉴定人员名单信息
        JSONArray identifyUserArrayInfo1 = (JSONArray) params.get("jdrytjb");
        //鉴定检测设备及软件统计表
        JSONArray devArrayInfo1 = (JSONArray) params.get("jdsbjgjsrjtjb");
        //申请类型：1 初始；2 变更；3 取消
        String sqlx = unitInfo.getString("sqlx");

        if("1".equals(sqlx)) {
            //判断现有表中是否有相同的PLATFORM_ID 如果有说明保存过了（报错）如果没有就继续保存
            String platformID = unitInfo.getString("orgId");
            IdentifyOrgMain main = identifyOrgMapper.getOrgMainByPlatformId(platformID);
            if (main == null) {

            IdentifyOrgMain org = new IdentifyOrgMain();
            org.setName(unitInfo.getString("orgName"));
            org.setPlatformId(unitInfo.getString("orgId"));
            // TODO...补充剩余字段
            //单位类型
            org.setType(unitInfo.getString("unittype"));
            //单位住所
            org.setAddress(unitInfo.getString("uintaddress"));
            //申请类型
            org.setRequestType("1");
            //注册资本
            org.setRegisteredCapital(unitInfo.getString("zczj"));
            //成立日期
            org.setBuildDate(unitInfo.getDate("clsj"));
            //营业期限-开始
            org.setBusinessTermStart(unitInfo.getDate("yyqxkssj"));
            //营业期限-结束
            org.setBusinessTermEnd(unitInfo.getDate("yyqxjssj"));
            //统一社会信用代码
            org.setUnifiedCode(unitInfo.getString("unifiedCode"));
            //营业执照登记机关
            org.setRegistrationOffice(unitInfo.getString("yyzzdjjg"));
            // 检测检验机构资质认证书编号
            org.setOrgCode(unitInfo.getString("jcjyjgzzzsbh"));
            // 检测检验机构资质有效日期-开始
            org.setOrgStart(unitInfo.getDate("jcjyjgzzkssj"));
            //检测检验机构资质有效日期-结束
            org.setOrgEnd(unitInfo.getDate("jcjyjgzzjssj"));
            //建设工程质量检测机构资质证书编号
            org.setQualiCode(unitInfo.getString("jzgcjcjgzsbh"));
            //建设工程质量检测机构资质核定项目
            org.setQualiProject(unitInfo.getString("jzgcjchdxm"));
            //建设工程质量检测机构资质核定项目有效日期-开始
            org.setQualiProjectStart(unitInfo.getDate("jzgczljcjgzzhdxmkssj"));
            //建设工程质量检测机构资质核定项目有效日期-结束
            org.setQualiProjectEnd(unitInfo.getDate("jzgczljcjgzzhdxmjssj"));
            //测绘资质证书编号
            org.setMappingCode(unitInfo.getString("chzzzsbh"));
            //测绘资质证书有效日期-开始
            org.setMappingStart(unitInfo.getDate("chzsyxqkssj"));
            //测绘资质证书有效日期-结束
            org.setMappingEnd(unitInfo.getDate("chzsyxqjssj"));
            //鉴定人数总数
            org.setTotalNum(unitInfo.getInteger("jdrys"));
            //高级工程师人数
            org.setAdvPersonNum(unitInfo.getInteger("gjgcsrs"));
            //中级工程师人数
            org.setMidPersonNum(unitInfo.getInteger("zjgcsrs"));
            //一级注册结构工程师人数
            org.setLevel1Num(unitInfo.getInteger("yjzcjggcsrs"));
            //二级注册结构工程师人数
            org.setLevel2Num(unitInfo.getInteger("ejzcjggcsrs"));
            //注册岩土工程师人数
            org.setRockNum(unitInfo.getInteger("zcytgcsrs"));
            //默认把单位设置为启用状态
            org.setUseStatus(Constant.USE_STATUS_YSE);
            //有无处分和责任事故
            org.setPunishment(unitInfo.getString("sfycfhsg"));
            identifyOrgMapper.insert(org);
            String orgMainId = org.getId();

            //保存法定代表人及技术负责人表
            IdentifyOrgPerson orgPerson = new IdentifyOrgPerson();
            //循环存入相关人员信息表
            for (int i = 0; i < userArrayInfo1.size(); i++) {
                JSONObject obj = userArrayInfo1.getJSONObject(i);
                String ryguid = obj.getString("ryguid");
                IdentifyOrgPerson person = new IdentifyOrgPerson();
                //ID

                //鉴定机构ID
                person.setOrgId(orgMainId);

                //相关人员表姓名
                person.setName(obj.getString("name"));
                //性别
                person.setSex(obj.getString("sex"));
                //出生年月
                person.setBirthday(obj.getDate("birthday"));
                //身份证号
                person.setIdentityNo(obj.getString("idnum"));
                //学历
                person.setEducation(obj.getString("education"));
                //技术职称等级
                person.setTitleDegree(obj.getString("jszcdj"));
                //职称证书编号
                person.setCertificateNo(obj.getString("zczsbh"));
                //执业资格
                person.setPractise(obj.getString("zyzg"));
                //注册证书编号
                person.setRegCertificateNo(obj.getString("zsbh"));
                //何时/何校/何专业毕业
                person.setGraduation(obj.getString("byxx"));
                //鉴定相关工作年限
                person.setWorkYear(obj.getString("jdxggznx"));
                //办公电话
                person.setOfficePhone(obj.getString("bgphone"));
                //移动电话
                person.setPhone(obj.getString("mobilephone"));
                //有无处分和责任事故
                person.setPunishment(obj.getString("sfycfhsg"));
                //人员类别
                person.setType(obj.getString("ryzw"));

                //保存法人及技术负责人信息表
                identifyOrgPersonMapper.insert(person);
                //获得保存后的法人技术负责人表的id
                String orgPersonId = person.getId();

                //循环存入工作简历
                for (int j = 0; j < resumeArrayInfo1.size(); j++) {
                    JSONObject orgResume = resumeArrayInfo1.getJSONObject(j);
                    String ry = orgResume.getString("ryguid");
                    if (ryguid.equals(ry)) {
                        IdentifyOrgResume resume = new IdentifyOrgResume();
                        //ID

                        //人员id
                        resume.setPersonId(orgPersonId);
                        //在何单位、从事何工作、任何职
                        resume.setWorkWhere(orgResume.getString("gzxq"));
                        //由何年何月至何年何月
                        resume.setWorkWhen(orgResume.getString("gzqx"));
                        //保存工作简历
                        // identifyOrgResumeService.saveIdentifyOrgResume(resume);
                        identifyOrgResumeMapper.insert(resume);
                    }
                }
            }


            //保存鉴定人员信息表
            for (int i = 0; i < identifyUserArrayInfo1.size(); i++) {
                JSONObject obj = identifyUserArrayInfo1.getJSONObject(i);
                IdentifyOrgIdentify identify = new IdentifyOrgIdentify();
                //姓名
                identify.setName(obj.getString("name"));
                //鉴定机构ID
                identify.setOrgId(orgMainId);
                //身份证号
                identify.setIdentityNo(obj.getString("idnum"));
                //专业
                identify.setMajor(obj.getString("profession"));
                //学历
                identify.setEducation(obj.getString("education"));
                //职称
                identify.setJob(obj.getString("job"));
                //执业资格
                identify.setPractise(obj.getString("zyzg"));
                //鉴定相关工作年限
                identify.setWorkYear(obj.getString("gznx"));
                //保存鉴定人员名单
                identifyOrgIdentifyMapper.insert(identify);

            }


            //保存检测设备和检测软件表
            for (int i = 0; i < devArrayInfo1.size(); i++) {
                JSONObject obj = devArrayInfo1.getJSONObject(i);
                IdentifyOrgDevice device = new IdentifyOrgDevice();
                //检测设备/结构计算软件名称
                device.setName(obj.getString("name"));
                //鉴定机构ID
                device.setOrgId(orgMainId);
                //规格型号
                device.setSpecification(obj.getString("ggxh"));
                //编号
                device.setNum(obj.getString("bh"));
                //备注
                device.setRemarks(obj.getString("bz"));
                //保存检测设备及其软件信息表
                identifyOrgDeviceMapper.insert(device);
            }

        }else{
                throw new IllegalArgumentException("该鉴定机构已经保存，无法重复保存" );
            }

        }else if ("2".equals(sqlx)){//变更
            //获得行政审批平台ID
           String platformId=unitInfo.getString("orgId");
            //通过行政审批平台ID获得主表对象
            IdentifyOrgMain orgMain=identifyOrgMapper.getOrgMainByPlatformId(platformId);
            //获得主表对象的ID值
            String orgMainId=orgMain.getId();

            //保存主表信息
            orgMain.setName(unitInfo.getString("orgName"));
            orgMain.setPlatformId(unitInfo.getString("orgId"));
            // TODO...补充剩余字段
            //保存主表ID
            orgMain.setId(orgMainId);
            //单位类型
            orgMain.setType(unitInfo.getString("unittype"));
            //单位住所
            orgMain.setAddress(unitInfo.getString("uintaddress"));
            //申请类型
            orgMain.setRequestType("2");
            //注册资本
            orgMain.setRegisteredCapital(unitInfo.getString("zczj"));
            //成立日期
            orgMain.setBuildDate(unitInfo.getDate("clsj"));
            //营业期限-开始
            orgMain.setBusinessTermStart(unitInfo.getDate("yyqxkssj"));
            //营业期限-结束
            orgMain.setBusinessTermEnd(unitInfo.getDate("yyqxjssj"));
            //统一社会信用代码
            orgMain.setUnifiedCode(unitInfo.getString("unifiedCode"));
            //营业执照登记机关
            orgMain.setRegistrationOffice(unitInfo.getString("yyzzdjjg"));
            // 检测检验机构资质认证书编号
            orgMain.setOrgCode(unitInfo.getString("jcjyjgzzzsbh"));
            // 检测检验机构资质有效日期-开始
            orgMain.setOrgStart(unitInfo.getDate("jcjyjgzzkssj"));
            //检测检验机构资质有效日期-结束
            orgMain.setOrgEnd(unitInfo.getDate("jcjyjgzzjssj"));
            //建设工程质量检测机构资质证书编号
            orgMain.setQualiCode(unitInfo.getString("jzgcjcjgzsbh"));
            //建设工程质量检测机构资质核定项目
            orgMain.setQualiProject(unitInfo.getString("jzgcjchdxm"));
            //建设工程质量检测机构资质核定项目有效日期-开始
            orgMain.setQualiProjectStart(unitInfo.getDate("jzgczljcjgzzhdxmkssj"));
            //建设工程质量检测机构资质核定项目有效日期-结束
            orgMain.setQualiProjectEnd(unitInfo.getDate("jzgczljcjgzzhdxmjssj"));
            //测绘资质证书编号
            orgMain.setMappingCode(unitInfo.getString("chzzzsbh"));
            //测绘资质证书有效日期-开始
            orgMain.setMappingStart(unitInfo.getDate("chzsyxqkssj"));
            //测绘资质证书有效日期-结束
            orgMain.setMappingEnd(unitInfo.getDate("chzsyxqjssj"));
            //鉴定人数总数
            orgMain.setTotalNum(unitInfo.getInteger("jdrys"));
            //高级工程师人数
            orgMain.setAdvPersonNum(unitInfo.getInteger("gjgcsrs"));
            //中级工程师人数
            orgMain.setMidPersonNum(unitInfo.getInteger("zjgcsrs"));
            //一级注册结构工程师人数
            orgMain.setLevel1Num(unitInfo.getInteger("yjzcjggcsrs"));
            //二级注册结构工程师人数
            orgMain.setLevel2Num(unitInfo.getInteger("ejzcjggcsrs"));
            //注册岩土工程师人数
            orgMain.setRockNum(unitInfo.getInteger("zcytgcsrs"));
            //默认把单位设置为启用状态
            orgMain.setUseStatus(Constant.USE_STATUS_YSE);
            //有无处分和责任事故
            orgMain.setPunishment(unitInfo.getString("sfycfhsg"));

            //更新主表信息
            identifyOrgMapper.update(orgMain);

            //根据主表ID 查出法定代表人 技术负责人信息表ID
            List<IdentifyOrgPerson> IdentifyOrgPerson =identifyOrgPersonMapper.getIdentifyOrgPersonListByMainId(orgMainId);
            //根据代表人表删除对应的工作简历表
            for(int i=0;i<IdentifyOrgPerson.size();i++){
                String orgPersonId=IdentifyOrgPerson.get(i).getId();
                identifyOrgResumeMapper.deleteOrgResumeByPersonId(orgPersonId);
            }

            //通过主表ID 删除法人和技术负责人表
            identifyOrgPersonMapper.deleteIdentifyOrgPersonListByMainId(orgMainId);

            //通过主表ID 删除鉴定人员信息表
            identifyOrgIdentifyMapper.deleteIdentifyOrgIdentifyList(orgMainId);

            //通过主表ID 逻辑删除使用设备表
            identifyOrgDeviceMapper.deleteIdentifyOrgDevice(orgMainId);

            //保存法定代表人技术负责人
            for(int i=0;i<userArrayInfo1.size();i++) {
                JSONObject obj = userArrayInfo1.getJSONObject(i);
                String ryguid = obj.getString("ryguid");
                IdentifyOrgPerson person = new IdentifyOrgPerson();
                //ID

                //鉴定机构ID
                person.setOrgId(orgMainId);

                //相关人员表姓名
                person.setName(obj.getString("name"));
                //性别
                person.setSex(obj.getString("sex"));
                //出生年月
                person.setBirthday(obj.getDate("birthday"));
                //身份证号
                person.setIdentityNo(obj.getString("idnum"));
                //学历
                person.setEducation(obj.getString("education"));
                //技术职称等级
                person.setTitleDegree(obj.getString("jszcdj"));
                //职称证书编号
                person.setCertificateNo(obj.getString("zczsbh"));
                //执业资格
                person.setPractise(obj.getString("zyzg"));
                //注册证书编号
                person.setRegCertificateNo(obj.getString("zsbh"));
                //何时/何校/何专业毕业
                person.setGraduation(obj.getString("byxx"));
                //鉴定相关工作年限
                person.setWorkYear(obj.getString("jdxggznx"));
                //办公电话
                person.setOfficePhone(obj.getString("bgphone"));
                //移动电话
                person.setPhone(obj.getString("mobilephone"));
                //有无处分和责任事故
                person.setPunishment(obj.getString("sfycfhsg"));
                //人员类别
                person.setType(obj.getString("ryzw"));

                //保存法人及技术负责人信息表
                identifyOrgPersonMapper.insert(person);
                //获得保存后的法人技术负责人表的id
                String orgPersonId = person.getId();
                //循环存入工作简历
                for(int j=0;j<resumeArrayInfo1.size();j++){
                    JSONObject orgResume =resumeArrayInfo1.getJSONObject(j);
                    String ry = orgResume.getString("ryguid");
                    if(ryguid.equals(ry)){
                        IdentifyOrgResume resume =new IdentifyOrgResume();
                        //ID

                        //人员id
                        resume.setPersonId(orgPersonId);
                        //在何单位、从事何工作、任何职
                        resume.setWorkWhere(orgResume.getString("gzxq"));
                        //由何年何月至何年何月
                        resume.setWorkWhen(orgResume.getString("gzqx"));
                        //保存工作简历
                        // identifyOrgResumeService.saveIdentifyOrgResume(resume);
                        identifyOrgResumeMapper.insert(resume);
                    }
                }
            }
            //保存鉴定人员信息表
            for(int i=0;i<identifyUserArrayInfo1.size();i++){
                JSONObject obj =identifyUserArrayInfo1.getJSONObject(i);
                IdentifyOrgIdentify identify=new IdentifyOrgIdentify();
                //姓名
                identify.setName(obj.getString("name"));
                //鉴定机构ID
                identify.setOrgId(orgMainId);
                //身份证号
                identify.setIdentityNo(obj.getString("idnum"));
                //专业
                identify.setMajor(obj.getString("profession"));
                //学历
                identify.setEducation(obj.getString("education"));
                //职称
                identify.setJob(obj.getString("job"));
                //执业资格
                identify.setPractise(obj.getString("zyzg"));
                //鉴定相关工作年限
                identify.setWorkYear(obj.getString("gznx"));
                //保存鉴定人员名单
                identifyOrgIdentifyMapper.insert(identify);

            }
            //保存鉴定设备信息表
            for(int i=0;i<devArrayInfo1.size();i++){
                JSONObject obj =devArrayInfo1.getJSONObject(i);
                IdentifyOrgDevice device=new IdentifyOrgDevice();
                //检测设备/结构计算软件名称
                device.setName(obj.getString("name"));
                //鉴定机构ID
                device.setOrgId(orgMainId);
                //规格型号
                device.setSpecification(obj.getString("ggxh"));
                //编号
                device.setNum(obj.getString("bh"));
                //备注
                device.setRemarks(obj.getString("bz"));
                //保存检测设备及其软件信息表
                identifyOrgDeviceMapper.insert(device);
            }



        } else if ("3".equals(sqlx)){//取消
            //获得行政审批平台ID
            String platformId=unitInfo.getString("orgId");
            //通过行政审批平台ID获得主表对象
            IdentifyOrgMain orgMain=identifyOrgMapper.getOrgMainByPlatformId(platformId);
            //申请类型
            orgMain.setRequestType("3");
            //获得主表对象的ID值
            String orgMainId=orgMain.getId();
            //根据主表ID 逻辑删除主表
            identifyOrgMapper.deleteOrgMainByMainId(orgMainId);



        }else{//
            throw new IllegalArgumentException("sqlx 必须是 1， 2， 3， 当前是： " + sqlx);
        }





        }
    }
