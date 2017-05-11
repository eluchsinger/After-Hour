package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryMock;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.sales.SalesLogic;
import logic.sales.SalesLogicImpl;
import logic.users.UsersLogic;
import models.events.TicketCategory;
import models.tickets.Ticket;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.api.test.Helpers;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian on 10.05.17.
 */
public class SalesLogicTest extends WithApplication {
    private SalesLogic salesLogic;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketRepositoryMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class).eagerly())
                .build();
    }


   @Before
    public void setup(){
       salesLogic = this.app.injector().instanceOf(SalesLogic.class);

   }

   @Test
   public void buyTicket() throws ParseException {
        Ticket ticket = salesLogic.buyTicket(2,1, dateFormat.parse("2017-4-22"));
        assertNotNull(ticket);
   }

   @Test
    public void buyTicketAlreadyBought(){
        Ticket ticket1 = salesLogic.buyTicket(1,1);
        assertNull(ticket1);
   }

}
