package jp.takumon.restsample;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * アプリケーションの設定
 * 
 * @author takumon
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {

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
}
