package br.com.blz.tstjava.exception

enum class ErrorKeyCode(var errorCode: String) {
  ALREADY_EXISTS("alreadyExistsProduct.message"),
  NOT_FOUND("notFound.message"),
  NOT_REMOVED("notRemoved.message")
}
