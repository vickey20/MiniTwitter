package minitwitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Followers {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long person_id;
	private Long follower_person_id;
	
	public Followers() {	}
	
	public Followers(Long id, Long person_id, Long follower_person_id) {
		super();
		this.id = id;
		this.person_id = person_id;
		this.follower_person_id = follower_person_id;
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

	public Long getFollowerId() {
		return follower_person_id;
	}

	public void setFollowerId(Long followerId) {
		this.follower_person_id = followerId;
	}

	@Override
	public String toString() {
		return "Followers [id=" + id + ", person_id=" + person_id + ", follower_person_id=" + follower_person_id + "]";
	}
}
