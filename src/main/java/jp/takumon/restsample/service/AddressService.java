package jp.takumon.restsample.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.takumon.restsample.datasource.AddressRepository;
import jp.takumon.restsample.domain.Address;
import jp.takumon.restsample.domain.City;
import jp.takumon.restsample.domain.State;
import jp.takumon.restsample.error.ProcessException;

/**
 * 住所検索サービス
 * 
 * @author takumon
 */
@Service
public class AddressService {

  private static final Pattern ADDRESS_ZIP_CODE_PATTERN = Pattern.compile("^\\d{3}-\\d{4}$");

  @Autowired
  private AddressRepository addressRepository;


  public List<State> getStates() {
    return addressRepository.getStates();
  }


  public List<City> getCities(String stateId) {
    int id;
    try {
      id = Integer.parseInt(stateId);
    } catch (NumberFormatException e) {
      throw new ValidationException(String.format("指定したstateId[%s]は数値ではありません。", stateId));
    }
    List<City> result = addressRepository.getCities(id);
    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したstateId[%s]に紐づく住所は見つかりませんでした。", id));
    }
    return result;
  }


  public List<Address> getAddressWithStateAndKeywork(String stateId, String keyword) {
    int id;
    try {
      id = Integer.parseInt(stateId);
    } catch (NumberFormatException e) {
      throw new ValidationException(String.format("指定したstateId[%s]は数値ではありません。", stateId));
    }
    List<Address> result = addressRepository.findByStateAndKeyword(id, keyword);
    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したstateId[%d]とkeyword[%s]に紐づく住所は見つかりませんでした。", id, keyword));
    }
    return result;
  }


  public List<Address> getSections(String cityId) {
    int id;
    try {
      id = Integer.parseInt(cityId);
    } catch (NumberFormatException e) {
      throw new ValidationException(String.format("cityId[%s]は数値を指定してください。", cityId));
    }
    List<Address> result = addressRepository.getSections(id);
    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したcityId[%s]に紐づく住所は見つかりませんでした。", cityId));
    }
    return result;
  }


  public List<Address> getAddress(String addressZipCode) {
    if (ADDRESS_ZIP_CODE_PATTERN.matcher(addressZipCode).find() == false) {
      throw new ValidationException(String.format("指定したzipCode[%s]が郵便番号形式ではありません。", addressZipCode));
    }
    
    List<Address> result = addressRepository.findByZip(addressZipCode);
    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したzipCode[%s]に紐づく住所は見つかりませんでした。", addressZipCode));
    }
    return result;
  }


  public List<Address> getAddresses(String part) {
    List<Address> result = addressRepository.findByKeyword(part);
    if (result.isEmpty()) {
      throw new ProcessException(String.format("指定したpart[%s]に紐づく住所は見つかりませんでした。", part));
    }
    return result;
  }
}
