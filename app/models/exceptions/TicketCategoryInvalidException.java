package models.exceptions;

/**
 * Created by Fabian on 11.05.17.
 */
public class TicketCategoryInvalidException extends ServerException {
    private static int EXCEPTION_CODE = 102;
    private static String EXCEPTION_MESSAGE = "Ticketkategorie ist nicht verfügbar.";

    protected TicketCategoryInvalidException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public TicketCategoryInvalidException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public TicketCategoryInvalidException(String message){
        this(EXCEPTION_CODE, message);
    }
}
