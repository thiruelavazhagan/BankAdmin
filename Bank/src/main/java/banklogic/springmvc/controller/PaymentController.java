package banklogic.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import banklogic.springmvc.model.User;
import banklogic.springmvc.service.UserService;

@Controller
public class PaymentController {
	
	 @Autowired
		public UserService userService;
		 public HashMap<String, String> map;
		 @RequestMapping(value = "/payment", method = RequestMethod.GET)
		  public ModelAndView user() {
		     User user = new User();	  
			 
			  ModelAndView modelAndView = new ModelAndView("payment", "command", user);
			  return modelAndView;
		  }

}
