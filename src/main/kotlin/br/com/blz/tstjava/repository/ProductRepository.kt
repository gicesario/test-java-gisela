package br.com.blz.tstjava.repository

import br.com.blz.tstjava.model.Inventory
import br.com.blz.tstjava.model.Product
import br.com.blz.tstjava.model.Warehouse
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class ProductRepository {
  private var productsList: MutableSet<Product> = mutableSetOf()

  @PostConstruct
  fun init() {
    create(
      Product(
        1,
        "teste",
        Inventory(1, warehouses = listOf(Warehouse("cwb", 12, "ECOMMERCE"), Warehouse("mcz", 3, "ECOMMERCE"))),
        true
      )
    )
  }

  fun findById(sku: Int): Product? = productsList.singleOrNull { p -> p.sku == sku }

  fun create(product: Product): Boolean {
    return productsList.add(product)
  }

  fun update(product: Product): Product {
    productsList.first { it.sku == product.sku }.also {
      it.name = product.name
      it.inventory = product.inventory
    }
    return product
  }

  fun delete(sku: Int): Boolean =
    productsList.removeIf { p -> p.sku == sku }
}
