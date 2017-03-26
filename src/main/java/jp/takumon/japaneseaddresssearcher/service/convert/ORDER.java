package jp.takumon.japaneseaddresssearcher.service.convert;

/** 
 * ソート時のオーダー 
 */
public enum ORDER {
  ASC(""),
  DESC("-");
  
  private String orderInRequestParam;
  
  private ORDER(String orderInRequestParam) {
    this.orderInRequestParam = orderInRequestParam;
  }
  
  public String getOrderInRequestParam() {
    return this.orderInRequestParam;
  }
  
  /**
   * 指定された値に一致するソート順のオーダーが存在するか判定する.
   * 
   * @param requestParam
   * @return 
   */
  public static boolean contains(String value) {
    for (ORDER order : values()) {
      if (order.getOrderInRequestParam().equals(value)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * 指定された値に一致するソート順のオーダーを取得する.
   * 
   * @param requestParam
   * @return 
   * @throw 
   */
  public static ORDER get(String value) {
    for (ORDER order : values()) {
      if (order.getOrderInRequestParam().equals(value)) {
        return order;
      }
    }
    
    throw new IllegalArgumentException(String.format("指定した値[%s]に一致するオーダー順が存在しません。", value));
  }
}
