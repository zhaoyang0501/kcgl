package seed.sys.service;
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
import seed.sys.entity.FoodCategory;
import seed.sys.entity.Good;
import seed.sys.repository.FoodCategoryRepository;

@Service
public class GoodService extends SimpleCurdService<Good, Long> {
	
	@Autowired
	private FoodCategoryRepository foodCategoryRepository;
	
	public FoodCategory getAllCategory(Long id){
		if(id == null) return null;
		return foodCategoryRepository.findOne(id);
	} 
	public List<FoodCategory> findAllCategory(){
		return (List<FoodCategory>)this.foodCategoryRepository.findAll();
	} 
	
	public Page<Good> findAll(final int pageNumber, final int pageSize,final String name,final FoodCategory category){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<Good> spec = new Specification<Good>() {
             public Predicate toPredicate(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (category!=null) {
                  predicate.getExpressions().add(cb.equal(root.get("category"),category));
             }
             return predicate;
             }
        };
        Page<Good> result = (Page<Good>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
  } 
}
