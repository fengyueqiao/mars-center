package io.github.fengyueqiao.marscenter.common.exception;


import io.github.fengyueqiao.marscenter.common.dto.ErrorCodeI;

public enum ErrorCode implements ErrorCodeI {

    E_Node_requestParamError ("E_Node_requestParamError", "请求参数错误"),
    E_Node_fileIOError ("E_Node_fileIOError", "文件读写错误"),
    E_Node_reqNodeError ("E_Node_reqNodeError", "请求node错误");

    private final String errCode;
    private final String errDesc;

    private ErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrDesc() {
        return errDesc;
    }
}