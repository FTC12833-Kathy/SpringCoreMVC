package guru.springframework.springmvc.controllers;

import guru.springframework.springmvc.domain.Product;
import guru.springframework.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")  // This is the path entered in browser (localhost:8080/products)
    public String listProducts(Model model){

        // object (productService.listAllProducts()) is associated with its name (products) to be used in html (products.html)
        model.addAttribute("products", productService.listAllProducts());
        
        return "products"; // This must equal the html file name (products.html)
    }

    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));

        return  "product";
    }

    @RequestMapping("/product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));

        return "productform";
    }


    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());

        return "productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product){
        Product savedProduct = productService.saveOrUpdateProduct(product);

        return "redirect:/product/" + savedProduct.getId();
    }
}
