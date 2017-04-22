package config;

import dal.generator.DataGenerator;
import dal.generator.GenerateException;
import play.Logger;

import javax.inject.Inject;

/**
 * Created by Fabian Schwyter on 17.04.2017.
 * Configures the application after initialization
 * using the default "mock" configuration.
 */
public class StartupConfigurationMock implements StartupConfiguration{
    private final DataGenerator dataGenerator;

    @Inject
    public StartupConfigurationMock(final DataGenerator dataGenerator) throws GenerateException {
        this.dataGenerator = dataGenerator;
        init();
    }

    @Override
    public void init() throws GenerateException {
        try {
            this.dataGenerator.initializeData();
        }
        catch(Exception exception) {
            Logger.error(exception.getMessage());
        }
    }
}
