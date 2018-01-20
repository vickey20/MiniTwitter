package minitwitter.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import minitwitter.dao.PeopleDao;
import minitwitter.model.People;

@Repository
public class PeopleDaoImpl implements PeopleDao {

	private static final String QUERY_GET_ALL_FOLLOWING_FOR_USER = "SELECT * FROM People WHERE id IN (SELECT follower_person_id FROM Followers WHERE person_id = :person_id)";
	private static final String QUERY_GET_ALL_FOLLOWERS_FOR_USER = "SELECT * FROM People WHERE id IN (SELECT person_id FROM Followers WHERE follower_person_id = :follower_person_id)";
			
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public PeopleDaoImpl() {}

	@Override
	public List<People> getAllFollowing(long userId) {
		return namedParameterJdbcTemplate.query(QUERY_GET_ALL_FOLLOWING_FOR_USER,
                new MapSqlParameterSource("person_id", userId), (resultSet, i) -> {
                    return toPeople(resultSet);
                });
	}

	@Override
	public List<People> getAllFollowers(long userId) {
		return namedParameterJdbcTemplate.query(QUERY_GET_ALL_FOLLOWERS_FOR_USER,
                new MapSqlParameterSource("follower_person_id", userId), (resultSet, i) -> {
                    return toPeople(resultSet);
                });
	}
	
	private People toPeople(ResultSet resultSet) throws SQLException {
		People people = new People();
		people.setId(resultSet.getLong("id"));
		people.setName(resultSet.getString("name"));
		people.setHandle(resultSet.getString("handle"));
		return people;
	}

}
