package hotpot.sys.repository;
import hotpot.common.repository.SimpleCurdRepository;
import hotpot.sys.entity.FrontUser;
public interface FrontUserRepository   extends SimpleCurdRepository<FrontUser ,Long>{
	FrontUser findByUsernameAndPassword(String username, String password);
}
