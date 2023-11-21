//import jakarta.validation.ConstraintViolationException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.InvalidDataAccessResourceUsageException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import com.bycnit.workedhours.service.exception.BadRequestException;
//import com.bycnit.workedhours.service.exception.BruteForceException;
//import com.bycnit.workedhours.service.exception.ElementNotFoundException;
//import com.bycnit.workedhours.service.exception.EmailInvalidException;
//import com.bycnit.workedhours.service.exception.EmailNotAvailableException;
//import com.bycnit.workedhours.service.exception.EntityInactiveException;
//import com.bycnit.workedhours.service.exception.ExceptionResponse;
//import com.bycnit.workedhours.service.exception.ExpiredAcessTokenException;
//import com.bycnit.workedhours.service.exception.InvalidAccessTokenException;
//import com.bycnit.workedhours.service.exception.NonExistentTokenException;
//import com.bycnit.workedhours.service.exception.NonSSOException;
//import com.bycnit.workedhours.service.exception.SiteCodeDuplicatedException;
//import com.bycnit.workedhours.service.exception.UserBlockException;
//import com.bycnit.workedhours.service.exception.UserInactiveException;
//import com.bycnit.workedhours.service.exception.UserNotFoundException;
//import com.bycnit.workedhours.service.exception.UsernameInvalidException;
//import com.bycnit.workedhours.service.exception.UsernameNotAvailableException;
//import com.bycnit.workedhours.service.exception.WrongPasswordException;
//import com.bycnit.workedhours.service.exception.WrongUsernamePasswordException;
//import com.bycnit.workedhours.service.exception.mergesite.MergeSiteBadRequestException;
//import com.bycnit.workedhours.service.exception.mergesite.MergeSiteBusinessException;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * Controller advice to translate the server side exceptions to client-friendly
// * json structures. The error response follows RFC7807 - Problem Details for
// * HTTP APIs (https://tools.ietf.org/html/rfc7807)
// */
//@RestControllerAdvice
//@Slf4j
//public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//    private static final String BAD_REQUEST_MSG = "Bad request";
//    private static final String EXCEPTION_MSG = "Exception";
//    private static final String AUTHENTICATION_LOGIN_MESSAGES_UNAUTHORIZED = "AUTHENTICATION.LOGIN.MESSAGES.UNAUTHORIZED";
//
//    /**
//     * Not found exception handling
//     */
//
//    @ExceptionHandler({ ElementNotFoundException.class })
//    public final ResponseEntity<ExceptionResponse> handleNonExistingElement(ElementNotFoundException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Not found", HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
//    }
//
//    /**
//     * Bad request exception handling
//     */
//    @ExceptionHandler({ BadRequestException.class })
//    public final ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * Access denied exception handling
//     */
//    @ExceptionHandler({ AccessDeniedException.class,
//            com.bycnit.workedhours.service.exception.AccessDeniedException.class })
//    public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(RuntimeException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("COMMON.ERROR.PERMISION.PERMISSION_DENIED",
//                HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler({ AuthenticationException.class,
//            com.bycnit.workedhours.service.exception.AuthenticationException.class })
//    public final ResponseEntity<ExceptionResponse> handleAuthenticationException(RuntimeException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(AUTHENTICATION_LOGIN_MESSAGES_UNAUTHORIZED,
//                HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
//    }
//
//    /**
//     * Brute Force Exception handling
//     */
//    @ExceptionHandler({ BruteForceException.class })
//    public final ResponseEntity<ExceptionResponse> handleBruteForceException(BruteForceException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("AUTHENTICATION.LOGIN.MESSAGES.BLOCKED",
//                HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
//    }
//
//    /**
//     * A custom general exception
//     */
//    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
//    public ResponseEntity<Object> handleDBExceptions(InvalidDataAccessResourceUsageException ex, WebRequest request) {
//        log.error(EXCEPTION_MSG, ex);
//        ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
//            WebRequest request) {
//        log.error(EXCEPTION_MSG, ex);
//        ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
//            WebRequest request) {
//        log.error(EXCEPTION_MSG, ex);
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Validation error", HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
//            WebRequest request) {
//        log.error(EXCEPTION_MSG, ex);
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Validation error", HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
//        log.error(EXCEPTION_MSG, ex);
//        ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    /**
//     * Custom not found exception of Spring
//     */
//    @Override
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Not found", HttpStatus.NOT_FOUND);
//        return handleExceptionInternal(ex, exceptionResponse, headers, status, request);
//    }
//
//    /**
//     * Custom HttpMessageNotReadableException
//     */
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, headers, status, request);
//
//    }
//
//    /**
//     * Custom Argument Not Valid request
//     */
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Arguments not valid", HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, headers, status, request);
//    }
//
//    /*
//     * Custom exceptions
//     */
//
//    @ExceptionHandler({ NonExistentTokenException.class, InvalidAccessTokenException.class })
//    public ResponseEntity<ExceptionResponse> handleInvalidAccessTokenException(RuntimeException ex,
//            WebRequest request) {
//        return handleAuthenticationException(ex, request);
//    }
//
//    @ExceptionHandler(ExpiredAcessTokenException.class)
//    public ResponseEntity<Object> handleExpiredAcessTokenException(ExpiredAcessTokenException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("EXPIRED_ACCESS_TOKEN", HttpStatus.FORBIDDEN);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
//    }
//
//    @ExceptionHandler({ UsernameNotFoundException.class, UserNotFoundException.class })
//    public ResponseEntity<Object> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                "AUTHENTICATION.LOGIN.MESSAGES.WRONG_USERNAME_PASSWORD", HttpStatus.FORBIDDEN);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
//    }
//
//    @ExceptionHandler(UserInactiveException.class)
//    public ResponseEntity<Object> handleUserInactiveException(UserInactiveException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("AUTHENTICATION.LOGIN.MESSAGES.INACTIVE",
//                HttpStatus.FORBIDDEN);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
//    }
//
//    @ExceptionHandler(WrongUsernamePasswordException.class)
//    public ResponseEntity<Object> handleWrongUsernamePasswordException(WrongUsernamePasswordException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                "AUTHENTICATION.LOGIN.MESSAGES.WRONG_USERNAME_PASSWORD", HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(WrongPasswordException.class)
//    public ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("AUTHENTICATION.LOGIN.MESSAGES.WRONG_PASSWORD",
//                HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(UserBlockException.class)
//    public ResponseEntity<Object> handleUserBlockException(UserBlockException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("AUTHENTICATION.LOGIN.MESSAGES.BLOCKED",
//                HttpStatus.FORBIDDEN);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
//    }
//
//    @ExceptionHandler(UsernameNotAvailableException.class)
//    public ResponseEntity<Object> handleUsernameNotAvailableException(UsernameNotAvailableException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("USER.MESSAGES.USERNAME_NOT_AVAILABLE",
//                HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(EmailNotAvailableException.class)
//    public ResponseEntity<Object> handleEmailNotAvailableException(EmailNotAvailableException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("USER.MESSAGES.EMAIL_NOT_AVAILABLE",
//                HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(UsernameInvalidException.class)
//    public ResponseEntity<Object> handleUsernameInvalidException(UsernameInvalidException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("USER.MESSAGES.USERNAME_INVALID",
//                HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(EmailInvalidException.class)
//    public ResponseEntity<Object> handleEmailInvalidException(EmailInvalidException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("USER.MESSAGES.EMAIL_INVALID",
//                HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(NonSSOException.class)
//    public ResponseEntity<Object> handleNonSSOException(NonSSOException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse("NON_SSO", HttpStatus.UNAUTHORIZED);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
//    }
//
//    @ExceptionHandler(MergeSiteBusinessException.class)
//    public ResponseEntity<Object> handleMergeSiteBusinessException(MergeSiteBusinessException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.OK);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.OK, request);
//    }
//
//    @ExceptionHandler(MergeSiteBadRequestException.class)
//    public ResponseEntity<Object> handleMergeSiteBadRequestException(MergeSiteBadRequestException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.OK, request);
//    }
//
//    @ExceptionHandler(EntityInactiveException.class)
//    public ResponseEntity<Object> handleEntityInactiveException(EntityInactiveException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(SiteCodeDuplicatedException.class)
//    public ResponseEntity<Object> handleSiteCodeDuplicatedException(SiteCodeDuplicatedException ex,
//            WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//}
//package com.bycnit.mytodo.exceptions;
//
//public class RestResponseEntityExceptionHandler {
//
//}
