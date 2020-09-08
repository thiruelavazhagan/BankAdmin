package banklogic.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import banklogic.springmvc.model.CountMap;
import banklogic.springmvc.model.User;
import banklogic.springmvc.service.UserService;


@Controller
public class RegistrationController {
  @Autowired
  public UserService userService;
  boolean error=false;
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("user", new User());
    
    
    return mav;
  }

  @ModelAttribute("cList")
  public Map<String, String> getCountryList() {
	   List<CountMap>spinn = userService.clist();
     Map<String, String> cList = new HashMap<String, String>();
     for(int i=0;i<=spinn.size()-1;i++) {
   	  cList.put(spinn.get(i).getCountryname(), spinn.get(i).getCountryname());
     }
     return cList;
  }
  
  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("user") User user, BindingResult result, SessionStatus status) 
  {
	  ModelAndView mav = new ModelAndView("register");
	  mav.addObject("error", "User Already Exists!!!");
    int stat = userService.register(user);
    if(stat == 0) {
    	 mav.addObject("error", "User Already Exists!!!");
    	 mav.addObject("user", new User());
    	return mav;
    }
    

    return new ModelAndView("welcome", "firstname", user.getFirstname());
  }
  
 
}
