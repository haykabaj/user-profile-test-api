package user.profile.demo.api.exception;

public class UserUpdateException  extends RuntimeException{

    public UserUpdateException() {
    }

    public UserUpdateException(String message) {
        super(message);
    }

    public UserUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUpdateException(Throwable cause) {
        super(cause);
    }

    public UserUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
