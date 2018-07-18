package me.cc200.base.mvc;

import lombok.Builder;
import lombok.Data;

/**
 * Rest请求结果封装
 */
@Data
@Builder
public class Rest<T> {

    public static final int OK = 1;
    public static final int ERR = 0;
    public static final String OK_MSG = "OK";
    public static final String ERR_MSG = "ERR";

    /**
     * 请求返回码
     */
    int code;
    /**
     * 请求返回消息
     */
    String msg;
    /**
     * 业务返回码
     */
    String bizCode;
    /**
     * 业务返回消息
     */
    String bizMsg;
    /**
     * 返回数据
     */
    T data;

    /**
     * 成功返回
     * @param data 成功返回数据
     * @param <T> clz
     * @return Rest
     */
    public static <T> Rest<T> ok(T data) {
        return Rest.<T>builder().code(OK).msg(OK_MSG).data(data).build();
    }

    /**
     * 失败返回
     * @param err 失败原因
     * @param data 失败返回数据
     * @param <T> clz
     * @return Rest
     */
    public static <T> Rest<T> err(String err, T data) {
        return Rest.<T>builder().code(ERR).msg(err).data(data).build();
    }

    /**
     * 失败返回
     * @param bizCode 业务失败代码
     * @param bizMsg 业务失败原因
     * @param data 失败返回数据
     * @param <T> clz
     * @return Rest
     */
    public static <T> Rest<T> err(String bizCode, String bizMsg, T data) {
        return Rest.<T>builder().code(ERR).msg(ERR_MSG).bizCode(bizCode).bizMsg(bizMsg).data(data).build();
    }
}
