package seed.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import seed.common.dto.json.EmptyResponse;
import seed.common.dto.json.ObjectResponse;
import seed.common.dto.json.Response;
import seed.common.web.AbstractBaseCURDController;
import seed.sys.entity.Food;
import seed.sys.service.FoodService;

@Controller
@RequestMapping("sys/food")
public class FoodController extends AbstractBaseCURDController<Food,Long>{
	
	@Override
	public FoodService getSimpleCurdService() {
		return (FoodService)super.getSimpleCurdService();
	}
	
	@Override
	protected String getBasePath() {
		return "sys/food";
	}
	
	@RequestMapping("getByCode")
	@ResponseBody
	public Response getByCode(String code) {
		Food food = getSimpleCurdService().findByCode(code);
		if(food == null)
			return new EmptyResponse();
		else return new ObjectResponse<Food>(food);
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys", this.getSimpleCurdService().findAllCategory());
		return this.getBasePath()+"/index";
	}
	
}
