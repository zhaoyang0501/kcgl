package hotpot.admin.web;

import hotpot.common.web.AbstractBaseCURDController;
import hotpot.sys.entity.News;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys/news")
public class NewsController extends AbstractBaseCURDController<News,Long>{
	
	@Override
	protected String getBasePath() {
		return "sys/news";
	}
	@RequestMapping("create")
	public String create(){
		return getBasePath()+"/create";
	}
	@RequestMapping("docreate")
	public String docreate(){
		return getBasePath()+"/create";
	}
}
