package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyAccept;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyPreview;

/**
* T_IDENTIFY_PREVIEW Mapper
* @author wangwj
* @date 2018-11-21
*/
@Repository
public interface IdentifyPreviewMapper extends CURDMapper<IdentifyPreview, String> {
    /**
     * 根据主表ID查询初勘表
     */

    IdentifyPreview getIdentifyPreviewByMainId(String mainId);


}
