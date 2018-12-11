package org.acme.eshop;

import lombok.extern.slf4j.Slf4j;
import org.acme.eshop.model.Category;
import org.acme.eshop.model.User;
import org.acme.eshop.service.CategoryService;
import org.acme.eshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EshopApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

	@Test
	public void contextLoads() {
        Category category = Category.builder().description("hahaha").build();
        assertTrue(categoryService.create(category).getId() >= 20);

	}

	@Test
    public void applicationRunnerTest() {
	    User user = User.builder().build();
	    user.setId(10L);

	    assertTrue(userService.findAll().size() == 100);
    }
}
