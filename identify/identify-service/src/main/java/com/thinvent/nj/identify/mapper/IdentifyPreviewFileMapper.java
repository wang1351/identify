package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyPreviewFile;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* T_IDENTIFY_PREVIEW_FILE Mapper
* @author administrator
* @date 2018-11-21
*/
@Repository
public interface IdentifyPreviewFileMapper extends CURDMapper<IdentifyPreviewFile, String> {
    /**
     * 根据初勘ID获取初勘附件列表
     * @param previewId
     * @return
     */
    List<IdentifyPreviewFile> getByPreviewId(String previewId);

    /**
     * 保存初勘附件列表
     * @param totals
     */
    void insertFileList(List<IdentifyPreviewFile> totals);
}
