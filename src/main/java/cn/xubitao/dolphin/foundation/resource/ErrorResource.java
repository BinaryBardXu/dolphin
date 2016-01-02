package cn.xubitao.dolphin.foundation.resource;

/**
 * Created by xubitao on 1/1/16.
 */
public class ErrorResource extends RestResource {

    private String message;

    public ErrorResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RestResource error(String message) {
        return new ErrorResource(message);
    }
}
