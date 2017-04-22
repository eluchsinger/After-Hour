package config;

import dal.generator.GenerateException;

/**
 * Created by Esteban Luchsinger on 08.04.2017.
 * Configures the application after initialization.
 */
public interface StartupConfiguration {
    void init() throws GenerateException;
}
