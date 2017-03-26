package jp.takumon.japaneseaddresssearcher;

import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;


/**
 * アプリケーションの設定
 * 
 * @author takumon
 */
@SpringBootApplication
public class App {

  /** レスポンスのJSONPのコールバック関数名 */
  public static final String JSONP_CALLBACK_FUNCTION_NAME = "callback";

  /**
   * REST周り
   */
  @ControllerAdvice
  static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdvice() {
      super(JSONP_CALLBACK_FUNCTION_NAME);
    }
  }
  
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
