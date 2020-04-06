package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping({"/list", "/", ""})  // This is the path entered in browser (localhost:8080/customers)
    public String listCustomers(Model model){

        // object (customerService.listAllCustomers()) is associated with its name (customers) to be used in html (listcustomers.html)
        model.addAttribute("customers", customerService.listAll());
        
        return "customer/listcustomers"; // This must equal the html file name (listcustomers.html)
    }

    @RequestMapping("/{id}")
    // @PathVariable binds the web data to our variable
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer",  customerService.getByID(id));

        return  "customer/showcustomer";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getByID(id));

        return "customer/maintaincustomer";
    }

    @RequestMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("customer", new Customer());

        return "customer/maintaincustomer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createOrUpdateCustomer(Customer customer){
        Customer updatedCustomer = (Customer) customerService.createOrUpdate(customer);
        return "redirect:/customer/" + updatedCustomer.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customer/";
    }
}
