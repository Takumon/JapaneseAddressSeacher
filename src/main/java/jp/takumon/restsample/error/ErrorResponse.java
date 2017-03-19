package jp.takumon.restsample.error;

/**
 * レスポンス用のエラーBean
 * 
 * @author takumon
 */
public class ErrorResponse {

  private String status;

  private int statusCode;

  private String message;


  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public int getStatusCode() {
    return statusCode;
  }


  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }


  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }
}
