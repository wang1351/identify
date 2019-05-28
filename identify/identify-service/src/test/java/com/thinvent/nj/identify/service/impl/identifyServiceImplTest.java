/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : identifyServiceImplTest.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・Administrator
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.service.IdentifyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author panqh
 * @date 2018-11-13
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class identifyServiceImplTest {

    @Autowired
    private IdentifyService identifyService;


    @Test
    public void testGet() {
        IdentifyMain identifyMain = identifyService.get("7B0006305354FC1BE05010AC0C054DDF");
        System.out.println(identifyMain);
    }


   @Test
    public void testGetIdentifyDangerHouseDetail() {
      /*  String reportDetailId = "7D58D7F00A84840AE05010AC0C054850";
        String result = identifyService.getIdentifyDangerHouseDetail(reportDetailId);
        Assert.assertNotNull(result);
        System.err.print(result);*/
    }
}
