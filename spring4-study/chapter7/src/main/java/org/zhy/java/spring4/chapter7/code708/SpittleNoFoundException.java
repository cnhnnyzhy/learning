package org.zhy.java.spring4.chapter7.code708;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Spittle Not Found")
public class SpittleNoFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1981222347487986673L;

}
