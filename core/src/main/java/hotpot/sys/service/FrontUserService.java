package hotpot.sys.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotpot.common.service.SimpleCurdService;
import hotpot.core.entity.User;
import hotpot.sys.entity.FrontUser;
import hotpot.sys.repository.FrontUserRepository;

@Service
public class FrontUserService extends SimpleCurdService<FrontUser, Long> {
	@Autowired
	private  FrontUserRepository frontUserRepository;
	
	public User login(String username,String password){
		return  this.frontUserRepository.findByUsernameAndPassword(username, password);
	}
}
