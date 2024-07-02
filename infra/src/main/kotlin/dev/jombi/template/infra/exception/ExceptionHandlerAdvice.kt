package dev.jombi.template.infra.exception

import dev.jombi.template.common.exception.CustomException
import dev.jombi.template.common.exception.GlobalExceptionDetail
import dev.jombi.template.common.exception.response.ResponseError
import dev.jombi.template.core.auth.exception.AuthExceptionDetails
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException


@RestControllerAdvice
class ExceptionHandlerAdvice {
    private val log = LoggerFactory.getLogger(ExceptionHandlerAdvice::class.java)
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception?): ResponseEntity<ResponseError> {
        log.error("Error while processing request: ", e)
        return ResponseError.of(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(CustomException::class)
    fun customException(e: CustomException): ResponseEntity<ResponseError> {
        return ResponseError.of(e.detail, *e.formats)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingParameter(e: MissingServletRequestParameterException): ResponseEntity<ResponseError> {
        return ResponseError.of(GlobalExceptionDetail.PARAMETER_NOT_MATCH, e.parameterName, e.parameterType)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun noResourceFound(e: NoResourceFoundException): ResponseEntity<ResponseError> {
        return ResponseError.of(GlobalExceptionDetail.RESOURCE_NOT_FOUND, e.resourcePath)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException) =
        ResponseError.of(GlobalExceptionDetail.METHOD_NOT_SUPPORTED, e.method, e.supportedMethods?.joinToString("', '") ?: "N/A")

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException) =
        ResponseError.of(GlobalExceptionDetail.UNPROCESSABLE_BODY)

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun httpMediaTypeNotSupportedException(e: HttpMediaTypeNotSupportedException) =
        ResponseError.of(GlobalExceptionDetail.UNSUPPORTED_MEDIA_TYPE, e.contentType, e.supportedMediaTypes.takeIf { it.isNotEmpty() }?.joinToString("', '") ?: "N/A")

    @ExceptionHandler
    fun badCredentialsException(e: BadCredentialsException) =
        ResponseError.of(AuthExceptionDetails.BAD_CREDENTIALS)
}
