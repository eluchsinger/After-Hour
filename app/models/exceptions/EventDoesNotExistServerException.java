package models.exceptions;

/**
 * Created by Fabian on 15.05.17.
 */
public class EventDoesNotExistServerException extends ServerException {
    private static final int EXCEPTION_CODE = 106;
    private static final String EXCEPTION_MESSAGE = "Event exisitiert nicht.";

    protected EventDoesNotExistServerException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public EventDoesNotExistServerException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public EventDoesNotExistServerException(String message){
        this(EXCEPTION_CODE, message);
    }
}
