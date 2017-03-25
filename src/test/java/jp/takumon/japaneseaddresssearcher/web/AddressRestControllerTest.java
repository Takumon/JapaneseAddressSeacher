package jp.takumon.japaneseaddresssearcher.web;


import java.net.URL;
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
import org.springframework.web.util.UriUtils;

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
    
    this.mvc.perform(get("/api/v1/jp/states").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(states)));
    
  }
  
  @Test
  public void getCities_正常() throws Exception {
    List<City> cities = new ArrayList<>();
    cities.add(TestHelper.createCity(40133, "福岡市中央区", "フクオカシチュウオウク", 500, 40, "福岡県", "フクオカケン"));
    // 日本語URLはあらかじめエンコードしておく必要がある
    String stateName = UriUtils.encode("福岡県", "utf-8");
    
    given(this.addressService.getCities(stateName)).willReturn(cities);
    
    System.out.println(stateName);
    this.mvc.perform(get("/api/v1/jp/states/" + stateName + "/cities").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(cities)));
  }
}
