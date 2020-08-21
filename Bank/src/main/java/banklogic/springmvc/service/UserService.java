package banklogic.springmvc.service;

import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;

public interface UserService {

  int register(User user);

  User validateUser(Login login);
}
