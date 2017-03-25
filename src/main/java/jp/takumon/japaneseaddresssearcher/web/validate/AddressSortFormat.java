package jp.takumon.japaneseaddresssearcher.web.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 検索時のソートパラメータの形式チェック用アノテーション.
 * 
 * @author takumon
 */
@Documented
@Constraint(validatedBy=AddressSortFormatValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddressSortFormat {
  
  String message() default "ソート順の指定はカラム名をカンマ区切りで指定してください。";
  
  Class<?>[] groups() default { };
  
  Class<? extends Payload>[] PayLoad() default{};

}
