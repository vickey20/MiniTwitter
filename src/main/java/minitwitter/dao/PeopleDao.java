package minitwitter.dao;

import java.util.List;

import minitwitter.model.People;

public interface PeopleDao {
	public List<People> getAllFollowing(long userId);
	public List<People> getAllFollowers(long userId);
}
