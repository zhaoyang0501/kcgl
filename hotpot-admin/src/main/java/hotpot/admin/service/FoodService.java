package hotpot.admin.service;
import java.util.List;

import org.springframework.stereotype.Service;

import hotpot.admin.entity.Food;
import hotpot.common.service.SimpleCurdService;

@Service
public class FoodService extends SimpleCurdService<Food, Long> {
	public List<Food> findAll(){
		return (List<Food>) this.simpleCurdRepository.findAll();
	}
}
