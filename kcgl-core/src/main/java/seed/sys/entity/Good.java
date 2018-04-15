package seed.sys.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import seed.common.entity.BaseEntity;


@Entity
@Table(name = "t_good_seat")
public class Good extends BaseEntity<Long>{
	private String  name;
	private String remark;
	
	@OneToOne
	private FoodCategory category;
	
	public FoodCategory getCategory() {
		return category;
	}
	public void setCategory(FoodCategory category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
