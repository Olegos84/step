package org.itstep.selenium.product.api.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.itstep.selenium.product.api.model.User;
import org.testng.Assert;

public class UserService extends BaseApiService {

  public static final String PATH = "/users";

  public List<User> getAll() {
    Response response = RestAssured.given()
        .baseUri(BASE_URL)
        .when()
        .get(PATH);
    Assert.assertEquals(response.getStatusCode(), 200);
    return Arrays.asList(response.as(User[].class));
  }

  public User getById(Long id) {
    Response response = RestAssured.given()
        .baseUri(BASE_URL)
        .when()
        .get(PATH + "/" + id);
    Assert.assertEquals(response.getStatusCode(), 200);
    return response.as(User.class);
  }

  public User getByEmail(String email) {
    List<User> users = getAll();
    Optional<User> first = users.stream()
        .filter(u -> u.getEmail().equals(email))
        .findFirst();
    return first.orElse(null);
  }
}
