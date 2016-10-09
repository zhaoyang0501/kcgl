package hotpot.sys.service;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import hotpot.common.service.SimpleCurdService;
import hotpot.sys.entity.Order;
@Service
public class OrderService extends SimpleCurdService<Order, Long> {
	public Page<Order> findAll(final int pageNumber, final int pageSize,final Date orderDate){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<Order> spec = new Specification<Order>() {
             public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (orderDate!=null) {
                  predicate.getExpressions().add(cb.equal(root.get("orderDate").as(Date.class),orderDate));
             }
             return predicate;
             }
        };
        Page<Order> result = (Page<Order>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
  } 
}
