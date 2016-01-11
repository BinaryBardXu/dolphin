package cn.xubitao.dolphin.foundation.exceptions;

/**
 * Created by 15031046 on 2016/1/11.
 */
public class DolphinException extends RuntimeException {
    private static final long serialVersionUID = -8485124144566222888L;
    protected String errorCode;
    protected Object object;

    public DolphinException(Object object, String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.object = object;
    }

    public DolphinException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public DolphinException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public DolphinException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

    public DolphinException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
