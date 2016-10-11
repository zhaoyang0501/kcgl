package hotpot.admin.web;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hotpot.admin.dto.FormDto;
import hotpot.common.dto.json.FailedResponse;
import hotpot.common.dto.json.ObjectResponse;
import hotpot.common.dto.json.Response;
import hotpot.common.dto.json.SuccessResponse;
import hotpot.common.web.AbstractBaseCURDController;
import hotpot.sys.entity.FoodItem;
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
	
	@RequestMapping("precheck")
	@ResponseBody
	public Response precheck(Model model,Long formid) {
		Form form = formService.find(formid);
		Double totalprice=0d;
		for(FoodItem item: form.getFoodItem()){
			totalprice+=item.getNum()*item.getPrice();
		}
		form.setPrice(totalprice);
		
		return new ObjectResponse<Form>(formService.find(formid));
	}
	
	@RequestMapping("check")
	@ResponseBody
	public Response check(Model model,Long formid) {
		
		Form form = formService.find(formid);
		form.setEndTime(new Date());
		Double totalprice=0d;
		for(FoodItem item: form.getFoodItem()){
			totalprice+=item.getNum()*item.getPrice();
		}
		form.setPrice(totalprice);
		
		Seat seat = getSimpleCurdService().findByName(form.getSeatName());
		seat.setState("2");
		seat.setForm(null);
		formService.save(form);
		this.getSimpleCurdService().save(seat);
		
		return new ObjectResponse<Form>(formService.find(formid));
	}
	@RequestMapping("choose")
	@ResponseBody
	public Response choose(Model model,FormDto formdto) {
		Form form = formService.find(formdto.getId());
		Set<FoodItem> foodItem = new HashSet<FoodItem>();
		if(formdto.getFoodItems()!=null){
			for(FoodItem item:formdto.getFoodItems()){
				FoodItem newitem = new FoodItem();
				newitem.setFood(foodService.find(item.getFood().getId()));
				newitem.setForm(form);
				newitem.setNum(item.getNum());
				newitem.setPrice(newitem.getNum()*newitem.getFood().getPrice());
				foodItem.add(newitem);
			}
		}
		Double totalprice=0d;
		for(FoodItem item: form.getFoodItem()){
			totalprice+=item.getNum()*item.getPrice();
		}
		form.setPrice(totalprice);
		form.setFoodItem(foodItem);
		formService.save(form);
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
