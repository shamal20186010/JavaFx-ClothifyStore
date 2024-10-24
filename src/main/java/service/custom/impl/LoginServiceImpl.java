package service.custom.impl;

import dto.Login;
import entity.LoginEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.LoginDao;
import service.custom.LoginService;
import util.DaoType;
import util.HibernateUtil;

public class LoginServiceImpl implements LoginService {

    private LoginEntity login = null;
    private LoginDao loginDao = DaoFactory.getInstance().getServiceType(DaoType.LOGIN);

    @Override
    public LoginEntity createLogin(String username, String password) {
        Session session = HibernateUtil.getSession().getSessionFactory().openSession();
        try {
            System.out.println(username+" "+password);
            Query<LoginEntity> query = session.createQuery("FROM loginentity WHERE email = :username AND password = :password", LoginEntity.class);
            query.setParameter("email", username);
            query.setParameter("password", password);
            System.out.println(username+" "+password);
            return query.uniqueResult(); // Return user if found, otherwise null
        } finally {
            session.close();
        }
    }

    @Override
    public boolean createLogin(Login login) {
        LoginEntity entity = new ModelMapper().map(login, LoginEntity.class);
        return loginDao.save(entity);
    }

    @Override
    public LoginEntity verifyLogin(Login login) {
        LoginEntity entity = new ModelMapper().map(login,LoginEntity.class);
        LoginEntity search = loginDao.search(login.getEmail());
        return (search);
    }

    @Override
    public boolean validEmail(String email) {
        login = loginDao.search(email);
        if (null != login) {
            return login.getEmail().equals(email);
        }else {
            return false;
        }
    }

    @Override
    public boolean createPassword(String password) {
        System.out.println(this.login);
        loginDao.search(login.getEmail());
        return loginDao.update(new LoginEntity(login.getEmail(),password,login.getRole()));
    }
}
