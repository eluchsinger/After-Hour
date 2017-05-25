package logic.coatchecks;

import models.tickets.CoatCheck;

import java.util.Date;

public interface CoatChecksLogic {

    CoatCheck createNewCoatCheck(String email, Integer coatHangerNumber, String locationName);

    Boolean fetchJacket(Date date, Integer coatHangerID);
}
