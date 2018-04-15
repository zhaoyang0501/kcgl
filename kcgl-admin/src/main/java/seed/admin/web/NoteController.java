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
import seed.sys.entity.Note;
import seed.sys.service.DriverService;
import seed.sys.service.FoodService;
import seed.sys.service.NoteService;

@Controller
@RequestMapping("sys/note")
public class NoteController extends AbstractBaseCURDController<Note,Long>  {
	
	
	@Autowired
	private DriverService driverService;
	
	
	@Autowired
	private FoodService foodService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@Override
	public NoteService getSimpleCurdService() {
		return (NoteService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/note";
	}
	
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("drivers", this.driverService.findAll());
		model.addAttribute("foods", this.foodService.findAll());
		return this.getBasePath()+"/index";
	}
	
}
