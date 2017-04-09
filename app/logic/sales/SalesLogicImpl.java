package logic.sales;

import models.events.TicketCategory;

/**
 * Created by Fabian on 08.04.17.
 */
public class SalesLogicImpl implements SalesLogic{

    @Override
    public boolean validateTicket(Integer userId, Integer ticketCategoryId) {
        return false;
    }

    @Override
    public boolean buyTicket(Integer userId, Integer ticketCategoryId) {
        return false;
    }
}
