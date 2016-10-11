package hotpot.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hotpot.common.dto.json.DataTableResponse;
import hotpot.common.dto.json.FailedResponse;
import hotpot.common.dto.json.Response;
import hotpot.common.dto.json.SuccessResponse;
import hotpot.common.web.AbstractBaseCURDController;
import hotpot.sys.entity.Order;
import hotpot.sys.service.OrderService;

@Controller
@RequestMapping("sys/order")
public class OrderController extends AbstractBaseCURDController<Order,Long>{
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  
	            ServletRequestDataBinder binder) throws Exception {   
	      binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	} 
	@Override
	public OrderService getSimpleCurdService() {
		return (OrderService)super.getSimpleCurdService();
	}
	
	@RequestMapping("pass")
	@ResponseBody
	public Response pass(Long id) {
		Order order = this.simpleCurdService.find(id);
		order.setState("2");
		simpleCurdService.save(order);
		return new SuccessResponse();
	}
	
	@RequestMapping("nopass")
	@ResponseBody
	public Response nopass(Long id) {
		Order order = this.simpleCurdService.find(id);
		order.setState("4");
		simpleCurdService.save(order);
		return new SuccessResponse();
	}
	
	@Override
	protected String getBasePath() {
		return "sys/order";
	}
	
	@RequestMapping("listall")
	@ResponseBody
	public Response listall(Integer start, Integer length, Date orderDate) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<Order> m = getSimpleCurdService().findAll(pageNumber, pageSize, orderDate);
		return new DataTableResponse<Order>( m.getContent(),(int) m.getTotalElements() );
	}
	
}
