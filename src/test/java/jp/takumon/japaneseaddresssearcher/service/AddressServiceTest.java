package jp.takumon.japaneseaddresssearcher.service;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import jp.takumon.japaneseaddresssearcher.datasource.AddressRepository;
import jp.takumon.japaneseaddresssearcher.domain.State;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
  @MockBean
  private AddressRepository addressRepository;
  
  @Autowired
  AddressService addressService;
  
  
  @Test
  public void getStates_検索結果を返す() {
    List<State> states = new ArrayList<>();
    states.add(createState(1, "北海道", "ホッカイドウ", 600));
    states.add(createState(5, "秋田県", "アキタケン", 300));
    
    given(this.addressRepository.getStates()).willReturn(states);
    
    List<State> actual = addressService.getStates();
    assertThat(actual, is(states));
  }
  
  private State createState(
      int stateId,
      String stateName, 
      String stateKana,
      int stateCityCount) {
    State result = new State();
    result.setStateId(stateId);
    result.setStateName(stateName);
    result.setStateKana(stateKana);
    result.setStateCityCount(stateCityCount);
    return result;
  }
}
