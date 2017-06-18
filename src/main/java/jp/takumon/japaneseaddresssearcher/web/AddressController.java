package jp.takumon.japaneseaddresssearcher.web;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
import jp.takumon.japaneseaddresssearcher.domain.StateWithRegion;
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


  // 入力値の空白はトリムする
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "form";
  }

  @RequestMapping(value = "/address-search", method = RequestMethod.GET)
  public String adressSearch() {
    return "form";
  }

  @RequestMapping(value = "address/search", method = RequestMethod.POST)
  public ModelAndView searchAddress(
      @NotNull(message="郵便番号を入力してください。")
      @Pattern(regexp="^\\d{3}-\\d{4}", message="郵便番号が999-9999の形式ではありません。")
      @RequestParam(name="addressZipCode") String addressZipCode) {

    ModelAndView model = new ModelAndView();
    List<Address> addressList = addressService.getAddress(addressZipCode);
    model.addObject("addressList", addressList);
    model.setViewName("form");
    return model;
  }

	@RequestMapping(value = "/zip-search", method = RequestMethod.GET)
	public ModelAndView zipSearch() {
		ModelAndView model = new ModelAndView();
		List<StateWithRegion> stateList = addressService.getStatesWithRegion();
		model.addObject("stateList", stateList);
		model.setViewName("zip-search");
		return model;
	}

}
