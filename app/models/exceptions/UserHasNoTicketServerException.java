package models.exceptions;

/**
 * Created by Fabian on 15.05.17.
 */
public class UserHasNoTicketServerException extends ServerException{
    private static final int EXCEPTION_CODE = 104;
    private static final String EXCEPTION_MESSAGE = "User besitzt kein Ticket";

    protected UserHasNoTicketServerException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public UserHasNoTicketServerException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public UserHasNoTicketServerException(String message){
        this(EXCEPTION_CODE, message);
    }
}
