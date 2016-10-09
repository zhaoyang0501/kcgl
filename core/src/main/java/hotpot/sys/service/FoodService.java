package hotpot.sys.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotpot.common.service.SimpleCurdService;
import hotpot.sys.entity.Food;
import hotpot.sys.entity.FoodCategory;
import hotpot.sys.repository.FoodCategoryRepository;
import hotpot.sys.repository.FoodRepository;

@Service
public class FoodService extends SimpleCurdService<Food, Long> {
	
	@Autowired
	private FoodCategoryRepository foodCategoryRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	public List<Food> findAll(){
		return (List<Food>) this.simpleCurdRepository.findAll();
	}
	
	public List<Food> findByCategory(Long categoryid){
		return foodRepository.findByFoodCategoryId(categoryid);
	}
	public List<FoodCategory> findAllCategory(){
		return (List<FoodCategory>)this.foodCategoryRepository.findAll();
	}
}
