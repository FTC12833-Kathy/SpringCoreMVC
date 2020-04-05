package guru.springframework.springmvc.controllers;

import guru.springframework.springmvc.domain.Customer;
import guru.springframework.springmvc.domain.Product;
import guru.springframework.springmvc.services.CustomerService;
import guru.springframework.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")  // This is the path entered in browser (localhost:8080/customers)
    public String listCustomers(Model model){

        // object (customerService.listAllCustomers()) is associated with its name (customers) to be used in html (customers.html)
        model.addAttribute("customers", customerService.listAllCustomers());
        
        return "customers"; // This must equal the html file name (customers.html)
    }

    @RequestMapping("/customer/{id}")
    // @PathVariable binds the web data to our variable
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getCustomerByID(id));

        return  "customer";
    }

    @RequestMapping("/customer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getCustomerByID(id));

        return "customerform";
    }

    @RequestMapping("/customer/new")
    public String newProduct(Model model){
        model.addAttribute("customer", new Customer());

        return "customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String createOrUpdateCustomer(Customer customer){
        Customer updatedCustomer = customerService.createOrUpdateCustomer(customer);
        return "redirect:/customer/" + updatedCustomer.getId();
    }

    @RequestMapping("/customer/delete/{id}")
    public String delete(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
