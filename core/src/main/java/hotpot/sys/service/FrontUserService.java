package hotpot.sys.service;
import hotpot.common.service.SimpleCurdService;
import hotpot.sys.entity.FrontUser;
import hotpot.sys.repository.FrontUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrontUserService extends SimpleCurdService<FrontUser, Long> {
	
	@Autowired
	private  FrontUserRepository frontUserRepository;
	
	public FrontUser login(String username,String password){
		return  this.frontUserRepository.findByUsernameAndPassword(username, password);
	}
}
