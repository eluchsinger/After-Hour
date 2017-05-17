package models.exceptions;

/**
 * Created by Fabian on 15.05.17.
 */
public class UserWrongPasswordException extends ServerException{
    private static int EXCEPTION_CODE = 107;
    private static String EXCEPTION_MESSAGE = "User hat falsches Passwort eingegeben";

    protected UserWrongPasswordException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public UserWrongPasswordException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public UserWrongPasswordException(String message){
        this(EXCEPTION_CODE, message);
    }
}
