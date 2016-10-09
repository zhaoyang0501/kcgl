package hotpot.sys.repository;
import java.util.List;

import hotpot.common.repository.SimpleCurdRepository;
import hotpot.sys.entity.Food;
public interface FoodRepository   extends SimpleCurdRepository<Food ,Long>{
	public List<Food> findByFoodCategoryId(Long id);
}
