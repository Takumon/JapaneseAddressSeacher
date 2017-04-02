package jp.takumon.japaneseaddresssearcher;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Rest周りの設定
 * 
 * @author takumon
 *
 */
@Configuration
public class RestConfig {

  /** レスポンスのJSONPのコールバック関数名 */
  public static final String JSONP_CALLBACK_FUNCTION_NAME = "callback";

  @ControllerAdvice
  static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdvice() {
      super(JSONP_CALLBACK_FUNCTION_NAME);
    }
  }
}
