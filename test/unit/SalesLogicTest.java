package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.coatchecks.CoatChecksRepository;
import dal.coatchecks.CoatChecksRepositoryMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryMock;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.sales.SalesLogic;
import models.exceptions.ServerException;
import models.exceptions.TicketAlreadyBoughtServerException;
import models.exceptions.TicketCategoryInvalidServerException;
import models.exceptions.UserDoesNotExistServerException;
import models.tickets.Ticket;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.ParseException;

import static org.junit.Assert.assertNotNull;
import static play.inject.Bindings.bind;
import static utils.DateGenerator.generateDate;

public class SalesLogicTest extends WithApplication {
    private SalesLogic salesLogic;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketRepositoryMock.class))
                .overrides(bind(CoatChecksRepository.class).to(CoatChecksRepositoryMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class).eagerly())
                .build();
    }

   @Before
    public void setup(){
       salesLogic = this.app.injector().instanceOf(SalesLogic.class);

   }

   @Test
   public void testBuyTicket() throws ParseException, ServerException {
        Ticket ticket = salesLogic.buyTicket(2,1, generateDate(2));
        assertNotNull(ticket);
   }

   @Test (expected = TicketAlreadyBoughtServerException.class)
    public void testBuyTicketAlreadyBought() throws ServerException {
        salesLogic.buyTicket(1,1);
   }

   @Test (expected = TicketCategoryInvalidServerException.class)
    public void testBuyTicketNotExistingTicketCategory() throws ServerException {
        salesLogic.buyTicket(1,100);
   }

   @Test (expected = TicketCategoryInvalidServerException.class)
    public void testBuyTicketNotAvailableTicketCategory() throws ServerException, ParseException {
        salesLogic.buyTicket(1,2, generateDate(2));
   }

   @Test (expected = UserDoesNotExistServerException.class)
    public void testBuyTicketWithNotExistingUser() throws ParseException, ServerException {
       salesLogic.buyTicket(1313,1);
   }

   @Test (expected = TicketAlreadyBoughtServerException.class)
    public void testBuyTicketDiffrentTicketCategories() throws ParseException, ServerException {
       salesLogic.buyTicket(5,9, generateDate(2));
       salesLogic.buyTicket(5,10, generateDate(2));
   }

}
