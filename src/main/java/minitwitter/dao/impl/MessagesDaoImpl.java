package minitwitter.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import minitwitter.dao.MessagesDao;
import minitwitter.model.Messages;

@Repository
public class MessagesDaoImpl implements MessagesDao {

	private static final String QUERY_ALL_MESSAGES_FOR_USER = "SELECT * FROM Messages WHERE person_id IN (SELECT follower_person_id FROM Followers WHERE person_id = :person_id) OR person_id = :person_id";
	private static final String QUERY_SEARCH_WITHIN_MESSAGES = "SELECT * FROM Messages WHERE content IN (SELECT content FROM Messages WHERE person_id IN (SELECT follower_person_id FROM Followers WHERE person_id = :person_id) OR person_id = :person_id) AND content LIKE :search";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public MessagesDaoImpl() {}

	@Override
	public List<Messages> getAllMessagesForUser(Long userId) {
		
		return namedParameterJdbcTemplate.query(QUERY_ALL_MESSAGES_FOR_USER, 
			new MapSqlParameterSource("person_id", userId), (resultSet, i) -> {
				return toMessage(resultSet);
			});
	}
	
	private Messages toMessage(ResultSet resultSet) throws SQLException {
		Messages message = new Messages();
		message.setId(resultSet.getLong("id"));
		message.setPersonId(resultSet.getLong("person_id"));
		message.setContent(resultSet.getString("content"));
		return message;
	}

	@Override
	public List<Messages> searchMessages(Long userId, String searchKeyword) {
		Map<String, Object> paramMap = new HashMap<>(); 
		paramMap.put("person_id", userId);
		paramMap.put("search", "%" + searchKeyword + "%");
		System.out.println("person_id: " + userId + "; search: " + paramMap.get("search"));
		return namedParameterJdbcTemplate.
				query(QUERY_SEARCH_WITHIN_MESSAGES, 
				paramMap, (resultSet, i) -> {
					return toMessage(resultSet);
				});
	}
}
