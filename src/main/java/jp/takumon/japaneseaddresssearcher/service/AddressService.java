package jp.takumon.japaneseaddresssearcher.service;

import java.util.List;

import javax.validation.ValidationException;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.takumon.japaneseaddresssearcher.datasource.AddressRepository;
import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.Section;
import jp.takumon.japaneseaddresssearcher.domain.State;
import jp.takumon.japaneseaddresssearcher.error.ProcessException;
import jp.takumon.japaneseaddresssearcher.service.convert.AddressSort;
import jp.takumon.japaneseaddresssearcher.service.convert.AddressSortHelper;

/**
 * 住所検索サービス
 *
 * @author takumon
 */
@Service
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;


  public List<State> getStates() {
    return addressRepository.getStates();
  }


  public State getState(String stateName) {
    State result = addressRepository.getState(stateName);

    if (result == null) {
      throw new ProcessException(
          String.format("指定したstateName[%s]に紐づくStateは見つかりませんでした。", stateName));
    }
    return result;
  }


  public List<City> getCities(String stateName) {
    List<City> result = addressRepository.getCities(stateName);

    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したstateName[%s]に紐づCityは見つかりませんでした。", stateName));
    }
    return result;
  }


  public City getCity(String stateName, String cityName) {
    City result = addressRepository.getCity(stateName, cityName);

    if (result == null) {
      throw new ProcessException("指定したstateName[%s]とcityName[%s]に紐づくCityは見つかりませんでした。");
    }
    return result;
  }


  public List<Section> getSections(String stateName, String cityName) {
    List<Section> result = addressRepository.getSections(stateName, cityName);

    if (result.isEmpty()) {
      throw new ProcessException(String
          .format("指定したstateName[%s]とcityName[%s]に紐づくSectionは見つかりませんでした。", cityName, cityName));
    }
    return result;
  }


  public Section getSection(String stateName, String cityName, String sectionName) {
    Section result = addressRepository.getSection(stateName, cityName, sectionName);

    if (result == null) {
      throw new ProcessException(
          String.format("指定したstateName[%s]とcityName[%s]とsectopmName[%s]に紐づくSectionは見つかりませんでした。",
              stateName, cityName, sectionName));
    }
    return result;
  }


  public List<Address> getAddress(String stateName, String cityName, String sectionName) {
    List<Address> result = addressRepository.find(stateName, cityName, sectionName);

    if (result.isEmpty()) {
      throw new ProcessException(
          String.format("指定したstateName[%s]cityName[%s]sectionName[%s]に紐づく住所は見つかりませんでした。", stateName,
              cityName, sectionName));
    }
    return result;
  }


  public List<Address> getAddress(String addressZipCode) {
    List<Address> result = addressRepository.findByAddressZipCode(addressZipCode);

    if (result.isEmpty()) {
      throw new ProcessException(
          String.format("指定したzipCode[%s]に紐づく住所は見つかりませんでした。", addressZipCode));
    }
    return result;
  }


  public List<Address> searchAddresses(String keyword, String sort, int offset, int limit) {
    if (AddressSortHelper.isValid(sort) == false) {
      throw new ValidationException(AddressSortHelper.errorMassage());
    }

    List<AddressSort> convertedSort = AddressSortHelper.convert(sort);
    String orderBy = AddressSortHelper.convertOrderByInSql(convertedSort);

    SelectOptions options = SelectOptions.get().offset(offset).limit(limit);

    List<Address> result = addressRepository.findByKeyword(keyword, orderBy, options);

    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したkeyword[%s]に紐づく住所は見つかりませんでした。", keyword));
    }
    return result;
  }
}
