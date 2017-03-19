package jp.takumon.restsample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.takumon.restsample.domain.Address;
import jp.takumon.restsample.domain.City;
import jp.takumon.restsample.domain.State;
import jp.takumon.restsample.service.AddressService;

/**
 * 住所検索用のRESTコントローラー
 * 
 * @author takumon
 */
@RestController
@RequestMapping("/api")
public class AddressRestController {

  @Autowired
  private AddressService addressService;


  @RequestMapping("/states")
  public List<State> getStates() {
    return addressService.getStates();
  }


  @RequestMapping("/cities/stateId/{stateId}")
  public List<City> getCities(@PathVariable(value = "stateId") String stateId) {
    return addressService.getCities(stateId);
  }


  @RequestMapping("/addresses/stateId/{stateId}")
  public List<Address> getAddressWithStateAndKeywork(@PathVariable(value = "stateId") String stateId, @RequestParam(value = "part") String part) {
    return addressService.getAddressWithStateAndKeywork(stateId, part);
  }


  @RequestMapping("/sections/cityId/{cityId}")
  public List<Address> getSections(@PathVariable(value = "cityId") String cityId) {
    return addressService.getSections(cityId);
  }


  @RequestMapping("/addresses/zipcode/{addressZipCode}")
  public Address getAddress(@PathVariable(value = "addressZipCode") String addressZipCode) {
    return addressService.getAddress(addressZipCode);
  }


  @RequestMapping("/addresses")
  public List<Address> getAddresses(@RequestParam(value = "part") String part) {
    return addressService.getAddresses(part);
  }
}