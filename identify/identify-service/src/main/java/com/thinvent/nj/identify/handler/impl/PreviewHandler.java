package com.thinvent.nj.identify.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.SpringContextUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.IdentifyPreview;
import com.thinvent.nj.identify.entity.IdentifyPreviewFile;
import com.thinvent.nj.identify.enums.RecordStatus;
import com.thinvent.nj.identify.handler.AbstractBusinessHandler;
import com.thinvent.nj.identify.mapper.IdentifyPreviewFileMapper;
import com.thinvent.nj.identify.mapper.IdentifyPreviewMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class PreviewHandler extends AbstractBusinessHandler {

    private IdentifyPreviewMapper identifyPreviewMapper = SpringContextUtil.getBean(IdentifyPreviewMapper.class);

    private IdentifyPreviewFileMapper previewFileMapper = SpringContextUtil.getBean(IdentifyPreviewFileMapper.class);

    public PreviewHandler(Map<String, Object> params) {
        super(params);
        mainId = (String) params.get("mainId");
    }

    @Override
    protected void save() {

        // 保存初勘表
        savePreview();

        // 更新鉴定主表
        updateIdentifyMain();
    }

    @Override
    protected void record() {
        internelRecord(RecordStatus.PREVIEW);
    }

    @Override
    protected void process() {
        completeTask(null);
    }

    private void savePreview() {
        // 初勘表
        IdentifyPreview preview = new IdentifyPreview();
        MapperUtil.copy(params, preview);
        preview.setCreateUsername(curFullName);
        preview.setCreateUser(curUserId);
        preview.setCreateTime(new Date());

        identifyPreviewMapper.insert(preview);

        savePreviewFileList(params, preview.getId());
    }


    private void updateIdentifyMain() {
        // 更新主表状态 -> 已初勘
        IdentifyMain identifyMain = identifyService.get(mainId);
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_CK);
        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);
    }


    private void savePreviewFileList(Map<String, Object> params, String id) {
        // 初勘附件表
        JSONArray livePhotos = (JSONArray) params.get("livePhotos");
        JSONArray dangerPhotos = (JSONArray) params.get("livePhotos");
        JSONArray files = (JSONArray) params.get("files");

        List<IdentifyPreviewFile> livePhotoList = getPreviewFileList(livePhotos, id, Constant.PREVIEW_FILE_TYPE_LIVE);
        List<IdentifyPreviewFile> dangerPhotoList = getPreviewFileList(dangerPhotos, id, Constant.PREVIEW_FILE_TYPE_DANGER);
        List<IdentifyPreviewFile> filesList = getPreviewFileList(files, id, Constant.PREVIEW_FILE_TYPE_FILES);

        List<IdentifyPreviewFile> totals = new ArrayList<>(livePhotoList.size() + dangerPhotoList.size() + files.size());
        totals.addAll(livePhotoList);
        totals.addAll(dangerPhotoList);
        totals.addAll(filesList);

        if (totals.size() > 0) {
            previewFileMapper.insertFileList(totals);
        }
    }

    private List<IdentifyPreviewFile> getPreviewFileList(JSONArray photos, String previewId, int type) {
        List<IdentifyPreviewFile> list = new ArrayList<>();

        if (photos == null) {
            return list;
        }

        IdentifyPreviewFile file;
        for (int index = 0; index < photos.size(); index ++) {
            file = new IdentifyPreviewFile();
            file.setFileId(photos.getString(index));
            file.setType(type);
            file.setPreviewId(previewId);
            file.setSort(index);
            file.setCreateTime(new Date());
            file.setDeletedFlag(Constant.DELETED_NO);
            list.add(file);
        }

        return list;
    }


}
