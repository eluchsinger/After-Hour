package models.exceptions;

/**
 * Created by Fabian on 11.05.17.
 */
public class UserDoesNotExistServerException extends ServerException {
    private static final int EXCEPTION_CODE = 101;
    private static final String EXCEPTION_MESSAGE = "User existiert nicht";

    protected UserDoesNotExistServerException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public UserDoesNotExistServerException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public UserDoesNotExistServerException(String message){
        this(EXCEPTION_CODE, message);
    }
}
