package jp.takumon.japaneseaddresssearcher.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.takumon.japaneseaddresssearcher.App;
import jp.takumon.japaneseaddresssearcher.TestHelper;
import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.service.AddressService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class,
    initializers = ConfigFileApplicationContextInitializer.class)
public class AddressControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @MockBean
  private AddressService addressService;

  private MockMvc mvc;


  @Before
  public void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void form_正常() throws Exception {
    this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("form"))
        .andExpect(content().string(containsString("<title>住所検索</title>")));

  }

  @Test
  public void searchAddress_正常() throws Exception {
    List<Address> addresses = new ArrayList<>();
    addresses.add(TestHelper.createAddress("001-0011", 11020012, "北十一条西", "キタ１１ジョウニシ", 1102,
        "札幌市北区", "サッポロシキタク", 1, "北海道", "ホッカイドウ"));

    given(addressService.getAddress("001-0011")).willReturn(addresses);

    this.mvc.perform(post("/address/search").param("addressZipCode", "001-0011"))
        .andExpect(status().isOk())
        .andExpect(view().name("form"))
        .andExpect(model().attribute("addressList", addresses))
        .andExpect(content().string(containsString("<title>住所検索アプリ</title>")))
        .andExpect(content().string(containsString("検索結果")));
  }

  @Test
  public void searchAddress_addresZipCodeがnull() throws Exception {
    List<Address> addresses = new ArrayList<>();
    addresses.add(TestHelper.createAddress("001-0011", 11020012, "北十一条西", "キタ１１ジョウニシ", 1102,
        "札幌市北区", "サッポロシキタク", 1, "北海道", "ホッカイドウ"));

    given(addressService.getAddress(anyString())).willReturn(addresses);

    // リクエストパラメータにaddressZipCodeを定義しない
    this.mvc.perform(post("/address/search"))
        .andExpect(status().is(400));
  }
}
