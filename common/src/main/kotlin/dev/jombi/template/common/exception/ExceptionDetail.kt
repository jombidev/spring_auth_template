package dev.jombi.template.common.exception

import org.springframework.http.HttpStatus

interface ExceptionDetail {
    val message: String
    val code: HttpStatus
    val enumName: String
}
