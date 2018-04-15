package seed.admin.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import seed.common.web.AbstractBaseCURDController;
import seed.sys.entity.Car;
import seed.sys.service.CarService;

@Controller
@RequestMapping("sys/car")
public class CarController extends AbstractBaseCURDController<Car,Long>  {
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@Override
	public CarService getSimpleCurdService() {
		return (CarService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/car";
	}
	
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}
	
}
