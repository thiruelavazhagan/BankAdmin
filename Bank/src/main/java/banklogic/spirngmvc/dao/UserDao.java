package banklogic.spirngmvc.dao;

import banklogic.spirngmvc.model.Login;
import banklogic.spirngmvc.model.User;

public interface UserDao {

  int register(User user);

  User validateUser(Login login);
}
