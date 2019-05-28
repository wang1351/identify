/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : Constant.java
 * 功能概要       :
 * 做成日期       : 2018-11-01・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.expert.constant;

/**
 * 房安鉴定常量类
 * @author panqh
 * @date 2018-11-01
 */
public final class Constant {

    /**
     * 鉴定科组织编码（1001003）
     */
    public static final String IDENTIFY_DEP_CODE = "1001003";

    /**
     * 逻辑删除标记（0:未删除；1:已删除）
     */
    public static final Integer DELETED_YES = 1;

    /**
     * 逻辑删除标记（0:未删除；1:已删除）
     */
    public static final Integer DELETED_NO = 0;

    /**
     * 审核状态（1:通过，2:不通过）
     */
    public static final Integer CHECKED_YES = 1;

    /**
     * 审核状态（1:通过，2:不通过）
     */
    public static final Integer CHECKED_NO = 2;

    /**
     * 鉴定机构启用状态：（0:禁用；1:启用）
     */
    public static final Integer USE_STATUS_NO = 0;

    /**
     * 鉴定机构启用状态：（0:禁用；1:启用）
     */
    public static final Integer USE_STATUS_YSE = 1;

    /**
     * 鉴定机构同步至UC状态（0:未同步；1:已同步）
     */
    public static final Integer SYNC_STATUS_NO = 0;

    /**
     * 鉴定机构同步至UC状态（0:未同步；1:已同步）
     */
    public static final Integer SYNC_STATUS_YSE = 1;


    /**
     * 系统字典组KEY：职称
     */
    public static final String XTZD_KEY_TITLE = "TITLE";

    /**
     * 系统字典组KEY：行业执业资格
     */
    public static final String XTZD_KEY_PRACTICE = "PRACTICE";

    /**
     * 系统字典组KEY：专家状态
     */
    public static final String XTZD_KEY_STATUS = "USE_STATUS";


    /**
     * 鉴定类别KEY（IDENTIFY_TYPE）
     */
    public static final String IDENTIFY_TYPE_KEY = "IDENTIFY_TYPE";

    /**
     * 专家领域KEY（EXPERT_BUSINESS_AREA）
     */
    public static final String EXPERT_FIELD_KEY = "EXPERT_BUSINESS_AREA";

    /**
     * 鉴定业务当前状态：已申请
     */
    public static final Integer IDENTIFY_STATUS_SQ = 1;

    /**
     * 鉴定业务当前状态：已受理
     */
    public static final Integer IDENTIFY_STATUS_SL = 2;

    /**
     * 鉴定业务当前状态：已初勘
     */
    public static final Integer IDENTIFY_STATUS_CK = 3;

    /**
     * 鉴定业务当前状态：已出具方案
     */
    public static final Integer IDENTIFY_STATUS_FA = 4;

    /**
     * 鉴定业务当前状态：已签订合同
     */
    public static final Integer IDENTIFY_STATUS_HT = 5;

    /**
     * 鉴定业务当前状态：已编制报告
     */
    public static final Integer IDENTIFY_STATUS_BG = 6;

    /**
     * 鉴定业务当前状态：已审核
     */
    public static final Integer IDENTIFY_STATUS_SH = 7;

    /**
     * 鉴定业务当前状态：已签发
     */
    public static final Integer IDENTIFY_STATUS_QF = 8;

    /**
     * 鉴定业务当前状态：已发放
     */
    public static final Integer IDENTIFY_STATUS_FF = 9;

    /**
     * 鉴定业务当前状态：未受理
     */
    public static final Integer IDENTIFY_STATUS_WSL = 10;

    /**
     * 鉴定业务当前状态：审核不通过
     */
    public static final Integer IDENTIFY_STATUS_SHBG = 11;

    /**
     * 鉴定申请方式：人工窗口
     */
    public static final Integer IDENTIFY_METHOD_RGCK = 1;

    /**
     * 鉴定申请方式：我的南京
     */
    public static final Integer IDENTIFY_METHOD_WDNJ = 2;

    /**
     * 鉴定申请方式：房产微政务
     */
    public static final Integer IDENTIFY_METHOD_FCZW = 3;

    /**
     * 是否复核(0: 否；1:是)
     */
    public static final Integer IDENTIFY_REVIEW_NO = 0;

    /**
     * 是否复核(0: 否；1:是)
     */
    public static final Integer IDENTIFY_REVIEW_YES = 1;

    /**
     * 是否补正(0: 否；1:是)
     */
    public static final Integer IDENTIFY_CORRECT_NO = 0;

    /**
     * 是否补正(0: 否；1:是)
     */
    public static final Integer IDENTIFY_CORRECT_YES = 1;

    /**
     * 委托人附件类型：房屋产权证
     */
    public static final Integer CLIENT_FILE_TYPE_CQZ = 1;

    /**
     * 委托人附件类型：身份证
     */
    public static final Integer CLIENT_FILE_TYPE_ID = 2;

    /**
     * 委托人附件类型：其他
     */
    public static final Integer CLIENT_FILE_TYPE_QT = 3;

    /**
     * 分栋信息附件类型：图纸材料
     */
    public static final Integer SPLIT_FILE_TYPE_TZCL = 1;

    /**
     * 新建工程附件类型：房屋安全鉴定委托书附表
     */
    public static final Integer NEWPRO_FILE_TYPE_WTS = 1;

    /**
     * 新建工程附件类型：岩石工程勘察报告
     */
    public static final Integer NEWPRO_FILE_TYPE_BG = 2;

    /**
     * 新建工程附件类型：新建工程建筑及结构图纸
     */
    public static final Integer NEWPRO_FILE_TYPE_XJTZ = 3;

    /**
     * 新建工程附件类型：新建工程基坑支护方案
     */
    public static final Integer NEWPRO_FILE_TYPE_ZHFA = 4;

    /**
     * 新建工程附件类型：所需鉴定房屋建筑及结构图纸
     */
    public static final Integer NEWPRO_FILE_TYPE_JDTZ = 5;

    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YSQ = 1;

    /**
     * 复核业务状态（1复核申请，2：受理意见 3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YSL = 2;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YXZZJ = 3;

    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YQRZJ = 4;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YTXYJ = 5;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YFF = 6;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_BSL= 10;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YZZLC= 12;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YGQLC= 13;
    /**
     * 复核业务状态（1复核申请，2：受理意见，3：选择专家，4：确认专家 5：专家意见 6: 发放 10：不受理 12：终止流程 13：挂起流程 14： 解除挂起）
     */
    public static final Integer REVIEW_STATUS_YJCGQ= 14;

    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_YSQ = 1;

    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_YSH = 2;

    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_YBZ= 3;
    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_SHBTG= 11;

    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_ZZLC = 12;

    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_GQLC = 13;

    /**
     * 补正业务"状态（1: 申请；2: 审核；3: 补正；11:审核不通过 12: 终止流程; 13: 挂起流程；14: 解除挂起）"
     */
    public static final Integer CORRECT_STATUS_JCGQ = 14;



    /**
     * 保存标识：保存
     */
    public static final Integer IDENTIFY_REQUEST_SAVE = 1;

    /**
     * 保存标识：保存并导出
     */
    public static final Integer IDENTIFY_REQUEST_SAVEANDEXPORT = 2;

    /**
     * 初勘员角色
     */
    public static final String ROLE_CODE_PREVIEW = "ROLE_PREVIEW";


}
