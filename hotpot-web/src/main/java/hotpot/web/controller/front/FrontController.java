package hotpot.web.controller.front;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hotpot.core.entity.User;
import hotpot.core.service.UserService;
import hotpot.sys.entity.FrontUser;
import hotpot.sys.entity.Msg;
import hotpot.sys.entity.Order;
import hotpot.sys.service.FoodService;
import hotpot.sys.service.FrontUserService;
import hotpot.sys.service.MsgService;
import hotpot.sys.service.OrderService;
/***
 * 前台，首页各种连接登陆等
 * @author qq:263608237
 *
 */
@Controller
@RequestMapping("/")
public class FrontController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FrontUserService frontUserService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private MsgService msgService;
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,   ServletRequestDataBinder binder) throws Exception {   
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true)); 
	}  
	/***
	 * 跳转到首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		return "index";
	}
	/***
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("order")
	public String order(Model model) {
		return "order";
	}
	@RequestMapping("myorder")
	public String myorder(Model model,HttpSession httpSession) {
		FrontUser user=(FrontUser)httpSession.getAttribute("user");
		model.addAttribute("orders", orderService.findByFrontUser(user));
		return "myorder";
	}
	@RequestMapping("doorder")
	public String doorder(Order order,Model model) {
		this.orderService.save(order);
		model.addAttribute("tip","预定成功！");
		return "order";
	}
	@RequestMapping("foodcategory")
	public String foodcategory(Model model) {
		model.addAttribute("categorys",foodService.findAllCategory());
		model.addAttribute("foods",foodService.findAll());
		return "food";
	}
	
	@RequestMapping("foodcategory/{id}")
	public String foodcategory(@PathVariable Long id,Model model) {
		model.addAttribute("categorys",foodService.findAllCategory());
		model.addAttribute("foods",foodService.findAll());
		model.addAttribute("id",id);
		return "food";
	}
	
	@RequestMapping("viewfood/{id}")
	public String viewfood(@PathVariable Long id,Model model) {
		model.addAttribute("food",foodService.find(id));
		model.addAttribute("msgs",msgService.findByFood(id));
		model.addAttribute("id",id);
		return "viewfood";
	}
	
	@RequestMapping("foodmsg")
	public String viewfood(Msg msg,Model model,HttpSession httpSession) {
		FrontUser user=(FrontUser)httpSession.getAttribute("user");
		msg.setFrontUser(user);
		msg.setState("1");
		msgService.save(msg);
		return "redirect:/viewfood/"+msg.getFood().getId();
	}
	/***
	 * 关于我们
	 * @return
	 */
	@RequestMapping("about")
	public String about() {
		return "about";
	}
	/***
	 * 注册连接
	 * @return
	 */
	@RequestMapping("center")
	public String center() {
		return "center";
	}
	
	/***
	 * 注册连接
	 * @return
	 */
	@RequestMapping("register")
	public String register() {
		return "register";
	}
	/***
	 * 点击注册
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("doregister")
	public String register(FrontUser user,Model model) {
		user.setCreateDate(new Date());
		model.addAttribute("tip","注册成功请登录！");
		frontUserService.save(user);
		return "login";
	}
	/***
	 * 登陆连接
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	/***
	 * 退出操作清空缓存等操作
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping("loginout")
	public String loginout(HttpSession httpSession,Model model) {
		httpSession.removeAttribute("user");
		return "index";
	}
	
	
	
	/***
	 * 执行登陆动作
	 * @param user
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping("dologin")
	public String dologin(User user,HttpSession httpSession,Model model) {
		FrontUser loginuser=frontUserService.login(user.getUsername(), user.getPassword());
    	if(loginuser!=null){
    		httpSession.setAttribute("user", loginuser);
            return "index"; 
    	}
    	else{
    		httpSession.removeAttribute("user");
    		model.addAttribute("tip","登陆失败 不存在此用户名或密码!");
    		return "login";
    	}
	}
	
}

