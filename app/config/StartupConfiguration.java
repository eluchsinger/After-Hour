package config;

import dal.generator.GenerateException;

/**
 * Created by Esteban Luchsinger on 08.04.2017.
 */
public interface StartupConfiguration {
    void init() throws GenerateException;
}
