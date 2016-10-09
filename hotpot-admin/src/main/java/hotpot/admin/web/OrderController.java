package hotpot.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hotpot.common.web.AbstractBaseCURDController;
import hotpot.sys.entity.Order;

@Controller
@RequestMapping("sys/order")
public class OrderController extends AbstractBaseCURDController<Order,Long>{
	
	
	
	@Override
	protected String getBasePath() {
		return "sys/order";
	}
	
	
}
