package jp.takumon.japaneseaddresssearcher.web;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.takumon.japaneseaddresssearcher.TestHelper;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.State;
import jp.takumon.japaneseaddresssearcher.service.AddressService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AddressRestController.class)
public class AddressRestControllerTest {
  @Autowired
  private MockMvc mvc;
  
  @Autowired
  private ObjectMapper mapper;
  
  @MockBean
  private AddressService addressService;
  
  
  @Test
  public void getStates_正常() throws Exception{
    List<State> states = new ArrayList<>();
    states.add(TestHelper.createState(13, "東京都", "トウキョウト", 600));
    states.add(TestHelper.createState(40, "福岡県", "フクオカケン", 300));
    
    given(this.addressService.getStates()).willReturn(states);
    
    this.mvc.perform(get("/api/states").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(states)));
    
  }
  
  @Test
  public void getCities_正常() throws Exception {
    List<City> cities = new ArrayList<>();
    cities.add(TestHelper.createCity(40133, "福岡市中央区", "フクオカシチュウオウク", 500, 40, "福岡県", "フクオカケン"));

    given(this.addressService.getCities("40")).willReturn(cities);
    
    this.mvc.perform(get("/api/cities/stateId/40").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(cities)));
  }
}
