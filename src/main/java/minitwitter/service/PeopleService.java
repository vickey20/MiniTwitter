package minitwitter.service;

import java.util.List;

import minitwitter.model.People;

public interface PeopleService {
	public List<People> getAllFollowing(long userId);
	public List<People> getAllFollowers(long userId);
}
