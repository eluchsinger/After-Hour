package models.exceptions;

/**
 * Created by Fabian on 11.05.17.
 */
public class TicketCategoryInvalidServerException extends ServerException {
    private static final int EXCEPTION_CODE = 102;
    private static final String EXCEPTION_MESSAGE = "Ticketkategorie ist nicht verfügbar.";

    protected TicketCategoryInvalidServerException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public TicketCategoryInvalidServerException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public TicketCategoryInvalidServerException(String message){
        this(EXCEPTION_CODE, message);
    }
}
