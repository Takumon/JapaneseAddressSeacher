package jp.takumon.japaneseaddresssearcher.web;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.takumon.japaneseaddresssearcher.domain.Address;
import jp.takumon.japaneseaddresssearcher.service.AddressService;

/**
 * 住所検索用の画面を返すコントローラー
 * 
 * @author takumon
 */
@Controller
public class AddressController {

  @Autowired
  AddressService addressService;


  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String form() {
    return "form";
  }


  @RequestMapping(value = "address/search", method = RequestMethod.POST)
  public ModelAndView searchAddress(
      @NotNull 
      @RequestParam String addressZipCode) {
    
    ModelAndView model = new ModelAndView();
    List<Address> addressList = addressService.getAddress(addressZipCode);
    model.addObject("addressList", addressList);
    model.setViewName("form");
    return model;
  }
}
