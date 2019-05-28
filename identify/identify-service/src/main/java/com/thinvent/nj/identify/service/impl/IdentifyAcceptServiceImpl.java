package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.IdentifyAccept;
import com.thinvent.nj.identify.handler.BusinessHandler;
import com.thinvent.nj.identify.handler.impl.AcceptHandler;
import com.thinvent.nj.identify.mapper.IdentifyAcceptMapper;
import com.thinvent.nj.identify.service.IdentifyAcceptService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.uc.entity.User;
import com.thinvent.nj.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * T_IDENTIFY_ACCEPT服务实现
 *
 * @author xujc
 * @date 2018-11-21
 */
@Service
public class IdentifyAcceptServiceImpl extends BaseCURDServiceImpl<IdentifyAccept, String> implements IdentifyAcceptService {

    @Autowired
    private IdentifyAcceptMapper identifyAcceptMapper;

    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        BusinessHandler handler = new AcceptHandler(params);
        handler.execute();
    }

    @Override
    public IdentifyAccept getAcceptByMainId(String mainId) {
        IdentifyAccept accept = identifyAcceptMapper.getIdentifyAcceptByMainId(mainId);
        User user = userService.get(accept.getChargeId());
        accept.setUserName(user.getFullName());
        String joinPerson = accept.getJoinPerson();
        String[] split = {};
        if (!StringUtil.isNullOrEmpty(joinPerson)) {
            split = joinPerson.split(",");
        }

        String joinPersonName = "";
        for (String s : split) {
            User user1 = userService.get(s);
            joinPersonName += user1.getFullName();
            joinPersonName += ",";
        }
        if (!StringUtil.isNullOrEmpty(joinPersonName)) {
            joinPersonName = joinPersonName.substring(0, joinPersonName.length() - 1);
        }
        accept.setJoinPersonName(joinPersonName);
        return accept;
    }
}
