package integration.rest;

import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import models.users.Gender;
import models.users.User;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.*;

public class IntegrationTest extends WithApplication{
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testGetExistingUser() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/users/1");
        final Result result = route(request);
        TestCase.assertEquals(OK, result.status());
    }

    @Test
    public void testGetNonExistingUser() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/users/123123123");
        final Result result = route(request);
        TestCase.assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void testLoginCorrect(){
        Map<String, String[]> loginData = new TreeMap<String, String[]>();
        loginData.put("email", new String[]{"silvio.berlusconi@italy.it"});
        loginData.put("password", new String[]{"123456"});
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/users/login")
                .bodyFormArrayValues(loginData);
        final Result result = route(request);
        TestCase.assertTrue(Helpers.contentAsString(result).contains("silvio.berlusconi@italy.it"));
    }

    @Test
    public void testLoginCorrectWithCharReplacement(){
        Map<String, String[]> loginData = new TreeMap<String, String[]>();
        loginData.put("email", new String[]{"silvio.berlusconi%40italy.it"});
        loginData.put("password", new String[]{"123456"});

        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/users/login")
                .bodyFormArrayValues(loginData);
        final Result result = route(request);
        TestCase.assertTrue(Helpers.contentAsString(result).contains("silvio.berlusconi@italy.it"));
    }

    @Test
    public void testLoginIncorrect(){
        Map<String, String[]> loginData = new TreeMap<String, String[]>();
        loginData.put("email", new String[]{"silvio.berlusconi@italy.it"});
        loginData.put("password", new String[]{"1234567"});
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/users/login")
                .bodyFormArrayValues(loginData);
        final Result result = route(request);
        TestCase.assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testCreateUserFromJson() throws ParseException {
        final JsonNode json = Json.toJson(new User(1, "elon.musk@hsr.ch", "Musk", "Elon", dateFormat.parse("1971-06-28"), Gender.MALE));
        final User user = new User(1, "elon.musk@hsr.ch", "Musk", "Elon", dateFormat.parse("1971-06-28"), Gender.MALE);
        final User userResult = Json.fromJson(json, User.class);

        TestCase.assertEquals(user, userResult);
    }

    @Test
    public void testRegisterUser() throws ParseException {
        String email = "elon.musk@hsr.ch";

        final User createdUser = new User(null,
                email,
                "Musk",
                "Elon",
                dateFormat.parse("1971-06-28"),
                Gender.MALE);
        final JsonNode createdUserJson = Json.toJson(createdUser);

        final Http.RequestBuilder createUserRequest = new Http.RequestBuilder()
                .method(POST)
                .uri("/users/register")
                .bodyJson(createdUserJson);
        final Result createUserResult = route(createUserRequest);
        TestCase.assertEquals(OK, createUserResult.status());

        final Http.RequestBuilder checkUserRequest = new Http.RequestBuilder()
                .method(GET)
                .uri("/users/mail/elon.musk@hsr.ch");
        final Result checkUserResult = route(checkUserRequest);
        String resultUserString = Helpers.contentAsString(checkUserResult);
        assertTrue(resultUserString.contains(email));
    }

    @Test
    public void testGetEventById() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/1");
        final Result result = route(request);
        TestCase.assertEquals(OK, result.status());
    }
}
