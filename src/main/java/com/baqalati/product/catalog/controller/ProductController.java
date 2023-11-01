package com.baqalati.product.catalog.controller;

import com.baqalati.product.catalog.model.Category;
import com.baqalati.product.catalog.model.Product;
import com.baqalati.product.catalog.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/filter")
    public String filterProducts(@RequestParam(name = "category", required = false) String category, Model model) {
        List<Product> filteredProducts;
        if (category != null && !category.isEmpty()) {
            filteredProducts = productRepository.findByCategory(category);
        } else {
            filteredProducts = new ArrayList<>(); // Return an empty list
        }

        model.addAttribute("products", filteredProducts);
        return "product-list"; // Return the name of your HTML template
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name, @RequestParam String description, @RequestParam String category, @RequestParam float price) {
        // Create a new product and add it to the database or your data store
        Product newProduct = new Product();
        Category newCategory = new Category();
        newCategory.setName(category);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCategory(newCategory);
        newProduct.setPrice(price);

        productRepository.save(newProduct);

        return "redirect:/products"; // Redirect to the product list after adding the product
    }
}
