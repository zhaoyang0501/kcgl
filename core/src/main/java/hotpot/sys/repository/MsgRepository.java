package hotpot.sys.repository;
import java.util.List;

import hotpot.common.repository.SimpleCurdRepository;
import hotpot.sys.entity.Msg;
public interface MsgRepository   extends SimpleCurdRepository<Msg ,Long>{

	List<Msg> findByFoodId(Long foodid);
}
