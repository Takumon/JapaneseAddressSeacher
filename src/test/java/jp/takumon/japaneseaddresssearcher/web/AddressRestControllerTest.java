package jp.takumon.japaneseaddresssearcher.web;


import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.takumon.japaneseaddresssearcher.TestHelper;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.State;
import jp.takumon.japaneseaddresssearcher.service.AddressService;


@RunWith(SpringRunner.class)
@WebMvcTest(AddressRestController.class)
public class AddressRestControllerTest {
  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private AddressService addressService;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private Filter springSecurityFilterChain;

  private MockMvc mvc;

  @Before
  public void setup() {
      mvc = MockMvcBuilders
              .webAppContextSetup(context)
              .addFilters(springSecurityFilterChain)
              .build();
  }
  

  @Test
  public void getStates_正常() throws Exception{
	  
    List<State> states = new ArrayList<>();
    states.add(TestHelper.createState(13, "東京都", "トウキョウト", 600));
    states.add(TestHelper.createState(40, "福岡県", "フクオカケン", 300));

    given(addressService.getStates()).willReturn(states);

    mvc.perform(get("/api/v1/jp/states")
    		.with(user("admin").password("pass"))
    		.accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(states)));

  }

  @Test
  public void getCities_正常() throws Exception {
    List<City> cities = new ArrayList<>();
    cities.add(TestHelper.createCity(40133, "福岡市中央区", "フクオカシチュウオウク", 500, 40, "福岡県", "フクオカケン"));
    // 日本語URLはあらかじめエンコードしておく必要がある
    String stateName = UriUtils.encode("福岡県", "utf-8");

    given(addressService.getCities(stateName)).willReturn(cities);

    mvc.perform(get("/api/v1/jp/states/" + stateName + "/cities")
    		.with(user("admin").password("pass"))
    		.accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(cities)));
  }
}
