package hotpot.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hotpot.admin.entity.Food;
import hotpot.common.web.AbstractBaseCURDController;

@Controller
@RequestMapping("sys/food")
public class FoodController extends AbstractBaseCURDController<Food,Long>{

	@Override
	protected String getBasePath() {
		return "sys/food";
	}
	
}
