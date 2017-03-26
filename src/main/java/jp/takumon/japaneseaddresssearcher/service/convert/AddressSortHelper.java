package jp.takumon.japaneseaddresssearcher.service.convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * アドレスのソート順のオブジェクトに対し操作を行うクラス.
 * 
 * @author takumon
 */
public class AddressSortHelper {
  
  /** ソートパラメタにおいて複数カラムを指定する場合の区切り文字 */
  private static final String COLUMN_DELIMITER = ",";
  
  /** SQLのORDERBY句 */
  private static final String ORDER_BY = "ORDER BY";
  
  /** エラーメッセージ */
  private static final String ERROR_MESSAGE =
      "ソート順には[%s]のいずれかをカンマ区切りで指定してください。降順にする場合は項目の先頭に\"-\"をつけてください。";

  
  
  private AddressSortHelper() {}

  /**
   * 指定された値がソート指定のフォーマットに従っているかを判定する.
   * 
   * @param value
   * @return
   */
  public static boolean isValid(String value) {
    if (StringUtils.contains(value, COLUMN_DELIMITER)) {
      return Stream.of(StringUtils.split(value, COLUMN_DELIMITER))
          .allMatch(AddressSortHelper::isColumnValid);
    }

    return isColumnValid(value);
  }


  /**
   * 指定された値が１項目に対するソート指定のフォーマットに従っているかを判定する.
   * <dl>
   * <dt>フォーマット</dt>
   * <dd>接頭辞に{@link PREFIX_DESC}か{@link PREFIX_ADC}または接頭辞なし + {@link COLUMN_NAME}のいずれか</dd>
   * </dl>
   * 
   * @param value
   * @return 指定された値が１項目に対するソート指定のフォーマットに従っている場合は{@code true}. それ以外の場合は{@code false}.
   */
  private static boolean isColumnValid(String value) {
    if (StringUtils.isEmpty(value)) {
      return false;
    }

    // オーダーの指定がある場合は取り除く
    String onlyClumnName = ORDER.contains(value.substring(0, 1)) ? value.substring(1) : value;

    if (StringUtils.isBlank(onlyClumnName)) {
      return false;
    }

    return COLUMN_NAME.contains(onlyClumnName);
  }
  
  /**
   * リクエストパラメータのソート指定を文字列から{@link AddressSort}のリストに変換する.
   * 
   * @param source
   * @return {@link AddressSort}オブジェクトのリスト
   */
  public static List<AddressSort> convert(String value) {
    if (StringUtils.contains(value, COLUMN_DELIMITER)) {
      return Stream.of(StringUtils.split(value, COLUMN_DELIMITER))
          .map(AddressSortHelper::convertColumn)
          .collect(Collectors.toList());
    }

    return Arrays.asList(convertColumn(value));
  }

  private static AddressSort convertColumn(String value) {
    if(ORDER.DESC.getOrderInRequestParam().equals(value.substring(0, 1))) {
      return new AddressSort(ORDER.DESC, COLUMN_NAME.get(value.substring(1)));
    }
    
    // デフォルトのオーダーは昇順
    return new AddressSort(ORDER.ASC, COLUMN_NAME.get(value));
  }
  
  /**
   * SQL上のorderby句に変換する.
   * 
   * @param sorts
   * @return
   */
  public static String convertOrderByInSql(List<AddressSort> sort) {
    if(sort == null) {
      return "";
    }
    
    if(sort.isEmpty()) {
      return "";
    }
    
    return ORDER_BY + " " + sort.stream().map(addressSort -> addressSort.getColumnName() + " " + addressSort.getOrder())
        .collect(Collectors.joining(", "));
  }


  public static String errorMassage() {
    return String.format(ERROR_MESSAGE, Stream.of(COLUMN_NAME.values()).map(COLUMN_NAME::toString)
        .collect(Collectors.joining(",")));
  }



}
