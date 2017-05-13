package jp.takumon.japaneseaddresssearcher.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
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
@ContextConfiguration(classes = App.class, initializers = ConfigFileApplicationContextInitializer.class)
@WithMockUser(username = "user", password = "pass")
public class AddressControllerTest {

	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private AddressService addressService;

	private MockMvc mvc;
	
	private WebClient webClient;

	@Before
	public void setup() {
	  mvc = MockMvcBuilders
	              .webAppContextSetup(context)
	              .addFilters(springSecurityFilterChain)
	              .defaultRequest(get("/").with(user("admin").password("pass")))
	              .build();

	  webClient = new WebClient();
	  webClient.setWebConnection(new MockMvcWebConnection(mvc));
	}
	

	@After
	public void cleanup() {
		this.webClient.close();
	}

	@Test
	public void form_正常() throws Exception {
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getCookieManager().setCookiesEnabled(true);

		HtmlPage addressSearchPage = webClient.getPage("http://localhost:8080/");
		assertThat(addressSearchPage.getTitleText()).isEqualTo("住所検索");
		assertThat(addressSearchPage.getElementByName("addressZipCode").getAttribute("value")).isEqualTo("");
		assertThat(addressSearchPage.getElementById("search-result-area")).isNull();
	}

	@Test
	public void searchAddress_正常() throws Exception {
		List<Address> addresses = new ArrayList<>();
		addresses.add(TestHelper.createAddress("001-0011", 11020012, "北十一条西", "キタ１１ジョウニシ", 1102, "札幌市北区", "サッポロシキタク", 1,
				"北海道", "ホッカイドウ"));

		given(addressService.getAddress("001-0011")).willReturn(addresses);

		mvc.perform(post("/address/search").param("addressZipCode", "001-0011"))
		        .andExpect(status().isOk())
				.andExpect(view().name("form")).andExpect(model().attribute("addressList", addresses))
				.andExpect(content().string(containsString("<title>住所検索</title>")))
				.andExpect(content().string(containsString("検索結果")));
	}

	@Test
	public void searchAddress_addresZipCodeがnull() throws Exception {
		List<Address> addresses = new ArrayList<>();
		addresses.add(TestHelper.createAddress("001-0011", 11020012, "北十一条西", "キタ１１ジョウニシ", 1102, "札幌市北区", "サッポロシキタク", 1,
				"北海道", "ホッカイドウ"));

		given(addressService.getAddress(anyString())).willReturn(addresses);

		// リクエストパラメータにaddressZipCodeを定義しない
		mvc.perform(post("/address/search")).andExpect(status().is(400));
	}
}
