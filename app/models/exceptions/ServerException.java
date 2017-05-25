package models.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

/**
 * Created by Fabian on 11.05.17.
 */
@JsonAutoDetect(fieldVisibility = NONE, getterVisibility = NONE, setterVisibility = NONE)
public abstract class ServerException extends Exception{
    @JsonProperty("exceptionCode")
    protected final int exceptionCode;
    @JsonProperty("exceptionMessage")
    protected final String exceptionMessage;

    protected ServerException(int exceptionCode, String exceptionMessage){
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public int getExceptionCode(){
        return exceptionCode;
    }
    public String getExceptionMessage(){
        return exceptionMessage;
    }
}
