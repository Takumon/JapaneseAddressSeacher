package jp.takumon.japaneseaddresssearcher.error;

/**
 * 入力チェックエラー
 * 
 * @author takumon
 */
public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;


  public ValidationException(String message) {
    super(message);
  }
}
