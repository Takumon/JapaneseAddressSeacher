package jp.takumon.japaneseaddresssearcher.datasource;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.takumon.japaneseaddresssearcher.App;
import jp.takumon.japaneseaddresssearcher.domain.Address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class,
    initializers = ConfigFileApplicationContextInitializer.class)
public class AddressRepositoryTest {

  @Rule
  public ErrorCollector collector = new ErrorCollector();
  
  @Autowired
  private AddressRepository addressRepository;

  @Test
  public void findByZip_正常() {
    List<Address> actual = addressRepository.findByZip("146-0093");
    assertThat(actual.size()).isEqualTo(1);

    collector.checkThat(actual.get(0).getAddressZipCode(), equalTo("146-0093"));
    collector.checkThat(actual.get(0).getStateId(), equalTo(13));
    collector.checkThat(actual.get(0).getStateName(), equalTo("東京都"));
    collector.checkThat(actual.get(0).getStateKana(), equalTo("トウキョウト"));
    collector.checkThat(actual.get(0).getCityId(), equalTo(13111));
    collector.checkThat(actual.get(0).getCityName(), equalTo("大田区"));
    collector.checkThat(actual.get(0).getCityKana(), equalTo("オオタク"));
    collector.checkThat(actual.get(0).getSectionId(), equalTo(131110060));
    collector.checkThat(actual.get(0).getSectionName(), equalTo("矢口"));
    collector.checkThat(actual.get(0).getSectionKana(), equalTo("ヤグチ"));
  }

  @Test
  public void findByZip_addressZipCodが存在しない郵便番号() {
    List<Address> actual = addressRepository.findByZip("999-9999");
    assertThat(actual.size()).isEqualTo(0);
  }

}
