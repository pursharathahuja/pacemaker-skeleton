package controllers;

import io.javalin.Javalin;

public class RestMain {

	public static void main(String[] args) {
		Javalin app = Javalin.create()
				.start(getHerokuAssignedPort());
		PacemakerRestService service = new PacemakerRestService();
		configRoutes(app, service);
	}

	private static int getHerokuAssignedPort() {
		String herokuPort = System.getenv("PORT");
		if (herokuPort != null) {
			return Integer.parseInt(herokuPort);
		}
		return 7000;
	}

	static void configRoutes(Javalin app, PacemakerRestService service) {

		app.get("/users", ctx -> {
			service.listUsers(ctx);
		});
		
	    app.post("/users", ctx -> {
	        service.createUser(ctx);
	    });
	    
	    app.get("/users/:id", ctx -> {
	        service.listUser(ctx);
	    });
	    
	    app.get("/users/:id/activities", ctx -> {
	        service.getActivities(ctx);
	    });

		app.get("/users/:id/messages", ctx -> {
			service.listMessages(ctx);
		});

	    app.post("/users/:id/message", ctx -> {
	        service.messageFriend(ctx);
	    });

	    app.post("/users/:id/friends", ctx -> {
	        service.followFriend(ctx);
	    });

		app.post("/users/:id/friends/:friendemail", ctx -> {
			service.unfollowFriend(ctx);
		});

		app.delete("/users/:id/unfollowfriends", ctx -> {
			service.unfollowFriends(ctx);
		});

		app.get("/users/:id/friends", ctx -> {
			service.listFriends(ctx);
		});

		app.post("/users/:id/activities", ctx -> {
			service.createActivity(ctx);
		});

		app.get("/users/:id/activities/:activityid", ctx -> {
			service.getActivity(ctx);
		});

		app.get("/users/:id/activities/:activityid/locations", ctx -> {
			service.getActivityLocations(ctx);
		});

		app.post("/users/:id/activities/:activityid/locations", ctx -> {
			service.addLocation(ctx);
		});

		app.delete("/users", ctx -> {
			service.deleteUsers(ctx);
		});

		app.delete("/users/:id", ctx -> {
			service.deleteUser(ctx);
		});

		app.delete("/users/:id/activities", ctx -> {
			service.deleteActivities(ctx);
		});
	}
}