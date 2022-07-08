package br.com.blz.tstjava.model

data class Product(
  val sku: Int,
  var name: String,
  var inventory: Inventory? = null,
  var isMarketable: Boolean? = false
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (javaClass != other?.javaClass) {
      return false
    }
    other as Product
    if (sku != other.sku) {
      return false
    }
    return true
  }

  override fun hashCode(): Int {
    return sku.hashCode()
  }
}
