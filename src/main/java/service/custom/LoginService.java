package service.custom;

import dto.Login;
import entity.LoginEntity;
import service.SuperService;

public interface LoginService extends SuperService {
    LoginEntity createLogin(String username, String password);
    boolean createLogin(Login login);
    boolean verifyLogin(Login login);
    boolean validEmail(String email);
    boolean createPassword(String password);
}
