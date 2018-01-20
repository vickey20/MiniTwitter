package minitwitter.service;

import java.util.List;

import minitwitter.model.Messages;

public interface MessagesService {
	public List<Messages> getAllMessagesForUser(Long userId);
	public List<Messages> searchMessages(Long userId, String searchKeyword);
}
