package minitwitter.dao;

public interface FollowersDao {
	public int follow(Long userId, Long targetUserId);
	public int unfollow(Long userId, Long targetUserId);
}
