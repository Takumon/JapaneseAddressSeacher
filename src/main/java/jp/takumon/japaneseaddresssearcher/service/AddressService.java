package jp.takumon.japaneseaddresssearcher.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.takumon.japaneseaddresssearcher.datasource.AddressRepository;
import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.Section;
import jp.takumon.japaneseaddresssearcher.domain.State;
import jp.takumon.japaneseaddresssearcher.error.ProcessException;

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


  public List<City> getCities(String stateName) {
    List<City> result = addressRepository.getCities(stateName);

    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したstateName[%s]に紐づCityは見つかりませんでした。", stateName));
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


  public List<Address> searchAddresses(String keyword) {
    List<Address> result = addressRepository.findByKeyword(keyword);

    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したkeyword[%s]に紐づく住所は見つかりませんでした。", keyword));
    }
    return result;
  }
}
