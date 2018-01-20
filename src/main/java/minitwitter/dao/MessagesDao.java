package minitwitter.dao;

import java.util.List;

import minitwitter.model.Messages;

public interface MessagesDao {
	public List<Messages> getAllMessagesForUser(Long userId);
	public List<Messages> searchMessages(Long userId, String searchKeyword);
}
