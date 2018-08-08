package com.zph.cralwer.exception;

/**
 * 爬虫框架内部异常
 */
public class CrawlerException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public CrawlerException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public static CrawlerException newInstance(int code, String msgFormat, Object... args) {
        return new CrawlerException(code, msgFormat, args);
    }

}
    