package model.DAOs.LoginDAO;

import java.util.List;


public interface DAOInterfaceLogin {
    boolean checkCredentials(String username, String password);
}