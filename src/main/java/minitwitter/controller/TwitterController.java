package minitwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import minitwitter.model.Messages;
import minitwitter.model.People;
import minitwitter.service.FollowersService;
import minitwitter.service.MessagesService;
import minitwitter.service.PeopleService;

@RestController
@RequestMapping("/api")
public class TwitterController {

	private PeopleService peopleService;
	private MessagesService messagesService;
	private FollowersService followersService;
	
	@Autowired
	public TwitterController(PeopleService peopleService, MessagesService messagesService, FollowersService followersService) {
		this.peopleService = peopleService;
		this.messagesService = messagesService;
		this.followersService = followersService;
	}
	
	@RequestMapping(value = "/messagelist", method = RequestMethod.POST) 
	public Iterable<Messages> getMessageListForUser(@RequestParam(name = "userId", required = true) Long userId) {
		System.out.println("userId: " + userId);
		return messagesService.getAllMessagesForUser(userId);
	}
	
	@RequestMapping(value = "/messagelist/search", method = RequestMethod.POST) 
	public Iterable<Messages> searchMessages(@RequestParam(name = "userId", required = true) Long userId, 
											@RequestParam(name = "search", required = true) String searchKeyword) {
		return messagesService.searchMessages(userId, searchKeyword);
	}
	
	@RequestMapping(value = "/following", method = RequestMethod.POST) 
	public Iterable<People> getAllFollowing(@RequestParam(name = "userId", required = true) Long userId) {
		return peopleService.getAllFollowing(userId);
	}
	
	@RequestMapping(value = "/followers", method = RequestMethod.POST) 
	public Iterable<People> getAllFollowers(@RequestParam(name = "userId", required = true) Long userId) {
		return peopleService.getAllFollowers(userId);
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.POST) 
	public ResponseEntity<?> follow(@RequestParam(name = "userId", required = true) Long userId, 
								   @RequestParam(name = "targetUserId", required = true) Long targetUserId) {
		int result = followersService.follow(userId, targetUserId);
		if (result >= 1) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(result, HttpStatus.NOT_MODIFIED);
		}
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.POST) 
	public ResponseEntity<?> unfollow(@RequestParam(name = "userId", required = true) Long userId, 
			   						 @RequestParam(name = "targetUserId", required = true) Long targetUserId) {
		int result = followersService.unfollow(userId, targetUserId);
		if (result == 1) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
}
