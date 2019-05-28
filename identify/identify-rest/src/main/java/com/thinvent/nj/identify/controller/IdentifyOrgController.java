package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;

import com.thinvent.nj.identify.entity.*;

import com.thinvent.nj.identify.service.*;
import com.thinvent.nj.web.controller.BaseViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;

/**
 * 鉴定单位备案库 Controller
 * @author liupj
 */
@Controller
public class IdentifyOrgController extends BaseViewController {

    @Autowired
    private IdentifyOrgService identifyOrgService;
    @Autowired
    private IdentifyOrgDeviceService identifyOrgDeviceService;
    @Autowired
    private IdentifyOrgIdentifyService identifyOrgIdentifyService;
    @Autowired
    private IdentifyOrgPersonService identifyOrgPersonService;
    @Autowired
    private IdentifyOrgResumeService identifyOrgResumeService;

    /**
     * 提供给行政审批平台调用，同步审批平台发过来的鉴定单位数据
     * @param params {
     *       unitinformation: {    // 单位信息
     *                orgId: 单位唯一编号,
     *                orgName: 单位名称,
     *                unifiedCode: 统一社会信用代码,
     *                unittype,  单位类型
     *                uintaddress, 单位住所
     *                zczj,  注册资金
     *                clsj,  成立时间
     *                yyqxkssj, 营业期限开始时间
     *                yyqxjssj, 营业期限结束时间
     *                yyzzdjjg, 营业执照登记机关
     *                jcjyjgzzzsbh, 检测检验机构资质证书编号
     *                jcjyjgzzkssj, 检测检验机构资质起始时间
     *                jcjyjgzzjssj, 检测检验机构资质结束时间
     *                jzgcjcjgzsbh, 建筑工程质量检测机构资质证书编号
     *                jzgcjchdxm,   建筑工程质量检测机构资质核定项目
     *                jzgczljcjgzzhdxmkssj, 建筑工程质量检测机构资质核定项目有效期开始时间
     *                jzgczljcjgzzhdxmjssj, 建筑工程质量检测机构资质核定项目有效期结束时间
     *                chzzzsbh, 测绘资质证书编号
     *                chzsyxqkssj, 测绘资质证书有效期开始时间
     *                chzsyxqjssj, 测绘资质证书有效期结束时间
     *                jdrys,   鉴定人员总数
     *                gjgcsrs, 高级工程师人数
     *                zjgcsrs, 中级工程师人数
     *                yjzcjggcsrs, 一级注册结构工程师人数
     *                ejzcjggcsrs, 二级注册结构工程师人数
     *                zcytgcsrs, 注册岩土工程师人数
     *                sqlx, 申请类型：1 初始；2 变更；3 取消
     *               sfycfhsg 有无处分和责任事故
     *       },
     *
*           xgryxx: [{  // 相关人员信息表
     *                 ryguid: 人员编号
     *                 name： 姓名
                     * sex 性别
                     * birthday： 出生年月
                     * idnum    身份证号
                     * education 学历
                     * bgphone 办公电话
                     * jszcdj 技术职称等级
                     * zczsbh 职称证书编号
                     * zyzg 执业资格
                     * zsbh 注册证书编号
                     * byxx 何时/何校/何专业毕业
                     * jdxggznx 鉴定相关工作年限
                     * mobilephone 移动电话
                     * sfycfhsg 有无处分或责任事故
                     * ryzw 人员职务
     *           }],
     *
     *      gzjl: [{  // 工作简历
     *          gzqx 由何年何月至何年何月
     *          gzxq 在何单位、从事何工作、任何职
     *          ryguid: 人员编号
     *      }],
     *
     *      jdrytjb: [{  // 鉴定人员名单
     *          name 姓名
     *          idnum 身份证号
     *          profession 专业
     *          education 学历
     *          job 职称
     *          zyzg 执业资格
     *          gznx 鉴定相关工作年限
*           }],
     *
     *      jdsbjgjsrjtjb: [{ // 5、鉴定检测设备及结构计算软件统计表
     *          name, 检测设备/结构计算软件名称
     *          ggxh, 规格型号
*               bh, 编号
     *          bz 备注
*           }]
     *
     *
     * }
     * @return
     */
    @RequestMapping(path = "/identify/orgs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity sync(@RequestBody Map<String, Object> params) {
        identifyOrgService.saveIdentifyOrgList(params);
//        // 单位信息
//        JSONArray unitInfo = (JSONArray) params.get("unitinformation");
//        //相关人员信息
//        JSONArray userArrayInfo1 = (JSONArray) params.get("xgryxx");
//        //工作简历表信息
//        JSONArray resumeArrayInfo1 = (JSONArray) params.get("gzjl");
//        //鉴定人员名单信息
//        JSONArray identifyUserArrayInfo1 = (JSONArray) params.get("jdrytjb");
//        //鉴定检测设备及软件统计表
//        JSONArray devArrayInfo1 = (JSONArray) params.get("jdsbjgjsrjtjb");
//        //申请类型：1 初始；2 变更；3 取消
//        JSONObject unitObj = JSON.parseObject(unitInfo.toString());
//        String sqlx = unitObj.getString("sqlx");
//
//        IdentifyOrgMain org;
//        //暂时记录鉴定机构主表ID
//        String orgMainId = null;
//        //法定代表人和技术负责人id
//        String orgPersonId =null;
//        // 新建
//        if ("1".equals(sqlx)) {
//          /*  org = new IdentifyOrgMain();
//            org.setName(unitInfo.getString("orgName"));
//            org.setPlatformId(unitInfo.getString("orgId"));
//            // TODO...补充剩余字段
//            //单位住所
//            org.setAddress(unitInfo.getString("uintaddress"));
//           //注册资本
//            org.setRegisteredCapital(unitInfo.getString("zczj"));
//            //成立日期
//            org.setBuildDate(unitInfo.getDate("clsj"));
//            //营业期限-开始
//            org.setBusinessTermStart(unitInfo.getDate("yyqxkssj"));
//            //营业期限-结束
//            org.setBusinessTermEnd(unitInfo.getDate("yyqxjssj"));
//            //统一社会信用代码
//            org.setUnifiedCode(unitInfo.getString("unifiedCode"));
//            //营业执照登记机关
//            org.setRegistrationOffice(unitInfo.getString("yyzzdjjg"));
//            // 检测检验机构资质认证书编号
//            org.setOrgCode(unitInfo.getString("jcjyjgzzzsbh"));
//            // 检测检验机构资质有效日期-开始
//            org.setOrgStart(unitInfo.getDate("jcjyjgzzkssj"));
//            //检测检验机构资质有效日期-结束
//            org.setOrgEnd(unitInfo.getDate("jcjyjgzzjssj"));
//            //建设工程质量检测机构资质证书编号
//            org.setQualiCode(unitInfo.getString("jzgcjcjgzsbh"));
//            //建设工程质量检测机构资质核定项目
//            org.setQualiProject(unitInfo.getString("jzgcjchdxm"));
//            //建设工程质量检测机构资质核定项目有效日期-开始
//            org.setQualiProjectStart(unitInfo.getDate("jzgczljcjgzzhdxmkssj"));
//            //建设工程质量检测机构资质核定项目有效日期-结束
//            org.setQualiProjectEnd(unitInfo.getDate("jzgczljcjgzzhdxmjssj"));
//            //测绘资质证书编号
//            org.setMappingCode(unitInfo.getString("chzzzsbh"));
//            //测绘资质证书有效日期-开始
//            org.setMappingStart(unitInfo.getDate("chzsyxqkssj"));
//            //测绘资质证书有效日期-结束
//            org.setMappingEnd(unitInfo.getDate("chzsyxqjssj"));
//            //鉴定人数总数
//            org.setTotalNum(unitInfo.getInteger("jdrys"));
//            //高级工程师人数
//            org.setAdvPersonNum(unitInfo.getInteger("gjgcsrs"));
//            //中级工程师人数
//            org.setMidPersonNum(unitInfo.getInteger("zjgcsrs"));
//            //一级注册结构工程师人数
//            org.setLevel1Num(unitInfo.getInteger("yjzcjggcsrs"));
//            //二级注册结构工程师人数
//            org.setLevel2Num(unitInfo.getInteger("ejzcjggcsrs"));
//            //注册岩土工程师人数
//            org.setRockNum(unitInfo.getInteger("zcytgcsrs"));
//            //有无处分和责任事故
//            org.setPunishment(unitInfo.getString("sfycfhsg"));*/
//            //identifyOrgService.saveIdentifyOrgList(unitInfo);
//            //取出保存后的鉴定单位ID
//           // orgMainId = org.getId();
//            //循环存入相关人员信息表
//            for(int i=0;i<userArrayInfo1.size();i++){
//                JSONObject obj = userArrayInfo1.getJSONObject(i);
//                IdentifyOrgPerson person = new IdentifyOrgPerson();
//                //ID
//                person.setId(obj.getString("ryguid"));
//                //鉴定机构ID
//                //person.setOrgId(orgMainId);
//                //相关人员表姓名
//                person.setName(obj.getString("name"));
//                //性别
//                person.setSex(obj.getString("sex"));
//                //出生年月
//                person.setBirthday(obj.getDate("birthday"));
//                //身份证号
//                person.setIdentityNo(obj.getString("idnum"));
//                //学历
//                person.setEducation(obj.getString("education"));
//                //技术职称等级
//                person.setTitleDegree(obj.getString("jszcdj"));
//                //职称证书编号
//                person.setCertificateNo(obj.getString("zczsbh"));
//                //执业资格
//                person.setPractise(obj.getString("zyzg"));
//                //注册证书编号
//                person.setRegCertificateNo(obj.getString("zsbh"));
//                //何时/何校/何专业毕业
//                person.setGraduation(obj.getString("byxx"));
//                //鉴定相关工作年限
//                person.setWorkYear(obj.getString("jdxggznx"));
//                //办公电话
//                person.setOfficePhone(obj.getString("bgphone"));
//                //移动电话
//                person.setPhone(obj.getString("mobilephone"));
//                //有无处分和责任事故
//                person.setPunishment(obj.getString("sfycfhsg"));
//                //保存法人和技术负责人信息
//               // identifyOrgPersonService.saveIdentifyOrgPerson(person);
//                //取出法人及技术负责人ID
//               // orgPersonId = person.getId();
//            }
//
//            //循环存入工作简历
//            for(int i=0;i<resumeArrayInfo1.size();i++){
//                JSONObject obj =resumeArrayInfo1.getJSONObject(i);
//                IdentifyOrgResume resume =new IdentifyOrgResume();
//                //ID
//                resume.setId(obj.getString("ryguid"));
//                //人员id
//               // resume.setPersonId(orgPersonId);
//                //在何单位、从事何工作、任何职
//                resume.setWorkWhere(obj.getString("gzxq"));
//                //由何年何月至何年何月
//                resume.setWorkWhen(obj.getString("gzqx"));
//                //保存工作简历
//               // identifyOrgResumeService.saveIdentifyOrgResume(resume);
//            }
//
//            //循环鉴定人员名单
//            for(int i=0;i<identifyUserArrayInfo1.size();i++){
//                JSONObject obj =identifyUserArrayInfo1.getJSONObject(i);
//                IdentifyOrgIdentify identify=new IdentifyOrgIdentify();
//                //姓名
//                identify.setName(obj.getString("name"));
//                //鉴定机构ID
//                //identify.setOrgId(orgMainId);
//                //身份证号
//                identify.setIdentityNo(obj.getString("idnum"));
//                //专业
//                identify.setMajor(obj.getString("profession"));
//                //学历
//                identify.setEducation(obj.getString("education"));
//                //职称
//                identify.setJob(obj.getString("job"));
//                //执业资格
//                identify.setPractise(obj.getString("zyzg"));
//                //鉴定相关工作年限
//                identify.setWorkYear(obj.getString("gznx"));
//                //保存鉴定人员名单
//                //identifyOrgIdentifyService.saveIdentifyOrgIdentify(identify);
//
//            }
//
//            //鉴定检测设备及其软件
//            for(int i=0;i<devArrayInfo1.size();i++){
//                JSONObject obj =devArrayInfo1.getJSONObject(i);
//                IdentifyOrgDevice device=new IdentifyOrgDevice();
//                //检测设备/结构计算软件名称
//                device.setName(obj.getString("name"));
//                //鉴定机构ID
//                //device.setOrgId(orgMainId);
//                //规格型号
//                device.setSpecification(obj.getString("ggxh"));
//                //编号
//                device.setNum(obj.getString("bh"));
//                //备注
//                device.setRemarks(obj.getString("bz"));
//                //保存检测设备及其软件信息表
//               // identifyOrgDeviceService.saveIdentifyOrgDevice(device);
//            }
//
//
//        } else if ("2".equals(sqlx)) { // 变更
//
//
//
//
//
//
//        } else if ("3".equals(sqlx)) { // 取消
//
//
//        } else {
//            throw new IllegalArgumentException("sqlx 必须是 1， 2， 3， 当前是： " + sqlx);
//        }
//
//        // 相关人员信息
//        JSONArray userArrayInfo = (JSONArray) params.get("xgryxx");
//
//        // 工作简历
//        JSONArray resumeArrayInfo = (JSONArray) params.get("gzjl");
//
//        // 鉴定人员名单
//        JSONArray identifyUserArrayInfo = (JSONArray) params.get("jdrytjb");
//
//        // 检测设备和软件列表
//        JSONArray devArrayInfo = (JSONArray) params.get("jdsbjgjsrjtjb");


        return ResponseEntity.ok();
    }
}
