package seed.sys.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import seed.common.entity.BaseEntity;

/***
 *  驾驶员管理模块：驾驶员管理模块：驾驶员的主要信息包括姓名、年龄、驾驶年限、违章记录和联系
方式等。主要功能的添加、删除和查询的基本档案方面的信息，该功能模块是一个管理
信息的查询模块，其中包括对驾驶员基本信息的查询管理，同时当货物到达客户当地是
也便于客户与管理员进行联系。

 * @author pzy
 *
 */
@Entity
@Table(name = "t_driver")
public class Driver extends BaseEntity<Long> {

	private String name;
	private Integer age;
	private String driveryear;
	private String tel;
	private String cardno;
	private String cardstate;
	private String addr;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDriveryear() {
		return driveryear;
	}
	public void setDriveryear(String driveryear) {
		this.driveryear = driveryear;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCardstate() {
		return cardstate;
	}
	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
