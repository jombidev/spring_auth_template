package dev.jombi.template.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseData<T>(code: String, status: Int, val data: T) : ResponseEmpty(code, status) {
    companion object {
        fun <T> of(message: String, code: HttpStatus, data: T) =
            ResponseEntity.status(code).body(ResponseData(message, code.value(), data))
        fun <T> ok(message: String = "OK", data: T) = of(message, HttpStatus.OK, data)
        fun <T> created(message: String = "Created", data: T) = of(message, HttpStatus.CREATED, data)
    }
}
