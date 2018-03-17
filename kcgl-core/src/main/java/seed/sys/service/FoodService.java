package seed.sys.service;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import seed.common.service.SimpleCurdService;
import seed.sys.entity.Food;
import seed.sys.entity.FoodCategory;
import seed.sys.repository.FoodCategoryRepository;
import seed.sys.repository.FoodRepository;

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
	
	public Food findByCode(String code){
		return foodRepository.findByCode(code);
	}
	
	public List<FoodCategory> findAllCategory(){
		return (List<FoodCategory>)this.foodCategoryRepository.findAll();
	}
	
	
}
