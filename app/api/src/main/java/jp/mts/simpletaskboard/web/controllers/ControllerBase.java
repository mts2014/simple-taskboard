package jp.mts.simpletaskboard.web.controllers;

import javax.servlet.http.HttpServletResponse;

import jp.mts.simpletaskboard.web.response.ApiError;
import jp.mts.simpletaskboard.web.response.RestResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class ControllerBase {

	@ExceptionHandler
	@ResponseBody
	public RestResponse handleExceptionFor(
			MethodArgumentNotValidException e,
			HttpServletResponse httpServletResponse){

		httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		RestResponse response = new RestResponse();

		BindingResult bindingResult = e.getBindingResult();
		for(FieldError fe : bindingResult.getFieldErrors()){
			response.addError(ApiError.ofMessage(fe.getDefaultMessage(), fe.getField()));
		}
		for(ObjectError ge : bindingResult.getGlobalErrors()){
			response.addError(ApiError.ofMessage(ge.getDefaultMessage()));
		}

		return response;
	}

}
