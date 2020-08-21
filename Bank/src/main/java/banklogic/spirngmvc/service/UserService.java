package banklogic.spirngmvc.service;

import banklogic.spirngmvc.model.Login;
import banklogic.spirngmvc.model.User;

public interface UserService {

  int register(User user);

  User validateUser(Login login);
}
