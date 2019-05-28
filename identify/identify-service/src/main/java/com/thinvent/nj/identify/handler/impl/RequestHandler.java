package com.thinvent.nj.identify.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.SpringContextUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.enums.RecordStatus;
import com.thinvent.nj.identify.handler.AbstractBusinessHandler;
import com.thinvent.nj.identify.mapper.*;

import java.util.*;


public class RequestHandler extends AbstractBusinessHandler {

    private HouseMapper houseMapper = SpringContextUtil.getBean(HouseMapper.class);

    private HouseSplitMapper houseSplitMapper = SpringContextUtil.getBean(HouseSplitMapper.class);

    private HouseSplitFileMapper houseSplitFileMapper = SpringContextUtil.getBean(HouseSplitFileMapper.class);

    private NewProjectMapper newProjectMapper = SpringContextUtil.getBean(NewProjectMapper.class);

    private NewProFileMapper newProFileMapper = SpringContextUtil.getBean(NewProFileMapper.class);

    private ClientMapper clientMapper = SpringContextUtil.getBean(ClientMapper.class);

    private ClientFileMapper clientFileMapper = SpringContextUtil.getBean(ClientFileMapper.class);

    private String houseId;


    public RequestHandler(Map<String, Object> params) {
        super(params);
    }

    @Override
    protected void save() {
        // 保存鉴定主表
        saveMain();

        // 保存委托人信息表
        saveClient();

        // 保存房屋基本信息
        saveHouse();

        // 保存房屋分栋列表
        saveSplits();

        // 保存新建工程
        saveNewProject();
    }

    @Override
    protected void record() {
        internelRecord(RecordStatus.REQUEST);
    }

    @Override
    protected void process() {
        startProcess();
    }


     // 保存鉴定主表
    private void saveMain() {
        JSONObject identifyContentObject = (JSONObject) params.get("identifyContent");

        String content = identifyContentObject.getString("content");
        String ratingLevel = identifyContentObject.getString("ratingLevel");
        String ratingType = identifyContentObject.getString("ratingType");
        String otherContent = identifyContentObject.getString("otherContent");

        // 鉴定业务主表
        IdentifyMain identify = new IdentifyMain();

        identify.setCaseNo((String)params.get("caseNo"));
        identify.setOrgId(curOrgId);
        identify.setStatus(Constant.IDENTIFY_STATUS_SQ);
        identify.setSuspend(Constant.IDENTIFY_PROCESS_RUNNING);
        Integer source = (Integer)params.get("source");
        if(source ==1){
            identify.setMethod(Constant.IDENTIFY_METHOD_WDNJ);
        }else if(source ==2){
            identify.setMethod(Constant.IDENTIFY_METHOD_FCZW);
        }else {
            identify.setMethod(Constant.IDENTIFY_METHOD_RGCK);
        }
        identify.setContent(content);
        identify.setRatingLevel(ratingLevel);
        identify.setRatingType(ratingType);
        identify.setOtherContent(otherContent);
        identify.setIsReview(Constant.IDENTIFY_REVIEW_NO);
        identify.setIsCorrect(Constant.IDENTIFY_CORRECT_NO);
        identify.setRequestTime(new Date());
        identify.setOperatorTime(new Date());

        IdentifyMain identifyMain = identifyMainService.insert(identify);

        mainId = identifyMain.getId();
    }

    // 委托人信息表
    private void saveClient() {
        Client client = new Client();
        client.setMainId(mainId);
        JSONObject clientObject = (JSONObject) params.get("client");
        JSONArray certificateFile = clientObject.getJSONArray("certificateFile");
        JSONArray IDFile = clientObject.getJSONArray("IDFile");
        JSONArray otherFile = clientObject.getJSONArray("otherFile");
        JSONArray clientIdentifyFile = clientObject.getJSONArray("clientIdentifyFile");
        client.setClientIdentifyFileId(clientIdentifyFile.getString(0));

        MapperUtil.copy(clientObject, client);
        clientMapper.insert(client);

        String clientId = client.getId();

        // 委托人附件信息表
        if (certificateFile != null && certificateFile.size() > 0) {
            clientFileMapper.insertClientFileList(creatClientFileList(clientId, certificateFile, Constant.CLIENT_FILE_TYPE_CQZ));
        }
        if (IDFile != null && IDFile.size() > 0) {
            clientFileMapper.insertClientFileList(creatClientFileList(clientId, IDFile, Constant.CLIENT_FILE_TYPE_ID));
        }
        if (otherFile != null && otherFile.size() > 0) {
            clientFileMapper.insertClientFileList(creatClientFileList(clientId, otherFile, Constant.CLIENT_FILE_TYPE_QT));
        }

    }

    /**
     * 创建委托人附件信息
     * @param clientId:委托人ID
     * @param array:附件ID数组
     * @param type：存储附件类型
     */
    private List<ClientFile> creatClientFileList(String clientId, JSONArray array, Integer type) {
        List<ClientFile> lstClientFile = new ArrayList<>();
        if (array != null && array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                ClientFile clientFile = new ClientFile();
                clientFile.setClientId(clientId);
                clientFile.setFileId(array.getString(i));
                clientFile.setType(type);
                clientFile.setSort(i + 1);

                lstClientFile.add(clientFile);
            }
        }
        return lstClientFile;
    }

    // 保存房屋基本信息
    private void saveHouse() {
        House house = new House();
        house.setMainId(mainId);
        JSONObject houseObject = (JSONObject) params.get("houseObject");

        MapperUtil.copy(houseObject, house);
        houseMapper.insert(house);

        this.houseId = house.getId();
    }

    // 保存房屋分栋列表
    private void saveSplits() {
        JSONArray splitArray = (JSONArray) params.get("splitArray");

        for (int i = 0; i < splitArray.size(); i++) {
            HouseSplit houseSplit = new HouseSplit();
            houseSplit.setHouseId(houseId);
            houseSplit.setSort(i + 1);
            JSONObject houseSplitObject = splitArray.getJSONObject(i);
            MapperUtil.copy(houseSplitObject, houseSplit);
            houseSplitMapper.insert(houseSplit);

            String splitId = houseSplit.getId();

            JSONArray houseSplitFileArray = houseSplitObject.getJSONArray("constructFile");

            List<HouseSplitFile> lstHouseSplitFile = new ArrayList<>();
            // 分栋信息附件表
            if (houseSplitFileArray != null && houseSplitFileArray.size() > 0) {
                for (int j = 0; j < houseSplitFileArray.size(); j++) {
                    HouseSplitFile houseSplitFile = new HouseSplitFile();
                    houseSplitFile.setSplitId(splitId);
                    houseSplitFile.setFileId(houseSplitFileArray.getString(j));
                    houseSplitFile.setType(Constant.SPLIT_FILE_TYPE_TZCL);
                    houseSplitFile.setSort(j + 1);

                    lstHouseSplitFile.add(houseSplitFile);
                }
                houseSplitFileMapper.insertSplitFileList(lstHouseSplitFile);
            }
        }
    }

    // 保存新建工程
    private void saveNewProject() {
        // 房屋新建工程表
        // 判断鉴定内容是否为“施工对相邻房屋影响鉴定”
        JSONObject identifyContentObject = (JSONObject) params.get("identifyContent");
        String content = identifyContentObject.getString("content");

        if (!"7".equals(content)) {
            return;
        }

        NewProject project = new NewProject();
        project.setMainId(mainId);
        JSONObject projectObject = (JSONObject) params.get("newPro");
        MapperUtil.copy(projectObject, project);
        newProjectMapper.insert(project);

        String projectId = project.getId();

        // 房屋新建工程附件表
        JSONArray clientFile = projectObject.getJSONArray("clientFile");
        JSONArray reportFile = projectObject.getJSONArray("reportFile");
        JSONArray structureFile = projectObject.getJSONArray("structureFile");
        JSONArray foundationFile = projectObject.getJSONArray("foundationFile");
        JSONArray identifyHouseFile = projectObject.getJSONArray("identifyHouseFile");
        if (clientFile != null && clientFile.size() > 0) {
            newProFileMapper.insertNewProFileList(creatProjectFileList(projectId, clientFile, Constant.NEWPRO_FILE_TYPE_WTS));
        }
        if (reportFile != null && reportFile.size() > 0) {
            newProFileMapper.insertNewProFileList(creatProjectFileList(projectId, reportFile, Constant.NEWPRO_FILE_TYPE_BG));
        }
        if (structureFile != null && structureFile.size() > 0) {
            newProFileMapper.insertNewProFileList(creatProjectFileList(projectId, structureFile, Constant.NEWPRO_FILE_TYPE_XJTZ));
        }
        if (foundationFile != null && foundationFile.size() > 0) {
            newProFileMapper.insertNewProFileList(creatProjectFileList(projectId, foundationFile, Constant.NEWPRO_FILE_TYPE_ZHFA));
        }
        if (identifyHouseFile != null && identifyHouseFile.size() > 0) {
            newProFileMapper.insertNewProFileList(creatProjectFileList(projectId, identifyHouseFile, Constant.NEWPRO_FILE_TYPE_JDTZ));
        }
    }

    /**
     * 创建新建工程附件信息
     * @author panqh
     * @date 2018-11-16
     * @param projectId：新建工程项目ID
     * @param array：新建工程附件数组
     * @param type：新建工程附件类型
     */
    private List<NewProFile> creatProjectFileList(String projectId, JSONArray array, Integer type) {
        List<NewProFile> lstNewProFile = new ArrayList<>();
        if (array != null && array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                NewProFile proFile = new NewProFile();
                proFile.setNewProjectId(projectId);
                proFile.setFileId(array.getString(i));
                proFile.setType(type);
                proFile.setSort(i + 1);

                lstNewProFile.add(proFile);
            }
        }
        return lstNewProFile;
    }


}
