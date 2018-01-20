package minitwitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Messages {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long person_id;
	private String content;
	
	public Messages() {}
	
	public Messages(Long id, Long person_id, String content) {
		super();
		this.id = id;
		this.person_id = person_id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return person_id;
	}

	public void setPersonId(Long personId) {
		this.person_id = personId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Messages [id=" + id + ", person_id=" + person_id + ", content=" + content + "]";
	}

}
