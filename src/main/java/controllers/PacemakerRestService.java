package controllers;

import io.javalin.Context;
import models.*;

import static models.Fixtures.users;

public class PacemakerRestService {

  PacemakerAPI pacemaker = new PacemakerAPI("http://localhost:7000");

  PacemakerRestService() {
	    users.forEach(
	        user -> pacemaker.createUser(user.firstname, user.lastname, user.email, user.password));
  }

	public void listUsers(Context ctx) {
		ctx.json(pacemaker.getUsers());

	}

	public void createUser(Context ctx) {
		User user = ctx.bodyAsClass(User.class);
		User newUser = pacemaker
			.createUser(user.firstname, user.lastname, user.email, user.password);
		ctx.json(newUser);
	}

	public void listUser(Context ctx) {
		String id = ctx.pathParam("id");
		ctx.json(pacemaker.getUser(id));
	}

	public void getActivities(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
		  ctx.json(user.activities.values());
		} else {
		  ctx.status(404);
		}
	}

	public void createActivity(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
		  Activity activity = ctx.bodyAsClass(Activity.class);
		  Activity newActivity = pacemaker
			  .createActivity(id, activity.type, activity.location, activity.distance);
		  ctx.json(newActivity);
		} else {
		  ctx.status(404);
		}
	}

	public void listMessages(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
			ctx.json(user.messages.values());
		} else {
			ctx.status(404);
		}
	}

	public void messageFriend(Context ctx) {
		String id 	= ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
		  Message messageToSave = ctx.bodyAsClass(Message.class);
		  Message addedMessage = pacemaker
				  .messageFriend(messageToSave.from, messageToSave.to, messageToSave.message);
		  ctx.json(addedMessage);
		} else {
		  ctx.status(404);
		}
	}

	public void followFriend(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
			Friend friend = ctx.bodyAsClass(Friend.class);
			Friend newFriend = pacemaker
					.followFriend(id, friend.useremail, friend.friendemail);
			ctx.json(newFriend);
		} else {
			ctx.status(404);
		}
	}

	public void unfollowFriend(Context ctx) {
		String user_id = ctx.pathParam("id");
		String friend_email = ctx.pathParam("friendemail");
		User user = pacemaker.getUser(user_id);
		if (user != null) {
			user.friends.values().removeIf(friend -> friend.friendemail.equals(friend_email));
			ctx.json(friend_email);
		} else {
			ctx.status(404);
		}
	}

	public void unfollowFriends(Context ctx) {
		User user = pacemaker.getUser(ctx.pathParam("id"));
		if (user != null) {
			user.friends.clear();
			ctx.json(204);
		} else {
			ctx.status(404);
		}
	}

	public void listFriends(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
			ctx.json(user.friends.values());
		} else {
			ctx.status(404);
		}
	}

	public void getActivity(Context ctx) {
		String id = ctx.pathParam("activityid");
		Activity activity = pacemaker.getActivity(id);
		if (activity != null) {
			ctx.json(activity);
		} else {
			ctx.status(404);
		}
	}

	public void getActivityLocations(Context ctx) {
		String id = ctx.pathParam("activityid");
		Activity activity = pacemaker.getActivity(id);
		if (activity != null) {
			ctx.json(activity.route);
		} else {
			ctx.status(404);
		}
	}

	public void addLocation(Context ctx) {
		String id = ctx.pathParam("activityid");
		Activity activity = pacemaker.getActivity(id);
		if (activity != null) {
			Location location = ctx.bodyAsClass(Location.class);
			activity.route.add(location);
			ctx.json(location);
		} else {
			ctx.status(404);
		}
	}

	public void deleteUser(Context ctx) {
		String id = ctx.pathParam("id");
		ctx.json(pacemaker.deleteUser(id));
	}

	public void deleteUsers(Context ctx) {
		pacemaker.deleteUsers();
		ctx.json(204);
	}

	public void deleteActivities(Context ctx) {
		String id = ctx.pathParam("id");
		pacemaker.deleteActivities(id);
		ctx.json(204);
	}




}