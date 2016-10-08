package hotpot.admin.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotpot.admin.entity.Food;
import hotpot.admin.entity.FoodCategory;
import hotpot.admin.repository.FoodCategoryRepository;
import hotpot.common.service.SimpleCurdService;

@Service
public class FoodService extends SimpleCurdService<Food, Long> {
	
	@Autowired
	private FoodCategoryRepository foodCategoryRepository;
	
	public List<Food> findAll(){
		return (List<Food>) this.simpleCurdRepository.findAll();
	}
	
	public List<FoodCategory> findAllCategory(){
		return (List<FoodCategory>)this.foodCategoryRepository.findAll();
	}
}
