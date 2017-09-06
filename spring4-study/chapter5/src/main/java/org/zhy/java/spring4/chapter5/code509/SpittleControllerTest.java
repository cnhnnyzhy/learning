package org.zhy.java.spring4.chapter5.code509;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import org.zhy.java.spring4.chapter5.code508.Spittle;
import org.zhy.java.spring4.chapter5.code508.SpittleRepository;

public class SpittleControllerTest {
	
	@Test
	public void shouldShowRecentSpittles() throws Exception{
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository spittleRepository = mock(SpittleRepository.class);
		when(spittleRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		
		SpittleController spittleController = new SpittleController(spittleRepository);
		MockMvc mockMvc = standaloneSetup(spittleController)
							.setSingleView(
									new InternalResourceView("/WEB-INF/views/spittles.jsp"))
							.build();
		mockMvc.perform(get("/spittles"))
		.andExpect(view().name("spittles"))
		.andExpect(model().attributeExists("spittleList"))
		.andExpect(model().attribute("spittleList", IsCollectionContaining.hasItems(expectedSpittles.toArray())));
	}
	
	private List<Spittle> createSpittleList(int count){
		List<Spittle> list = new ArrayList<>();
		for(int i=0; i<count; i++){
			list.add(new Spittle("Spittle " + i, new Date()));
		}
		return list;
	}
}
