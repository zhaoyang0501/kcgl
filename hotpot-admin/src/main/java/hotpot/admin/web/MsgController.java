package hotpot.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hotpot.common.dto.json.Response;
import hotpot.common.dto.json.SuccessResponse;
import hotpot.common.web.AbstractBaseCURDController;
import hotpot.sys.entity.Msg;

@Controller
@RequestMapping("sys/msg")
public class MsgController extends AbstractBaseCURDController<Msg,Long>{
	
	@Override
	protected String getBasePath() {
		return "sys/msg";
	}
	
	@RequestMapping("reply")
	@ResponseBody
	public Response reply(String reply,Long id){
		Msg msg = this.simpleCurdService.find(id);
		msg.setReply(reply);
		msg.setState("2");
		this.simpleCurdService.save(msg);
		return new SuccessResponse("回复成功");
	}
}
