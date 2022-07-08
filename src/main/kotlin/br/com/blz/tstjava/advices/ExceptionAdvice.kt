package br.com.blz.tstjava.advices

import br.com.blz.tstjava.exception.ProductsExceptions
import br.com.blz.tstjava.model.response.ResponseError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ExceptionAdvice
@Autowired constructor(private val messageSource: MessageSource) {

  @ExceptionHandler(ProductsExceptions::class)
  fun handleAlredyExistsException(e: ProductsExceptions): ResponseEntity<ResponseError> {
    val errorMessage = ResponseError(messageSource.getMessage(e.errorCodeMessage, null, Locale.getDefault()))
    return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
  }
}
