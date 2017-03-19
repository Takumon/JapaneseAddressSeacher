package jp.takumon.restsample.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import jp.takumon.restsample.AppConfig;

/**
 * 例外ハンドラ
 * 
 * @author takumon
 *
 */
public class AppExceptionResolver extends AbstractHandlerExceptionResolver {

  private static final String HANDL_TARGET_REQUEST = "/api/";


  @Override
  protected boolean shouldApplyTo(HttpServletRequest request, Object handler) {
    // apiに対するリクエストをハンドリング対象とする
    return super.shouldApplyTo(request, handler) && request.getRequestURI().contains(HANDL_TARGET_REQUEST);
  }


  @Override
  protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    MappingJackson2JsonView view = new MappingJackson2JsonView();
    view.setJsonPrefix(AppConfig.JSONP_CALLBACK_FUNCTION_NAME);
    ModelAndView mav = new ModelAndView(view);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    mav.addObject(errorResponse);
    // 入力チェックエラーの場合
    if (ex instanceof ValidationException) {
      errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
      errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
      return mav;
    }
    // 業務例外の場合
    if (ex instanceof ProcessException) {
      errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
      errorResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
      return mav;
    }
    // いづれにも該当しない場合はシステムエラーとみなす
    errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    return mav;
  }
}
