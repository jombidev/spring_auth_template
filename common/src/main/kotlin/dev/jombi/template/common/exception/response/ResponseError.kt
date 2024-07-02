package dev.jombi.template.common.exception.response

import dev.jombi.template.common.exception.ExceptionDetail
import dev.jombi.template.common.response.ResponseEmpty
import org.springframework.http.ResponseEntity

class ResponseError(message: String, code: Int, val detail: String) : ResponseEmpty(message, code) {
    companion object {
        fun of(message: ExceptionDetail, vararg args: Any?) =
            ResponseEntity
                .status(message.code)
                .body(ResponseError(message.enumName, message.code.value(), message.message.format(*args)))
    }
}
