package banklogic.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import banklogic.springmvc.dao.UserDaoImpl;
import banklogic.springmvc.model.Login;
import banklogic.springmvc.model.User;
import banklogic.springmvc.service.UserService;

@Controller
public class AddAmountController {
	
	 @Autowired
	public UserService userService1;
	 public HashMap<String, String> map;
	 @RequestMapping(value = "/addamount", method = RequestMethod.GET)
	  public ModelAndView user() {
	     User user = new User();	  
		 
		  ModelAndView modelAndView = new ModelAndView("addamount", "command", user);
		  return modelAndView;
	  }
	
	 @ModelAttribute("countryList")
	   public Map<String, String> getCountryList() {
		 map = new HashMap<String, String>();
		   List<User>spinn = userService.list();
	      Map<String, String> countryList = new HashMap<String, String>();
	      for(int i=0;i<=spinn.size()-1;i++) {
	    	  countryList.put(spinn.get(i).getFirstname()+"@"+spinn.get(i).getAccno(), spinn.get(i).getAccno());
	    	  map.put(spinn.get(i).getAccno(), spinn.get(i).getTotalamnt());
	      }
	      return countryList;
	   }
	 
	 @RequestMapping(value = "/addamountprocess", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	      @ModelAttribute("add") User user) 
	  {
		 String[]arr=user.getAccno().split("@");
		
		String amount = map.get(arr[1]);
		amount = String.valueOf(Integer.parseInt(amount) + Integer.parseInt(user.getTotalamnt()));
		
		int s = userService.addAmount(arr[1], amount);
		user.setAccno("");user.setFirstname("");user.setTotalamnt("");
		return new ModelAndView("addamount","command",user);
	  }
	  

}
