package seed.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import seed.common.dto.json.DataTableResponse;
import seed.common.dto.json.Response;
import seed.common.dto.json.SuccessResponse;
import seed.common.web.AbstractBaseCURDController;
import seed.sys.entity.FoodIn;
import seed.sys.entity.FoodOut;
import seed.sys.service.FoodOutService;
import seed.sys.service.FoodService;

@Controller
@RequestMapping("sys/out")
public class Outcontroller extends AbstractBaseCURDController<FoodOut,Long>  {
	
	@Autowired
	private FoodService foodService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	 
	@Override
	public FoodOutService getSimpleCurdService() {
		return (FoodOutService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/out";
	}
	
	@RequestMapping("foodOut")
	public String foodOut(FoodOut m,String foodCode,Model model) {
		m.setFood(foodService.findByCode(foodCode));
		simpleCurdService.save(m);
		model.addAttribute("categorys", foodService.findAllCategory());
		model.addAttribute("response",new SuccessResponse("出库成功！"));
		return this.getBasePath()+"/index";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys", foodService.findAllCategory());
		return this.getBasePath()+"/index";
	}
	
	@RequestMapping("report")
	public String report(Model model) {
		model.addAttribute("categorys", foodService.findAllCategory());
		return this.getBasePath()+"/report";
	}
	@RequestMapping("findAll")
	@ResponseBody
	public Response list(Integer start, Integer length, String value,String columnname) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<FoodOut> m = simpleCurdService.findAll(pageNumber, pageSize, value,columnname);
		return new DataTableResponse<FoodOut>( m.getContent(),(int) m.getTotalElements() );
	}
	
}
