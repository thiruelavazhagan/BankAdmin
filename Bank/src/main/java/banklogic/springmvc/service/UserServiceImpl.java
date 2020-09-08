package banklogic.springmvc.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import banklogic.springmvc.dao.UserDao;
import banklogic.springmvc.model.CountMap;
import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;

public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;

	public int register(User user) {
		return userDao.register(user);
	}

	public int account(User user) {
		return userDao.account(user);
	}

	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	public int addAmount(String accno, String amount) {
		// TODO Auto-generated method stub
		return userDao.addAmount(accno, amount);
	}

	public List<CountMap> clist() {
		// TODO Auto-generated method stub
		return userDao.clist();
	}
	
	
	
}
