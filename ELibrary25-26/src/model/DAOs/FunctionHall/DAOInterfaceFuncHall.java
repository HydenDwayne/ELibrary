package model.DAOs.FunctionHall;

import java.util.List;


public interface DAOInterfaceFuncHall {
    List<DAOFuncHall> checkDayForEvent(String facilityCode);
}