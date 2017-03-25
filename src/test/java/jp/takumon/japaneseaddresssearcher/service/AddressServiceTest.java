package jp.takumon.japaneseaddresssearcher.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
    states.add(createState(1, "北海道", "ホッカイドウ", 600));
    states.add(createState(5, "秋田県", "アキタケン", 300));
    given(this.addressRepository.getStates()).willReturn(states);
    List<State> actual = addressService.getStates();
    assertThat(actual).isEqualTo(states);
  }


  @Test
  public void getCities_正常() {
    List<City> cities = new ArrayList<>();
    cities.add(createCity(500, "福岡県", "フクオカケン", 100));
    cities.add(createCity(1000, "東京都", "トウキョウト", 10));
    given(this.addressRepository.getCities(anyInt())).willReturn(cities);
    List<City> actual = addressService.getCities("40");
    assertThat(actual).isEqualTo(cities);
  }


  @Test
  public void getCities_stateIdが数値ではない() {
    List<City> cities = new ArrayList<>();
    cities.add(createCity(500, "福岡県", "フクオカケン", 100));
    given(this.addressRepository.getCities(anyInt())).willReturn(cities);
    thrown.expect(ValidationException.class);
    thrown.expectMessage("指定したstateId[文字]は数値ではありません。");
    addressService.getCities("文字");
  }


  @Test
  public void getCities_stateIdがnull() {
    List<City> cities = new ArrayList<>();
    cities.add(createCity(500, "福岡県", "フクオカケン", 100));
    given(this.addressRepository.getCities(anyInt())).willReturn(cities);
    thrown.expect(ValidationException.class);
    thrown.expectMessage("指定したstateId[null]は数値ではありません。");
    addressService.getCities(null);
  }


  private City createCity(int cityId, String cityName, String cityKana, int citySectionCount) {
    City result = new City();
    result.setCityId(cityId);
    result.setCityName(cityName);
    result.setCityKana(cityKana);
    result.setCitySectionCount(citySectionCount);
    return result;
  }


  private State createState(int stateId, String stateName, String stateKana, int stateCityCount) {
    State result = new State();
    result.setStateId(stateId);
    result.setStateName(stateName);
    result.setStateKana(stateKana);
    result.setStateCityCount(stateCityCount);
    return result;
  }
}
