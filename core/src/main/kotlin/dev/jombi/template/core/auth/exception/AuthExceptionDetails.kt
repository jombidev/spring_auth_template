package dev.jombi.template.core.auth.exception

import dev.jombi.template.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class AuthExceptionDetails(override val message: String, override val status: HttpStatus) : ExceptionDetail {
    BAD_CREDENTIALS("아이디 또는 비밀번호가 잘못 되었음", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS("'%s' 사용자가 이미 존재함", HttpStatus.BAD_REQUEST),

    INVALID_TOKEN("토큰이 잘못 되었음", HttpStatus.BAD_REQUEST),
    TOKEN_TYPE_MISMATCH("토큰 타입이 잘못 되었음", HttpStatus.BAD_REQUEST),
    EXPIRED_TOKEN("토큰이 만료 되었음", HttpStatus.UNAUTHORIZED),
    ;
    override val code = name
}
