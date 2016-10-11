package hotpot.sys.repository;
import java.util.List;

import hotpot.common.repository.SimpleCurdRepository;
import hotpot.sys.entity.Form;
public interface FormRepository   extends SimpleCurdRepository<Form ,Long>{
	public List<Form> findByState(String state);
}
