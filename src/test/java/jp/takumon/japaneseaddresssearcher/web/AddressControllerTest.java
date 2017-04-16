package jp.takumon.japaneseaddresssearcher.web;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jp.takumon.japaneseaddresssearcher.App;
import jp.takumon.japaneseaddresssearcher.TestHelper;
import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.service.AddressService;


@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class,
    initializers = ConfigFileApplicationContextInitializer.class)
public class AddressControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @MockBean
  private AddressService addressService;

  @Autowired
  private WebClient webClient;

  private MockMvc mvc;


  @Before
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void form_正常() throws Exception {
    HtmlPage page = webClient. getPage("/");
    assertThat(page.getTitleText()).isEqualTo("住所検索");
    assertThat(page.getElementByName("addressZipCode").getAttribute("value")).isEqualTo("");
    assertThat(page.getElementById("search-result-area")).isNull();


  }

  @Test
  public void searchAddress_正常() throws Exception {
    List<Address> addresses = new ArrayList<>();
    addresses.add(TestHelper.createAddress("001-0011", 11020012, "北十一条西", "キタ１１ジョウニシ", 1102,
        "札幌市北区", "サッポロシキタク", 1, "北海道", "ホッカイドウ"));

    given(addressService.getAddress("001-0011")).willReturn(addresses);

    mvc.perform(post("/address/search").param("addressZipCode", "001-0011"))
        .andExpect(status().isOk())
        .andExpect(view().name("form"))
        .andExpect(model().attribute("addressList", addresses))
        .andExpect(content().string(containsString("<title>住所検索</title>")))
        .andExpect(content().string(containsString("検索結果")));
  }

  @Test
  public void searchAddress_addresZipCodeがnull() throws Exception {
    List<Address> addresses = new ArrayList<>();
    addresses.add(TestHelper.createAddress("001-0011", 11020012, "北十一条西", "キタ１１ジョウニシ", 1102,
        "札幌市北区", "サッポロシキタク", 1, "北海道", "ホッカイドウ"));

    given(addressService.getAddress(anyString())).willReturn(addresses);

    // リクエストパラメータにaddressZipCodeを定義しない
    mvc.perform(post("/address/search"))
        .andExpect(status().is(400));
  }
}
