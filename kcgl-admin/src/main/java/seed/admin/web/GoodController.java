package seed.admin.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import seed.common.dto.json.DataTableResponse;
import seed.common.dto.json.Response;
import seed.common.web.AbstractBaseCURDController;
import seed.sys.entity.FoodCategory;
import seed.sys.entity.Good;
import seed.sys.service.GoodService;

@Controller
@RequestMapping("sys/good")
public class GoodController extends AbstractBaseCURDController<Good,Long>  {
	
	@Override
	public GoodService getSimpleCurdService() {
		return (GoodService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/good";
	}
	
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys", getSimpleCurdService().findAllCategory());
		return this.getBasePath()+"/index";
	}
	
	@RequestMapping("querybycategory")
	@ResponseBody
	public Response querybycategory(Integer start, Integer length, String value,Long cid) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<Good> m = getSimpleCurdService().findAll(pageNumber, pageSize, value,getSimpleCurdService().getAllCategory(cid));
		return new DataTableResponse<Good>( m.getContent(),(int) m.getTotalElements() );
	}
}
