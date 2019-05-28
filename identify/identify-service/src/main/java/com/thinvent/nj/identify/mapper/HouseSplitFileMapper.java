/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : HouseSplitFileMapper.java
 * 功能概要       :
 * 做成日期       : 2018-11-16・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.HouseSplit;
import com.thinvent.nj.identify.entity.HouseSplitFile;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author panqh
 * @date 2018-11-16
 */
@Repository
public interface HouseSplitFileMapper extends CURDMapper<HouseSplitFile, String> {

    void insertSplitFileList(@Param("splitFileList") List<HouseSplitFile> splitFileList);


    /**
     * 根据splitId获取房屋分栋信息附件列表
     * @author panqh
     * @date 2018-11-20
     * @param splitId
     * @return
     */
    List<HouseSplitFile> getSplitFileListBySplitId(String splitId);

}
