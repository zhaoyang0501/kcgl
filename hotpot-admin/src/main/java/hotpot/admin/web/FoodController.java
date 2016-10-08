package hotpot.admin.web;

import hotpot.admin.entity.Food;
import hotpot.admin.service.FoodService;
import hotpot.common.web.AbstractBaseCURDController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys", this.getSimpleCurdService().findAllCategory());
		return this.getBasePath()+"/index";
	}
}
