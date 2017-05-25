package models.exceptions;

/**
 * Created by Fabian on 15.05.17.
 */
public class UserWrongPasswordServerException extends ServerException{
    private static final int EXCEPTION_CODE = 107;
    private static final String EXCEPTION_MESSAGE = "User hat falsches Passwort eingegeben";

    protected UserWrongPasswordServerException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public UserWrongPasswordServerException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public UserWrongPasswordServerException(String message){
        this(EXCEPTION_CODE, message);
    }
}
