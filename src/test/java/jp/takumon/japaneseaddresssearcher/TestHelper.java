package jp.takumon.japaneseaddresssearcher;


import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.State;

/**
 * テストで使用する共通処理をまとめたクラス.
 * 
 * @author takumon
 */
public class TestHelper {

  /**
   * {@link State}オブジェクトを生成する.
   * 
   * @param stateId
   * @param stateName
   * @param stateKana
   * @param stateCityCount
   * @return {@link State}オブジェクト
   */
  public static State createState(
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
  
  
  /**
   * {@link City}オブジェクトを生成する.
   * 
   * @param cityName
   * @param cityKana
   * @param citySectionCount
   * @param stateId
   * @param stateName
   * @param stateKana
   * @return {@link City}オブジェクト}
   */
  public static City createCity(
      int cityId, 
      String cityName, 
      String cityKana, 
      int citySectionCount,
      int stateId, 
      String stateName, 
      String stateKana) {
    
    City result = new City();
    result.setCityId(cityId);
    result.setCityName(cityName);
    result.setCityKana(cityKana);
    result.setCitySectionCount(citySectionCount);
    result.setStateId(stateId);
    result.setStateName(stateName);
    result.setStateKana(stateKana);
    return result;
  }
  
  

  /**
   * {@link Address}オブジェクトを生成する.
   * 
   * @param addressZipCode
   * @param sectionId
   * @param sectionName
   * @param sectionKana
   * @param cityId
   * @param cityName
   * @param cityKana
   * @param stateId
   * @param stateName
   * @param stateKana
   * @return {@link Address}オブジェクト
   */
  public static Address createAddress(
      String addressZipCode,
      int sectionId,
      String sectionName,
      String sectionKana,
      int cityId,
      String cityName,
      String cityKana,
      int stateId,
      String stateName,
      String stateKana) {
    
    Address result = new Address();
    
    result.setAddressZipCode(addressZipCode);
    result.setSectionId(sectionId);
    result.setSectionName(sectionName);
    result.setSectionKana(sectionKana);
    result.setCityId(cityId);
    result.setCityName(cityName);
    result.setCityKana(cityKana);
    result.setStateId(stateId);
    result.setStateId(stateId);
    result.setStateName(stateName);
    result.setStateKana(stateKana);
    return result;
  }



}
