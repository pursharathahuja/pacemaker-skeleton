package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.Activity;
import models.User;

public class ActivityTest {

    PacemakerAPI pacemaker = new PacemakerAPI("https://secure-depths-94899.herokuapp.com/");
    //PacemakerAPI pacemaker = new PacemakerAPI("http://localhost:7000/");
    User homer = new User("homer", "simpson", "homer@simpson.com", "secret");

    @Before
    public void setup() {
        pacemaker.deleteUsers();
        homer = pacemaker.createUser(homer.firstname, homer.lastname, homer.email, homer.password);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateActivity() {
        Activity activity = new Activity("walk", "shop", 2.5);

        Activity returnedActivity = pacemaker.createActivity(homer.id, activity.type, activity.location, activity.distance);
        assertEquals(activity.type, returnedActivity.type);
        assertEquals(activity.location, returnedActivity.location);
        assertEquals(activity.distance, returnedActivity.distance, 0.001);
        assertNotNull(returnedActivity.id);
    }

    @Test
    public void testGetActivity() {
        Activity activity = new Activity("run", "fridge", 0.5);
        Activity returnedActivity1 = pacemaker.createActivity(homer.id, activity.type, activity.location, activity.distance);
        Activity returnedActivity2 = pacemaker.getActivity(returnedActivity1.id);
        assertEquals(returnedActivity1, returnedActivity2);
    }

    @Test
    public void testDeleteActivity() {
        Activity activity = new Activity("sprint", "pub", 4.5);
        Activity returnedActivity = pacemaker.createActivity(homer.id, activity.type, activity.location, activity.distance);
        assertNotNull (returnedActivity);
        pacemaker.deleteActivities(homer.id);
        returnedActivity = pacemaker.getActivity(returnedActivity.id);
        assertNull (returnedActivity);
    }
}