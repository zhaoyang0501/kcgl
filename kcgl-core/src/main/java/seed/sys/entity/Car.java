package seed.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import seed.common.entity.BaseEntity;

/***
   汽车管理模块：具体对车辆相关的信息进行识别统计，车辆尺寸和车辆负载等基本
信息等进行添加、查询和删除。同时也可以通过该模块对车辆的状态(耗油量、公里数、
净重量、保养日期)等进行查询，在车辆遇到紧急情况是以便及时查询。


 * @author pzy
 *
 */
@Entity
@Table(name = "t_car")
public class Car extends BaseEntity<Long> {
	private String brand;
	private String sn;
	@Column(name="load_")
	private Double load;
	private Double weight;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
	private Date buyDate;
	

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
	private Date fixDate;
	private String color;
	private Double mileage;
	
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Double getLoad() {
		return load;
	}
	public void setLoad(Double load) {
		this.load = load;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Date getFixDate() {
		return fixDate;
	}
	public void setFixDate(Date fixDate) {
		this.fixDate = fixDate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
