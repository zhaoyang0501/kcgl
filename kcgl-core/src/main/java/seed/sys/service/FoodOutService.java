package seed.sys.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seed.common.service.SimpleCurdService;
import seed.sys.entity.Food;
import seed.sys.entity.FoodOut;
import seed.sys.repository.FoodOutRepository;
import seed.sys.repository.FoodRepository;

@Service
public class FoodOutService extends SimpleCurdService<FoodOut, Long> {
	
	@Autowired
	private FoodOutRepository FoodOutRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	public List<FoodOut> findAll(){
		return (List<FoodOut>) this.simpleCurdRepository.findAll();
	}
	
	public List<Food> findByCategory(Long categoryid){
		return foodRepository.findByFoodCategoryId(categoryid);
	}
	
	public List<FoodOut> findAllCategory(){
		return (List<FoodOut>)this.FoodOutRepository.findAll();
	}
	
	
}
