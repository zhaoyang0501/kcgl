package seed.sys.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import seed.common.entity.BaseEntity;

//仓库编码	仓库名称	存货编码	规格型号	批号	现存数量	到货在检数量	调拨在途数量	预计入库数量合计	待发货数量	调拨待发数量	可用数量

@Entity
@Table(name = "t_food")
public class Food extends BaseEntity<Long> {

	private String name;

	private String remark;

	private String img;

	private Double price;

	private String code;

	private String standard;

	private String batchNo;

	private Integer num;

	private Integer useNum;

	private String seat;

	@ManyToOne
	private FoodCategory foodCategory;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStandard() {
		return standard;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
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
