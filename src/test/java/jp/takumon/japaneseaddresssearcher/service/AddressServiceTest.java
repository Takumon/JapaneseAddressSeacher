package jp.takumon.japaneseaddresssearcher.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import jp.takumon.japaneseaddresssearcher.TestHelper;
import jp.takumon.japaneseaddresssearcher.datasource.AddressRepository;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.State;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

  @MockBean
  private AddressRepository addressRepository;

  @Autowired
  AddressService addressService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void getStates_正常() {
    List<State> states = new ArrayList<>();
    states.add(TestHelper.createState(13, "東京都", "トウキョウト", 600));
    states.add(TestHelper.createState(40, "福岡県", "フクオカケン", 300));
    given(addressRepository.getStates()).willReturn(states);

    List<State> actual = addressService.getStates();
    assertThat(actual).isEqualTo(states);
  }


  @Test
  public void getCities_正常() {
    List<City> cities = new ArrayList<>();
    cities.add(TestHelper.createCity(13101, "千代田区", "チヨダク", 500, 13, "東京都", "トウキョウト"));
    cities.add(TestHelper.createCity(40133, "福岡市中央区", "フクオカシチュウオウク", 500, 40, "福岡県", "フクオカケン"));

    given(addressRepository.getCities(anyString())).willReturn(cities);
    List<City> actual = addressService.getCities("福岡県");
    assertThat(actual).isEqualTo(cities);
  }
}
