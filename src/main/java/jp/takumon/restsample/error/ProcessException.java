package jp.takumon.restsample.error;

/**
 * 業務例外
 * 
 * @author takumon
 */
public class ProcessException extends RuntimeException {

  private static final long serialVersionUID = 1L;


  public ProcessException(String message) {
    super(message);
  }
}
