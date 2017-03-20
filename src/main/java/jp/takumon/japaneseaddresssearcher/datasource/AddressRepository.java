package jp.takumon.japaneseaddresssearcher.datasource;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
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
  List<City> getCities(int stateId);


  @Select
  List<Address> getSections(int cityId);


  @Select
  List<Address> findByZip(String addressZipCode);


  @Select
  List<Address> findByKeyword(String keyword);


  @Select
  List<Address> findByStateAndKeyword(int stateId, String keyword);
}
