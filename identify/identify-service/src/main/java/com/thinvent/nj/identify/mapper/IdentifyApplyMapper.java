package com.thinvent.nj.identify.mapper;

import com.thinvent.nj.identify.entity.IdentifyApply;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by HASEE on 2019/4/9.
 */
@Repository
public interface IdentifyApplyMapper extends CURDMapper<IdentifyApply,String>{
    String getMaxCaseNo(String formatterDate);
}
