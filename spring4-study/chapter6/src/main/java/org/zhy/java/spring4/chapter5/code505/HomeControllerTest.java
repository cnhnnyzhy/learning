package org.zhy.java.spring4.chapter5.code505;

import static org.junit.Assert.*;
import org.junit.Test;
import org.zhy.java.spring4.chapter5.code503.HomeController;

public class HomeControllerTest {
	@Test
	public void testHomePage(){
		HomeController controller = new HomeController();
		assertEquals("home", controller.home());
	}
}
