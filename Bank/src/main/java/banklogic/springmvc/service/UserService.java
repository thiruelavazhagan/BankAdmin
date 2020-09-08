package banklogic.springmvc.service;

import java.util.List;

import banklogic.springmvc.model.CountMap;
import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;

public interface UserService {

  int register(User user);
  
  int account(User user);

  User validateUser(Login login);

  List<User> list();

  int addAmount(String accno,String amount);
  
  List<CountMap> clist();
}
