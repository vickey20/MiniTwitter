package minitwitter.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minitwitter.dao.FollowersDao;
import minitwitter.service.FollowersService;

@Service
public class FollowersServiceImpl implements FollowersService {

	@Autowired
	private FollowersDao followersDao;
	
	public FollowersServiceImpl() {}

	@Override
	@Transactional
	public int follow(Long userId, Long targetUserId) {
		return followersDao.follow(userId, targetUserId);
	}

	@Override
	@Transactional
	public int unfollow(Long userId, Long targetUserId) {
		return followersDao.unfollow(userId, targetUserId);
	}

}
