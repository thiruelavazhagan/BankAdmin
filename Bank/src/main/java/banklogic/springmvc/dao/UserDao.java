package banklogic.springmvc.dao;

import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;

public interface UserDao {

  int register(User user);

  User validateUser(Login login);
}
