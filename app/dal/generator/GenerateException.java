package dal.generator;

/**
 * Created by Esteban Luchsinger on 08.04.2017.
 * An exception to be thrown while generating the demo-data.
 */
public class GenerateException extends Exception {

    public GenerateException(String message, Throwable innerException) {
        super(message, innerException);
    }

}
