import com.google.inject.AbstractModule;

import config.StartupConfiguration;
import config.StartupConfigurationImpl;
import dal.coatchecks.CoatChecksRepository;
import dal.coatchecks.CoatChecksRepositoryJPA;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryJPA;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryJPA;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryJPA;
import logic.coatchecks.CoatChecksLogic;
import logic.coatchecks.CoatChecksLogicImpl;
import logic.events.EventsLogic;
import logic.events.EventsLogicImpl;
import logic.sales.SalesLogic;
import logic.sales.SalesLogicImpl;
import logic.users.UsersLogic;
import logic.users.UsersLogicImpl;
import services.ApplicationTimer;
import services.AtomicCounter;
import services.Counter;

import java.time.Clock;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
        // Use the system clock as the default implementation of Clock
        bind(Clock.class).toInstance(Clock.systemDefaultZone());
        // Ask Guice to create an instance of ApplicationTimer when the
        // application starts.
        bind(ApplicationTimer.class).asEagerSingleton();
        // Set AtomicCounter as the implementation for Counter.
        bind(Counter.class).to(AtomicCounter.class);

        bind(UsersLogic.class).to(UsersLogicImpl.class);
        bind(EventsLogic.class).to(EventsLogicImpl.class);
        bind(SalesLogic.class).to(SalesLogicImpl.class);
        bind(CoatChecksLogic.class).to(CoatChecksLogicImpl.class);

        // Every time your class expects a UserRespository (as @Inject), it gets a JPA one.
        bind(UsersRepository.class).to(UsersRepositoryJPA.class).asEagerSingleton();
        bind(EventsRepository.class).to(EventsRepositoryJPA.class).asEagerSingleton();
        bind(TicketRepository.class).to(TicketRepositoryJPA.class).asEagerSingleton();
        bind(CoatChecksRepository.class).to(CoatChecksRepositoryJPA.class).asEagerSingleton();

        bind(StartupConfiguration.class).to(StartupConfigurationImpl.class).asEagerSingleton();
    }

}
