package org.itstep.selenium.test.api;

import java.util.List;
import org.itstep.selenium.product.api.model.User;
import org.itstep.selenium.product.api.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

  @Test
  public void test() {
    List<User> users = new UserService().getAll();
    Assert.assertTrue(users.size() >= 10);
  }

  @Test
  public void test1() {
    User user = new UserService().getById(1L);
    Assert.assertTrue(user != null);
  }

  @Test
  public void test3() {
    User user = new UserService().getByEmail("Telly.Hoeger@billy.biz");
    Assert.assertEquals(user.getId(), new Long(7));
  }
}
