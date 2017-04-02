package jp.takumon.japaneseaddresssearcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 入力チェックの設定
 * 
 * @author takumon
 */
@Configuration
public class ValidateConfig  extends WebMvcConfigurerAdapter{

  @Autowired
  private MessageSource messageSource;

  /**
   * バリデーションメッセージをValidationMessage.propertiesからmessage.propertiesに上書きする
   */
  @Bean
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
    localValidatorFactoryBean.setValidationMessageSource(messageSource);
    return localValidatorFactoryBean;
  }
  
  @Override
  public Validator getValidator() {
    return validator();
  }
}
