package com.home.aatravel.bdd.users.steps;

import com.home.aatravel.bdd.CucumberIntegrationTest;
import com.home.aatravel.entity.User;
import com.home.aatravel.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


public class UserStepDefinition extends CucumberIntegrationTest {

    @Autowired
    private UserService userService;

    @Given("^user with provided username (.*) doesn't exists$")
    public void userWithProvidedUsernameProvided_nameDoesnTExsits(String provided_name) {
        assertNull(userService.findByName(provided_name));
    }

    @When("^username (.*) is passed in to create an new user$")
    public void usernameProvided_nameIsPassedInToCreateAnNewUser(String provided_name) {
        userService.save(new User(provided_name));
    }

    @Then("^The user with (.*) is created$")
    public void theUserWithUser_nameIsCreated(String user_name) {
        User user = userService.findByName(user_name);
        assertNotNull(user);
        assertEquals(user_name, user.getName());
    }

}
