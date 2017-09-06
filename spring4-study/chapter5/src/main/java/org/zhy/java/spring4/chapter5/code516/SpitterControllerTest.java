package org.zhy.java.spring4.chapter5.code516;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class SpitterControllerTest {
	
	@Test
	public void shouldProcessRegistration() throws Exception{
		SpitterRepository spitterRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer");
		Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
		
		when(spitterRepository.save(unsaved)).thenReturn(saved);
		
		SpitterController spitterController = new SpitterController(spitterRepository);
		MockMvc mockMvc = standaloneSetup(spitterController).build();
		mockMvc.perform(post("/spitter/register").param("firstName", "Jack")
				.param("lastName", "Bauer")
				.param("userName", "jbauer")
				.param("password", "24hours"))
		.andExpect(redirectedUrl("/spitter/jbauer"));
		
		verify(spitterRepository, atLeastOnce()).save(unsaved);
	}
	
}
