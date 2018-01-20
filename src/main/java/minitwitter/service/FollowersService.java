package minitwitter.service;

public interface FollowersService {
	public int follow(Long userId, Long targetUserId);
	public int unfollow(Long userId, Long targetUserId);
}
