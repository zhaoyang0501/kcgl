package hotpot.sys.repository;
import hotpot.common.repository.SimpleCurdRepository;
import hotpot.core.entity.User;
import hotpot.sys.entity.FrontUser;
public interface FrontUserRepository   extends SimpleCurdRepository<FrontUser ,Long>{

	User findByUsernameAndPassword(String username, String password);
}
