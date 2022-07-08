package br.com.blz.tstjava.service

import br.com.blz.tstjava.model.Product

interface ProductService {
  fun createProduct(product: Product): Product?
  fun updateProduct(product: Product): Product?
  fun findById(sku: Int): Product?
  fun deleteProductBySku(sku: Int): Boolean
}
