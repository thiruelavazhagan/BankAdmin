package banklogic.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import banklogic.springmvc.dao.UserDao;
import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;


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
