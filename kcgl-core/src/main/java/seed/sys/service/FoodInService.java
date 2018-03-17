package seed.sys.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seed.common.service.SimpleCurdService;
import seed.sys.entity.Food;
import seed.sys.entity.FoodIn;
import seed.sys.repository.FoodInRepository;
import seed.sys.repository.FoodRepository;

@Service
public class FoodInService extends SimpleCurdService<FoodIn, Long> {
	
	@Autowired
	private FoodInRepository foodInRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	public List<FoodIn> findAll(){
		return (List<FoodIn>) this.simpleCurdRepository.findAll();
	}
	
	public List<Food> findByCategory(Long categoryid){
		return foodRepository.findByFoodCategoryId(categoryid);
	}
	
	public List<FoodIn> findAllCategory(){
		return (List<FoodIn>)this.foodInRepository.findAll();
	}
	
	
}
