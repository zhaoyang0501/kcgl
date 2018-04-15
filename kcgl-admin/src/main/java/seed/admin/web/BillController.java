package seed.admin.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import seed.common.web.AbstractBaseCURDController;
import seed.sys.entity.Bill;
import seed.sys.service.BillService;
import seed.sys.service.DriverService;
import seed.sys.service.FoodService;

@Controller
@RequestMapping("sys/bill")
public class BillController extends AbstractBaseCURDController<Bill,Long>  {
	
	
	@Autowired
	private DriverService driverService;
	
	
	@Autowired
	private FoodService foodService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@Override
	public BillService getSimpleCurdService() {
		return (BillService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/bill";
	}
	
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("drivers", this.driverService.findAll());
		model.addAttribute("foods", this.foodService.findAll());
		return this.getBasePath()+"/index";
	}
	
}
