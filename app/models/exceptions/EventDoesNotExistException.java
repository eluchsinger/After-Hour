package models.exceptions;

/**
 * Created by Fabian on 15.05.17.
 */
public class EventDoesNotExistException extends ServerException {
    private static int EXCEPTION_CODE = 106;
    private static String EXCEPTION_MESSAGE = "Event exisitiert nicht.";

    protected EventDoesNotExistException(int exceptionCode, String exceptionMessage) {
        super(exceptionCode, exceptionMessage);
    }

    public EventDoesNotExistException(){
        this(EXCEPTION_CODE, EXCEPTION_MESSAGE);
    }

    public EventDoesNotExistException(String message){
        this(EXCEPTION_CODE, message);
    }
}
