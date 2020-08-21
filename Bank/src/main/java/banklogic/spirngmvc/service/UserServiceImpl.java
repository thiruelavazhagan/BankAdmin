package banklogic.spirngmvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import banklogic.spirngmvc.dao.UserDao;
import banklogic.spirngmvc.model.Login;
import banklogic.spirngmvc.model.User;


public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public int register(User user) {
    return userDao.register(user);
  }

  public User validateUser(Login login) {
    return userDao.validateUser(login);
  }

}
