package jp.takumon.japaneseaddresssearcher.datasource;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.Section;
import jp.takumon.japaneseaddresssearcher.domain.State;

/**
 * 住所リポジトリ
 * 
 * @author takumon
 */
@ConfigAutowireable
@Dao
public interface AddressRepository {

  @Select
  List<State> getStates();


  @Select
  List<City> getCities(String stateName);


  @Select
  List<Section> getSections(String stateName, String cityName);


  @Select
  List<Address> find(String stateName, String cityName, String sectionName);


  @Select
  List<Address> findByAddressZipCode(String addressZipCode);


  @Select
  List<Address> findByKeyword(String keyword);
}
