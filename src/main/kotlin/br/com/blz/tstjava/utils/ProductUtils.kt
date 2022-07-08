package br.com.blz.tstjava.utils

import br.com.blz.tstjava.model.Product

fun Product.tallyInventory(): Int {
  var number = 0
  this.inventory?.warehouses?.forEach {
    number += it.quantity
  }
  this.isMarketable = number > 0
  return number
}
