package jp.takumon.japaneseaddresssearcher.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import jp.takumon.japaneseaddresssearcher.App;

/**
 * サーブレットの初期化設定
 * 
 * @author takumon
 */
@Configuration
public class WebInitializer extends SpringBootServletInitializer {

  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(App.class, RestConfig.class, ValidateConfig.class);
  }
}
