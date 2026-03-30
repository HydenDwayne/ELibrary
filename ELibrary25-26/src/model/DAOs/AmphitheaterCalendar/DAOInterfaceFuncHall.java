package model.DAOs.AmphitheaterCalendar;

import java.util.List;


public interface DAOInterfaceFuncHall {
    List<DAOFuncHall> checkDayForEvent(String facilityCode);
}