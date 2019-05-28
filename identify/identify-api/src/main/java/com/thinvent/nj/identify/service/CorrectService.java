/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectService.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* 补正业务表服务
* @author panqh
* @date 2018-12-5
*/
public interface CorrectService extends CURDService<Correct, String> {

    /**
     * 保存补正变更申请信息
     * @author panqh
     * @date 2018-12-05
     * @param mainId
     * @param params
     */
    void saveCorrectRequestInfo(String mainId, Map<String, Object> params);

    /**
     * 保存补正变更审核信息
     * @author panqh
     * @date 2018-12-05
     * @param params
     */
    void saveCorrectCheckInfo(Map<String, Object> params);

    /**
     * 保存补正变更上传资料信息
     * @author panqh
     * @date 2018-12-05
     * @param params
     */
     void saveCorrectUploadInfo(Map<String, Object> params);





}
