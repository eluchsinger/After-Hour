package config;

import dal.generator.DataGenerator;
import dal.generator.GenerateException;
import play.Logger;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

/**
 * Created by Esteban Luchsinger on 08.04.2017.
 * Configures the application after initialization
 * using the default "production" configuration.
 */
public class StartupConfigurationImpl implements StartupConfiguration {

    private final DataGenerator dataGenerator;
    private final JPAApi jpaApi;

    @Inject
    public StartupConfigurationImpl(final DataGenerator dataGenerator,
                                    final JPAApi jpaApi) throws GenerateException {
        this.dataGenerator = dataGenerator;
        this.jpaApi = jpaApi;
        init();
    }

    @Override
    public void init() throws GenerateException {
        this.jpaApi.withTransaction(() -> {
            try {
                this.dataGenerator.initializeData();
            }
            catch(Exception exception) {
                Logger.error(exception.getMessage());
            }
        });
    }
}
