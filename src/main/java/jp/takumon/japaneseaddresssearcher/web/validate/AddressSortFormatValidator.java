package jp.takumon.japaneseaddresssearcher.web.validate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * 検索時のソートパラメタの形式チェッククラス.
 * 
 * @author takumon
 */
public class AddressSortFormatValidator implements ConstraintValidator<AddressSortFormat, String>{

  /** ソートパラメタで指定できるカラム名 */
  private static final Set<String> COLUMN_NAMES = new HashSet<>(Arrays.asList(
      "addressZipCode",
      "sectionId",
      "sectionName",
      "sectionKana",
      "cityId",
      "cityName",
      "cityKana",
      "stateId",
      "stateName",
      "stateKana"));
  
  /** ソートパラメタにおいて降順を表すプレフィックス */
  private static final String PREFIX_DESC = "-";

  /** ソートパラメタにおいて昇順を表すプレフィックス */
  private static final String PREFIX_ASC = "+";

  /** ソートパラメタにおいて複数カラムを指定する場合の区切り文字 */
  private static final String COLUMN_DELIMITER = ",";
  
  @Override
  public void initialize(AddressSortFormat constraintAnnotation) {
    
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(StringUtils.contains(value, COLUMN_DELIMITER) == false) {
      return isSortFormat(value);
    }
    
    return Stream.of(StringUtils.split(value, COLUMN_DELIMITER)).allMatch(AddressSortFormatValidator::isSortFormat);
  }
  
  
  /**
   * 指定された値が１項目に対するソート指定のフォーマットに従っているかを判定する.
   * <dl>
   * <dt>フォーマット</dt>
   * <dd>接頭辞に{@link PREFIX_DESC}か{@link PREFIX_ADC}または接頭辞なし + {@link COLUMN_NAMES}のいずれか</dd>
   * </dl>
   * 
   * @param value
   * @return 指定された値が１項目に対するソート指定のフォーマットに従っている場合は{@code true}. それ以外の場合は{@code false}.
   */
  private static boolean isSortFormat(String value) {
    if(StringUtils.isEmpty(value)) {
      return false;
    }
    
    String onlyClumnName = null;
    if(value.startsWith(PREFIX_ASC) || value.startsWith(PREFIX_DESC)) {
      onlyClumnName = value.substring(1);
    } else {
      onlyClumnName = value;
    }
    
    if(StringUtils.isBlank(onlyClumnName)) {
      return false;
    }
    
    return COLUMN_NAMES.contains(onlyClumnName);
  }
  

}
