package br.com.blz.tstjava.controller

import br.com.blz.tstjava.exception.ErrorKeyCode
import br.com.blz.tstjava.exception.ProductsExceptions
import br.com.blz.tstjava.model.Product
import br.com.blz.tstjava.service.ProductService
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.NumberFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.annotation.Nonnegative

@RestController
@RequestMapping("/products")
class ProductController(@Autowired private val productService: ProductService) {

  companion object {
    const val URI_PATH_FIND_PRODUCT: String = "/{sku}"
    const val URI_PATH_CREATE_PRODUCT: String = "/add"
    const val URI_PATH_UPDATE_PRODUCT: String = "/alter"
    const val URI_PATH_DELETE_PRODUCT_BY_SKU: String = "/delete/{sku}"
  }

  @GetMapping(value = [URI_PATH_FIND_PRODUCT], produces = ["application/json"])
  fun findById(@PathVariable sku: Int): ResponseEntity<Any> {
    val foundProduct = productService.findById(sku) ?: throw ProductsExceptions(ErrorKeyCode.NOT_FOUND)
    return ResponseEntity.ok(foundProduct)
  }

  @PostMapping(value = [URI_PATH_CREATE_PRODUCT], consumes = ["application/json"], produces = ["application/json"])
  fun create(@RequestBody product: Product): ResponseEntity<Any> {
    val createdProduct = productService.createProduct(product) ?: throw ProductsExceptions(ErrorKeyCode.ALREADY_EXISTS)
    val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(createdProduct).toUri()
    return ResponseEntity.created(uri).body(createdProduct)
  }

  @PutMapping(value = [URI_PATH_UPDATE_PRODUCT], consumes = ["application/json"], produces = ["application/json"])
  fun update(@RequestBody product: Product): ResponseEntity<Any> {
    val updatedProduct = productService.updateProduct(product) ?: throw ProductsExceptions(ErrorKeyCode.NOT_FOUND)
    return ResponseEntity.ok(updatedProduct)
  }

  @DeleteMapping(value = [URI_PATH_DELETE_PRODUCT_BY_SKU])
  fun delete(@NotNull @Nonnegative @NumberFormat @PathVariable("sku") sku: Int): ResponseEntity<Any> {
    if (productService.deleteProductBySku(sku)) {
      return ResponseEntity.ok().build()
    }
    throw ProductsExceptions(ErrorKeyCode.NOT_REMOVED)
  }
}
