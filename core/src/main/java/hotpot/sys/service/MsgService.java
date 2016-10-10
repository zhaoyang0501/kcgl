package hotpot.sys.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotpot.common.service.SimpleCurdService;
import hotpot.sys.entity.Msg;
import hotpot.sys.repository.MsgRepository;
@Service
public class MsgService extends SimpleCurdService<Msg, Long> {
	
	@Autowired
	private MsgRepository msgRepository;
	
	public List<Msg> findByFood(Long foodid){
		return msgRepository.findByFoodId(foodid);
	}
}
