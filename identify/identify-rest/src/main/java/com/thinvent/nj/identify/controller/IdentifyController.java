package com.thinvent.nj.identify.controller;


import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.service.*;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.entity.Org;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.commons.collections.MapUtils;
import org.dozer.util.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : xujc
 * @date :2019/4/8
 * @Description : TODO
 */
@Controller
@RequestMapping("/internet")
public class IdentifyController extends BaseViewController {
    @Autowired
    private IdentifyOrgService identifyOrgService;

    @Autowired
    private DictService dictService;

    @Autowired
    private IdentifyApplyService applyService;

    @Autowired
    private OrgCreditService orgCreditService;

    /**
     * @author : xujc
     * @date :2019/4/9
     * @Description : 根据鉴定类型id找到鉴定单位
     */
    @ResponseBody
    @RequestMapping(path = "/identifyOrgs/{identifyTypeId}",method = RequestMethod.GET)
    public ResponseEntity getTypes(@PathVariable("identifyTypeId") String identifyTypeId){
        List<Map<String,Object>> list = new ArrayList<>();
        List<IdentifyOrgMain> orgs = identifyOrgService.getIdentifyOrgsByTypeId(identifyTypeId);
        for (IdentifyOrgMain identifyOrg: orgs){
            Map<String,Object> map =new HashMap<>();
            map.put("id",identifyOrg.getId());
            map.put("name",identifyOrg.getName());
            map.put("address",identifyOrg.getAddress());
            map.put("creditGradeName",identifyOrg.getCreditGradeName());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }
    /**
     * @author : xujc
     * @date :2019/4/9
     * @Description : 获取鉴定类型数据
     */
    @RequestMapping(path = "/identifyTypes", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findDictItems() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<DictItem> targets = dictService.getItemsByGroupKey("IDENTIFY_TYPE");
        for(DictItem dictItem:targets){
            Map<String,Object> map =new HashMap<>();
            map.put("id",dictItem.getId());
            map.put("name",dictItem.getName());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    @RequestMapping(path = "/apply",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) throws Exception{
        IdentifyApply identifyApply =new IdentifyApply();
        MapperUtil.copy(params,identifyApply);
        identifyApply.setCreateUsername("admin");
        String caseNo = applyService.generateCaseNumber();
        Integer identifySource = identifyApply.getIdentifySource();
        if(identifySource == 1){
            caseNo = "WDNJ"+caseNo;
        }else {
            caseNo = "FCWZW"+caseNo;
        }

        identifyApply.setIdentifyCode(caseNo);
        applyService.insert(identifyApply);
        return ResponseEntity.ok();
    }

    /**
     * @author : xujc
     * @date :2019/4/10
     * @Description : 获取机构详细数据
     * @param id: 机构id
     */
    @RequestMapping(path = "/org/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        IdentifyOrgMain org = identifyOrgService.get(id);

        Map<String,Object> map =new HashMap<>();

        Map<String,Object> result =new HashMap<>();
        map.put("identifyOrgId",id);
        List<OrgCredit> list = orgCreditService.findList(map);
        // 放入信用等级
        result.put("creditGradeName",list.get(0).getCreditGradeName());
       // 放入法人信息
        List<IdentifyOrgPerson> identifyOrgPersonList = org.getIdentifyOrgPersonList();
        for(IdentifyOrgPerson person:identifyOrgPersonList){
            if("1".equals(person.getType())){
                result.put("personName",person.getName());
                result.put("officePhone",person.getOfficePhone());
                result.put("phone",person.getPhone());
            }
        }

        // 放入机构信息
        result.put("orgName",org.getName());
        result.put("address",org.getAddress());
        // 鉴定业务信息
        String types ="";
        List<IdentifyOrgType> identifyOrgTypeList = org.getIdentifyOrgTypeList();
        for(IdentifyOrgType type:identifyOrgTypeList){
            types+=type.getName() + "、";
        }
        if(!StringUtil.isNullOrEmpty(types)){
            types= types.substring(0,types.length()-1);
        }
        result.put("types",types);
        result.put("buildDate",org.getBuildDate());
        result.put("unifiedCode",org.getUnifiedCode());
        result.put("registrationOffice",org.getRegistrationOffice());
        result.put("totalNum",org.getTotalNum());
        result.put("advPersonNum",org.getAdvPersonNum());
        result.put("midPersonNum",org.getMidPersonNum());
        result.put("level1Num",org.getLevel1Num());
        result.put("level2Num",org.getLevel2Num());
        result.put("rockNum",org.getRockNum());

        return ResponseEntity.ok(result);
    }





}
