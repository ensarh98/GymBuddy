package com.gymbuddy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gymbuddy.common.AppException;

/**
 * Base controller for handling exceptions and returning standardized error
 * responses.
 * 
 * @author Ensar Horozović
 */
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
    private static final String INTERNAL_ERR_MSG = "Internal error.";

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<ErrorDetails> handleException(Exception ex) {
        if (ex instanceof AppException) {
            var e = (AppException) ex;

            if (AppException.INTERNAL_ERROR.equals(e.getCode())) {
                // log real cause and return generic error message
                LOG.error(e.getMessage());

                var details = new ErrorDetails(e.getCode(), INTERNAL_ERR_MSG);
                return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            var details = new ErrorDetails(e.getCode(), e.getMessage());
            return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
        }

        // unhandled exception, log stacktrace for further analysis
        LOG.error(AppException.INTERNAL_ERROR, ex);

        var details = new ErrorDetails(AppException.INTERNAL_ERROR, INTERNAL_ERR_MSG);
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ java.nio.file.AccessDeniedException.class })
    public final ResponseEntity<ErrorDetails> handleException(java.nio.file.AccessDeniedException ex) {

        var details = new ErrorDetails(AppException.UNAUTHORIZED_ERROR, ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.FORBIDDEN);
    }

    public static class ErrorDetails {

        private String code;
        private String message;

        public ErrorDetails(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }
}
