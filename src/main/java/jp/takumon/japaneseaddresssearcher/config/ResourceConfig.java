package jp.takumon.japaneseaddresssearcher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;

/**
 * リソースの設定
 * 
 * @author takumon
 *
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter{
	
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // webjars の定義
	  registry.addResourceHandler("/webjars/**")
              .addResourceLocations("classpath:/META-INF/resources/webjars/")
              .resourceChain(false)
              .addResolver(new GzipResourceResolver());
  }
}
