package com.saferich.core.exception;

public class BaseException extends Exception {

    private static final long serialVersionUID = -463492418595191842L;

    public static final String SYSTEM_ERROR = "00001";

    public static final String ILLEGAL_OPERATION = "00002";

    public static final String NO_PERMISSION = "00003";

    public BaseException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BaseException(String errorCode) {
        super(errorCode);
    }

    public BaseException() {
        super();
    }

    public String getErrorCode() {
        return super.getMessage();
    }
}
