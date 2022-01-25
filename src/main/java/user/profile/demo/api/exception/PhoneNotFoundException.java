package user.profile.demo.api.exception;

public class PhoneNotFoundException extends  RuntimeException {

    public PhoneNotFoundException() {
    }

    public PhoneNotFoundException(String message) {
        super("The phone with user-id" + message + "was not found");
    }

    public PhoneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhoneNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
