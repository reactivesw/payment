package io.reactivesw.payment.infrastructure.exception;

import io.reactivesw.exception.handler.ExceptionHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umasuo on 17/3/2.
 */
@Component
public class PaymentExceptionHandler extends ExceptionHandler implements HandlerExceptionResolver {

  /**
   * resolve exception.
   *
   * @param request  the HttpServletRequest
   * @param response the HttpServletResponse
   * @param handler  the Object
   * @param ex       the Exception
   * @return ModelAndView
   */
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                       Object handler, Exception ex) {
    setResponse(request, response, handler, ex);
    return new ModelAndView();
  }
}