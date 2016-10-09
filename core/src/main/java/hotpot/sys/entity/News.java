package hotpot.sys.entity;

import hotpot.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name = "t_news")
public class News  extends BaseEntity<Long>{
	
	private String title;
	
	@Lob
	private String body;
	
	private String creater;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	
}
