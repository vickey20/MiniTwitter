package minitwitter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minitwitter.dao.PeopleDao;
import minitwitter.model.People;
import minitwitter.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleDao peopleDao;
	
	public PeopleServiceImpl() {
	}

	@Override
	public List<People> getAllFollowing(long userId) {
		return peopleDao.getAllFollowing(userId);
	}

	@Override
	public List<People> getAllFollowers(long userId) {
		return peopleDao.getAllFollowers(userId);
	}

}
