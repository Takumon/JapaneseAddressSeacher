package jp.takumon.japaneseaddresssearcher.service.convert;

/**
 * 検索時のソート順を表すクラス.
 * 
 * @author takumon
 */
public class AddressSort {
    
  private ORDER order;
  
  public COLUMN_NAME columnName;
  
  public AddressSort(ORDER order, COLUMN_NAME columnName) {
    this.order = order;
    this.columnName = columnName;
  }


  public ORDER getOrder() {
    return order;
  }


  public COLUMN_NAME getColumnName() {
    return columnName;
  }
}
