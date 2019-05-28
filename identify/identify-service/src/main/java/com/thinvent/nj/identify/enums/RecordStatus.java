package com.thinvent.nj.identify.enums;

public enum RecordStatus {
    REQUEST("申请", 1),
    ACCEPT("受理", 2),
    PREVIEW("初勘", 3),
    PLAN("出具方案", 4),
    CONTRACT("签订合同", 5),
    REPORT("编制报告", 6),
    VERIFY("编制报告", 7),
    SIGN("签发", 8),
    SEND("发放", 9),
    UN_ACCEPT("不受理", 10),
    VERIFY_FAIL("审核不通过", 11),
    STOP("终止流程", 12),
    SUSPEND("挂起", 13),
    RELEASE("解除挂起", 14);

    private Integer num;

    private String name;

    RecordStatus(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
