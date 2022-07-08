package br.com.blz.tstjava.service.impl

import br.com.blz.tstjava.model.Product
import br.com.blz.tstjava.repository.ProductRepository
import br.com.blz.tstjava.service.ProductService
import br.com.blz.tstjava.utils.tallyInventory
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
  private val productRepository: ProductRepository
) : ProductService {

  override fun createProduct(product: Product): Product? {
    product.inventory?.quantity = product.tallyInventory()
    if (productRepository.create(product)) {
      return product
    }
    return null
  }

  override fun updateProduct(product: Product): Product? {
    return productRepository.update(product).let {
      it.inventory?.quantity = product.tallyInventory()
      it
    }
  }

  override fun findById(sku: Int): Product? {
    return productRepository.findById(sku).let {
      if (it != null) {
        it.inventory?.quantity = it.tallyInventory()
      }
      it
    }
  }

  override fun deleteProductBySku(sku: Int): Boolean {
    return productRepository.delete(sku)
  }
}
