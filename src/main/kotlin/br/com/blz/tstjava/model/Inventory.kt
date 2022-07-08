package br.com.blz.tstjava.model

data class Inventory(
  var quantity: Int? = 0,
  var warehouses: List<Warehouse>? = null
)
