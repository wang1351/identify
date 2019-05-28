package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.mapper.IdentifyMainMapper;
import com.thinvent.nj.identify.service.IdentifyMainService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* T_IDENTIFY_MAIN服务实现
* @author administrator
* @date 2018-11-13
*/
@Service
public class IdentifyMainServiceImpl extends BaseCURDServiceImpl<IdentifyMain, String> implements IdentifyMainService {

    @Autowired
    private IdentifyMainMapper identifyMainMapper;


}
