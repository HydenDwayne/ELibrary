package model.DAOs.FacilityLogin;

import java.util.List;


public interface DAOInterfaceFacilityLogin {
    List<DAOFacilityLogin> getLoggedIn(String facilityCode);
}