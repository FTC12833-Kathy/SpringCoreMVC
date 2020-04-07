package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/list", "/", ""})  // This is the path entered in browser (localhost:8080/products)
    public String listProducts(Model model){

        // object (productService.listAllProducts()) is associated with its name (products) to be used in html (listproducts.html)
        model.addAttribute("products", productService.listAll());
        
        return "product/listproducts"; // This must equal the html file name (listproducts.html)
    }

    @RequestMapping({"/{id}", "/show/{id}"})
    // @PathVariable binds the web data to our variable
    public String getProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getByID(id));

        return  "product/showproduct";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getByID(id));

        return "product/maintainproduct";
    }

    @RequestMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());

        return "product/maintainproduct";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product){
        Product savedProduct = (Product) productService.createOrUpdate(product);

        return "redirect:/product/" + savedProduct.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/product/list";
    }
}
