import com.google.inject.AbstractModule;
import utils.JavaJsonCustomObjectMapper;

/**
 * Created by Esteban Luchsinger on 03.04.2017.
 */
public class JavaJsonCustomObjectMapperModule extends AbstractModule{
    protected void configure() {
        bind(JavaJsonCustomObjectMapper.class).asEagerSingleton();
    }
}
