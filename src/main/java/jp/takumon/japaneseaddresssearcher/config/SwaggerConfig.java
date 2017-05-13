package jp.takumon.japaneseaddresssearcher.config;


import static com.google.common.base.Predicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swaggerの設定
 *
 * @author takumon
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /** レスポンスのJSONPのコールバック関数名 */
  public static final String JSONP_CALLBACK_FUNCTION_NAME = "callback";

  @Bean
  public Docket document() {
    return new Docket(DocumentationType.SWAGGER_2).select().paths(paths()).build()
        .apiInfo(apiInfo());
  }

  @SuppressWarnings("unchecked")
  private Predicate<String> paths() {
    return or(containsPattern("/api/*"));
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("AddressSearch API", "住所検索用API", "0.1.0", "termsOfServiceUrl",
        new Contact("Takumon", "https://github.com/Takumon", "inouetakumon@gmail.com"), "", "");
  }

}
