package seed.sys.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import seed.common.entity.BaseEntity;


@Entity
@Table(name = "t_food_in")
public class FoodIn extends BaseEntity<Long> {

	
	@ManyToOne
	private Food food;
	
	private Integer num;
	
	private Double price;
	
	private Double totalPrice;
	
	private String type;
	
	private String opUser;
	
	private String recordUser;
	
	private String supply;
	
	@ManyToOne
	private FoodCategory foodCategory;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
	private Date inDate;

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public String getRecordUser() {
		return recordUser;
	}

	public void setRecordUser(String recordUser) {
		this.recordUser = recordUser;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	
	
}
