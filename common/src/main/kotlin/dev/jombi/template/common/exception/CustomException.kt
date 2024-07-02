package dev.jombi.template.common.exception

class CustomException(val detail: ExceptionDetail, vararg val formats: Any?) : RuntimeException()
