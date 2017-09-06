package org.zhy.java.spring4.chapter5.code514;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.zhy.java.spring4.chapter5.code513.SpittleController;

public class SpittleControllerTest {
	
	@Test
	public void shouldShowRegistration() throws Exception{
		SpittleController spittleController = new SpittleController();
		MockMvc mockMvc = standaloneSetup(spittleController)
							.build();
		mockMvc.perform(get("/spitter/register"))
		.andExpect(view().name("registerForm"));
	}
	
}
