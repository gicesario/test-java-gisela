package br.com.blz.tstjava.exception

class ProductsExceptions(var error: ErrorKeyCode) : RuntimeException() {
  val errorCodeMessage: String
    get() = error.errorCode
}
