package com.an9elkiss.commons.command;

public interface Status {

    Integer SUCCESS_CODE = 200;
    String SUCCESS_MESSAGE = "操作成功";

    Integer ACCESS_DENY_CODE = 500;
    String ACCESS_DENY_MESSAGE = "拒绝访问";

    Integer getCode();

    String getMessage();

}
