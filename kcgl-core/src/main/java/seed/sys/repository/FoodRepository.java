package seed.sys.repository;
import java.util.List;

import seed.common.repository.SimpleCurdRepository;
import seed.sys.entity.Food;
public interface FoodRepository   extends SimpleCurdRepository<Food ,Long>{
	public List<Food> findByFoodCategoryId(Long id);
	public Food findByCode(String code);
}
