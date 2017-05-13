package models.exceptions;

/**
 * Created by Fabian on 11.05.17.
 */
public class TicketAlreadyBoughtException extends ServerException {
    private static int EXCEPTION_CODE = 100;
    private static String EXCEPTION_MESSAGE = "Sie haben bereits ein Ticket gekauft.";

    protected TicketAlreadyBoughtException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public TicketAlreadyBoughtException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public TicketAlreadyBoughtException(String message){
        this(EXCEPTION_CODE, message);
    }
}
