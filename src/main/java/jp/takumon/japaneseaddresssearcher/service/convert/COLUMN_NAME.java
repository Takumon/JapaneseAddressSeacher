package jp.takumon.japaneseaddresssearcher.service.convert;

/** 
 * ソートパラメタで指定できるカラム名 
 */
public enum COLUMN_NAME {
  address_zip_code("addressZipCode"), 
  section_id("sectionId"), 
  section_name("sectionName"), 
  section_kana("sectionKana"),
  city_id("cityId"), 
  city_name("cityName"), 
  city_kana("cityKana"), 
  state_id("stateId"), 
  state_name("stateName"), 
  state_kana("stateKana");
  
  private String columnNameInRequestParam;
  
  private COLUMN_NAME(String columnNameInRequestParam) {
    this.columnNameInRequestParam = columnNameInRequestParam;
  }
  
  public String getcolumnNameInRequestParam() {
    return this.columnNameInRequestParam;
  }
  
  
  /**
   * 指定された値に一致するカラム名が存在するか判定する.
   * 
   * @param requestParam
   * @return 
   */
  public static boolean contains(String value) {
    for (COLUMN_NAME name : values()) {
      if (name.getcolumnNameInRequestParam().equals(value)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * 指定された値に一致するカラムを取得する.
   * 
   * @param requestParam
   * @return 
   * @throws IllegalArgumentException 一致するカラムが存在しない場合
   */
  public static COLUMN_NAME get(String value) {
    for (COLUMN_NAME name : values()) {
      if (name.getcolumnNameInRequestParam().equals(value)) {
        return name;
      }
    }
    
    throw new IllegalArgumentException(String.format("指定した値[%s]に該当するカラム名はありません。", value));
  }
}