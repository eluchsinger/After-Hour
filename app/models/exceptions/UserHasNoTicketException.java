package models.exceptions;

/**
 * Created by Fabian on 15.05.17.
 */
public class UserHasNoTicketException extends ServerException{
    private static int EXCEPTION_CODE = 104;
    private static String EXCEPTION_MESSAGE = "User besitzt kein Ticket";

    protected UserHasNoTicketException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public UserHasNoTicketException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public UserHasNoTicketException(String message){
        this(EXCEPTION_CODE, message);
    }
}
