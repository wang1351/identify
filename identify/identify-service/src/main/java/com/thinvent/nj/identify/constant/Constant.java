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
package com.thinvent.nj.identify.constant;

/**
 * 房安鉴定常量类
 * @author panqh
 * @date 2018-11-01
 */
public class Constant extends com.thinvent.nj.common.constant.Constant {

    /**
     * 鉴定科组织编码（1001003）
     */
    public static final String IDENTIFY_DEP_CODE = "1001003";

    /**
     * 危房业务来源（1：鉴定业务，2：巡排查业务）
     */
    public static final Integer BUSINESS_TYPE_IDENTIFY = 1;

    /**
     * 危房业务来源（1：鉴定业务，2：巡排查业务）
     */
    public static final Integer BUSINESS_TYPE_PARTROL = 2;

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
     * 系统字典组KEY：状态
     */
    public static final String XTZD_KEY_STATUS = "USE_STATUS";

    /**
     * 鉴定类别KEY（IDENTIFY_TYPE）
     */
    public static final String IDENTIFY_TYPE_KEY = "IDENTIFY_TYPE";

    /**
     * 房屋结构类别KEY（HOUSE_STRUCTURE）
     */
    public static final String HOUSE_STRUCTURE_KEY = "HOUSE_STRUCTURE";

    /**
     * 行政区划KEY（ZONE）
     */
    public static final String ADDRESS_ZONE_KEY = "ZONE";

    /**
     * 专家领域KEY（EXPERT_BUSINESS_AREA）
     */
    public static final String EXPERT_FIELD_KEY = "EXPERT_BUSINESS_AREA";

    /**
     * 街道KEY（STREET）
     */
    public static final String ADDRESS_STREET_KEY = "STREET";

    /**
     * 房屋鉴定结论KEY（STREET）
     */
    public static final String HOUSE_IDENTIFY_RESULT = "IDENTIFY_RESULT";

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
     * 鉴定业务当前状态：终止流程
     */
    public static final Integer IDENTIFY_STATUS_ENDING = 12;

    /**
     * 鉴定业务当前状态：挂起流程
     */
    public static final Integer IDENTIFY_STATUS_SUSPEND = 13;

    /**
     * 鉴定业务当前状态：解除挂起流程
     */
    public static final Integer IDENTIFY_STATUS_RELEASE_SUSPEND = 14;

    /**
     * 鉴定业务当前状态：拒绝申请
     */
    public static final Integer IDENTIFY_STATUS_PHONE_JJSQ = 15;
    /**
     * 鉴定业务流程状态：进行中
     */
    public static final Integer IDENTIFY_PROCESS_RUNNING = 0;

    /**
     * 鉴定业务流程状态：已挂起
     */
    public static final Integer IDENTIFY_PROCESS_SUSPEND = 1;

    /**
     * 鉴定业务流程状态：已终止
     */
    public static final Integer IDENTIFY_PROCESS_STOP = 2;

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
     * 委托人产权性质：产权性质（1: 产权人； 2: 使用人； 3: 第三方）
     */
    public static final Integer CLIENT_NATURE_CQR = 1;

    /**
     * 委托人产权性质：产权性质（1: 产权人； 2: 使用人； 3: 第三方）
     */
    public static final Integer CLIENT_NATURE_SYR = 2;

    /**
     * 委托人产权性质：产权性质（1: 产权人； 2: 使用人； 3: 第三方）
     */
    public static final Integer CLIENT_NATURE_DSF = 3;

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
     * 初勘附件类型：现场照片
     */
    public static final Integer PREVIEW_FILE_TYPE_LIVE = 1;

    /**
     * 初勘附件类型：险情照片
     */
    public static final Integer PREVIEW_FILE_TYPE_DANGER = 2;

    /**
     * 初勘附件类型：附档文件
     */
    public static final Integer PREVIEW_FILE_TYPE_FILES = 3;

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
     * 编制报告上传方式： 1 -> 分栋上传
     */
    public static final Integer REPORT_METHOD_SPLIT = 1;

    /**
     * 编制报告上传方式： 2 -> 汇总上传
     */
    public static final Integer REPORT_METHOD_ALL = 2;

    public static final Integer USER_STATUS_YES = 1;

    public static final Integer USER_STATUS_NO = 0;

    /**
     * @author : xujc
     * @date :2019/5/14
     * @Description : 手机申请状态
     */
    public static final Integer PHONE_APPLY_WSQ = 0;
    public static final Integer PHONE_APPLY_YSQ = 1;
    public static final Integer PHONE_APPLY_YJJ = 2;
}
