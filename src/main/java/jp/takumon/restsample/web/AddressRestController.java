package jp.takumon.restsample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


  @RequestMapping(value = "/states", method = RequestMethod.GET)
  public List<State> getStates() {
    return addressService.getStates();
  }


  @RequestMapping(value = "/cities/stateId/{stateId}", method = RequestMethod.GET)
  public List<City> getCities(@PathVariable(value = "stateId") String stateId) {
    return addressService.getCities(stateId);
  }


  @RequestMapping(value = "/addresses/stateId/{stateId}", method = RequestMethod.GET)
  public List<Address> getAddressWithStateAndKeywork(@PathVariable(value = "stateId") String stateId, @RequestParam(value = "part") String part) {
    return addressService.getAddressWithStateAndKeywork(stateId, part);
  }


  @RequestMapping(value = "/sections/cityId/{cityId}", method = RequestMethod.GET)
  public List<Address> getSections(@PathVariable(value = "cityId") String cityId) {
    return addressService.getSections(cityId);
  }


  @RequestMapping(value = "/addresses/zipcode/{addressZipCode}", method = RequestMethod.GET)
  public List<Address> getAddress(@PathVariable(value = "addressZipCode") String addressZipCode) {
    return addressService.getAddress(addressZipCode);
  }


  @RequestMapping(value = "/addresses", method = RequestMethod.GET)
  public List<Address> getAddresses(@RequestParam(value = "part") String part) {
    return addressService.getAddresses(part);
  }
}