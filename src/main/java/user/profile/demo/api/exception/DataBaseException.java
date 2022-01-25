package user.profile.demo.api.exception;

import java.sql.SQLException;

public class DataBaseException  extends SQLException {

    public DataBaseException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public DataBaseException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public DataBaseException(String reason) {
        super(reason);
    }

    public DataBaseException() {
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    public DataBaseException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public DataBaseException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public DataBaseException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}
