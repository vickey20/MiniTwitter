package minitwitter.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import minitwitter.dao.FollowersDao;

@Repository
public class FollowersDaoImpl implements FollowersDao {

	private static final String QUERY_FOLLOW_USER = "INSERT INTO Followers (person_id, follower_person_id) values (:person_id, :follower_person_id)";
	private static final String QUERY_UNFOLLOW_USER = "DELETE FROM Followers WHERE person_id = :person_id AND follower_person_id = :follower_person_id";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public FollowersDaoImpl() {}

	@Override
	public int follow(Long userId, Long targetUserId) {
		Map<String, Object> paramMap = new HashMap<>(); 
		paramMap.put("person_id", userId);
		paramMap.put("follower_person_id", targetUserId);
		return namedParameterJdbcTemplate.update(QUERY_FOLLOW_USER,
				paramMap);
	}
	
	@Override
	public int unfollow(Long userId, Long targetUserId) {
		Map<String, Object> paramMap = new HashMap<>(); 
		paramMap.put("person_id", userId);
		paramMap.put("follower_person_id", targetUserId);
		return namedParameterJdbcTemplate.update(QUERY_UNFOLLOW_USER,
				paramMap);
	}
	
}
