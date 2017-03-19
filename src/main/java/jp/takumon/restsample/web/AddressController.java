package jp.takumon.restsample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 住所検索用の画面を返すコントローラー
 * 
 * @author takumon
 */
@Controller
public class AddressController {
  
  @RequestMapping("/")
  public String welcome() {
    return "form";
  }
  
  @RequestMapping("/")
  public String searchAddress() {
    return "result";
  }
  
}
