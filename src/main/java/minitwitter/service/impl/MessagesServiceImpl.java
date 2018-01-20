package minitwitter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minitwitter.dao.MessagesDao;
import minitwitter.model.Messages;
import minitwitter.service.MessagesService;

@Service
public class MessagesServiceImpl implements MessagesService {

	@Autowired
	private MessagesDao messagesDao;
	
	public MessagesServiceImpl() {
	}

	@Override
	public List<Messages> getAllMessagesForUser(Long userId) {
		return messagesDao.getAllMessagesForUser(userId);
	}

	@Override
	public List<Messages> searchMessages(Long userId, String searchKeyword) {
		return messagesDao.searchMessages(userId, searchKeyword);
	}

}
