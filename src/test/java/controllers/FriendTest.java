package controllers;

import models.Friend;
import models.Message;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;

public class FriendTest {

    PacemakerAPI pacemaker = new PacemakerAPI("https://secure-depths-94899.herokuapp.com/");
    //PacemakerAPI pacemaker = new PacemakerAPI("http://localhost:7000/");
    User homer = new User("homer", "simpson", "homer@simpson.com", "secret");
    User lisa = new User("lisa", "simpson", "lisa@simpson.com", "secret");
    User bart = new User("bart", "simpson", "bart@simpson.com", "secret");

    @Before
    public void setup() {
        pacemaker.deleteUsers();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFollowFriend() {
        pacemaker.deleteUsers();
        User mainUser   = pacemaker.createUser(homer.firstname, homer.lastname, homer.email, homer.password);
        User follower1  = pacemaker.createUser(lisa.firstname, lisa.lastname, lisa.email, lisa.password);
        Friend addedFirstFollower  = pacemaker.followFriend(mainUser.id, mainUser.email, follower1.email);
        assertEquals(addedFirstFollower, pacemaker.getUserByEmail(mainUser.email).friends.values().toArray()[0]);
        pacemaker.deleteUsers();
    }

    @Test
    public void testMessageFriend() {
        pacemaker.deleteUsers();
        User mainUser   = pacemaker.createUser(homer.firstname, homer.lastname, homer.email, homer.password);
        User follower1  = pacemaker.createUser(lisa.firstname, lisa.lastname, lisa.email, lisa.password);
        Friend addedFirstFollower  = pacemaker.followFriend(mainUser.id, mainUser.email, follower1.email);
        Message sendMessageToFirstFollower = pacemaker.messageFriend(mainUser.getEmail(), addedFirstFollower.friendemail,
                "This is some test message");
        assertEquals(sendMessageToFirstFollower, pacemaker.getUserByEmail(mainUser.email).messages.values().toArray()[0]);
    }
}