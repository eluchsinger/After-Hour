package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import play.libs.Json;

/**
 * Created by Esteban Luchsinger on 03.04.2017.
 */
public class JavaJsonCustomObjectMapper {
    JavaJsonCustomObjectMapper() {
        ObjectMapper mapper = Json.newDefaultMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Json.setObjectMapper(mapper);
    }
}
