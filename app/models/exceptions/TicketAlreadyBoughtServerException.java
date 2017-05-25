package models.exceptions;

/**
 * Created by Fabian on 11.05.17.
 */
public class TicketAlreadyBoughtServerException extends ServerException {
    private static final int EXCEPTION_CODE = 100;
    private static final String EXCEPTION_MESSAGE = "Sie haben bereits ein Ticket gekauft.";

    protected TicketAlreadyBoughtServerException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public TicketAlreadyBoughtServerException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public TicketAlreadyBoughtServerException(String message){
        this(EXCEPTION_CODE, message);
    }
}
