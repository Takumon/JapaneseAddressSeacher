package jp.takumon.japaneseaddresssearcher.web;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.domain.City;
import jp.takumon.japaneseaddresssearcher.domain.Section;
import jp.takumon.japaneseaddresssearcher.domain.State;
import jp.takumon.japaneseaddresssearcher.service.AddressService;
import jp.takumon.japaneseaddresssearcher.web.validate.AddressSortFormat;

/**
 * 住所検索用のRESTコントローラー
 * 
 * @author takumon
 */
@RestController
@RequestMapping("/api/v1/jp")
public class AddressRestController {

  
  @Autowired
  private AddressService addressService;


  @RequestMapping(value = "/states", method = RequestMethod.GET)
  public List<State> getStates() {
    return addressService.getStates();
  }

  
  @RequestMapping(value = "/states/{stateName}/cities", method = RequestMethod.GET)
  public List<City> getCities(
      @NotBlank @PathVariable(value = "stateName") String stateName) {
    System.out.println(stateName);
    return addressService.getCities(stateName);
  }

  
  @RequestMapping(value = "/states/{stateName}/cities/{cityName}/sections", method = RequestMethod.GET)
  public List<Section> getSections(
      @NotBlank @PathVariable(value = "stateName") String stateName,
      @NotBlank @PathVariable(value = "cityName") String cityName) {
    
    return addressService.getSections(stateName, cityName);
  }
  
  
  @RequestMapping(value = "/states/{stateName}/cities/{cityName}/sections/{sectionName}", method = RequestMethod.GET)
  public List<Address> getAddress(
      @PathVariable(value = "stateName") String stateName,
      @PathVariable(value = "cityName") String cityName,
      @PathVariable(value = "sectionName") String sectionName) {
    
    return addressService.getAddress(stateName,cityName, sectionName);
  }
  
  
  @RequestMapping(value = "/addresses/{addressZipCode}", method = RequestMethod.GET)
  public List<Address> getAddress(
      @Pattern(regexp="^\\d{3}-\\d{4}") @PathVariable(value = "addressAipCode") String addressZipCode) {
    return addressService.getAddress(addressZipCode);
  }
  
  
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public List<Address> search(
      @RequestParam(value = "q") String keyword,
      @NumberFormat @RequestParam(value = "offset") String offset,
      @NumberFormat @RequestParam(value = "limit") String limit,
      @AddressSortFormat @RequestParam(value = "sort") String sort) {
    return addressService.searchAddresses(keyword);
  }



  
}