package models.exceptions;

/**
 * Created by Fabian on 11.05.17.
 */
public class UserDoesNotExistException extends ServerException {
    private static int EXCEPTION_CODE = 101;
    private static String EXCEPTION_MESSAGE = "User existiert nicht";

    protected UserDoesNotExistException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public UserDoesNotExistException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public UserDoesNotExistException(String message){
        this(EXCEPTION_CODE, message);
    }
}
