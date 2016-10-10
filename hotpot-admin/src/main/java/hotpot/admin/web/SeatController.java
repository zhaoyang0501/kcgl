package hotpot.admin.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hotpot.common.dto.json.FailedResponse;
import hotpot.common.dto.json.ObjectResponse;
import hotpot.common.dto.json.Response;
import hotpot.common.dto.json.SuccessResponse;
import hotpot.common.web.AbstractBaseCURDController;
import hotpot.sys.entity.Form;
import hotpot.sys.entity.FrontUser;
import hotpot.sys.entity.Seat;
import hotpot.sys.service.FoodService;
import hotpot.sys.service.FormService;
import hotpot.sys.service.FrontUserService;
import hotpot.sys.service.SeatService;



@Controller
@RequestMapping("sys/seat")
public class SeatController extends AbstractBaseCURDController<Seat,Long>  {
	
	@Autowired
	private FrontUserService FrontUserService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private FoodService foodService;
	
	@Override
	public SeatService getSimpleCurdService() {
		return (SeatService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/seat";
	}
	
	@RequestMapping("open")
	@ResponseBody
	public Response open(String userName,String seatName) {
		
		Seat seat = getSimpleCurdService().findByName(seatName);
		
		FrontUser user = FrontUserService.findByUserName(userName);
		if(user==null){
			return new FailedResponse("会员不存在");
		}
		Form form = new Form();
		form.setSeatName(seatName);
		form.setUser(user);
		formService.save(form);
		
		seat.setState("1");
		seat.setForm(form);
		this.simpleCurdService.save(seat);
		return new SuccessResponse();
	}
	@RequestMapping("prechoose")
	@ResponseBody
	public Response choose(Model model,Long formid) {
		return new ObjectResponse<Form>(formService.find(formid));
	}
	
	
	@RequestMapping("choose")
	@ResponseBody
	public Response choose(Model model,Form form) {
		return new SuccessResponse();
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("seats", this.getSimpleCurdService().findAll());
		model.addAttribute("foods", this.foodService.findAll());
		return this.getBasePath()+"/index";
	}
}
