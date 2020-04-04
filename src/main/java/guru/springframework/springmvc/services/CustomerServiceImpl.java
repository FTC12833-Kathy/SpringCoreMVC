package guru.springframework.springmvc.services;

import guru.springframework.springmvc.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
    private Map<Integer, Customer> customers;

    public CustomerServiceImpl(){
        customers = new HashMap<>();
        loadCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<Customer>(customers.values());
    }

    @Override
    public Customer getCustomerByID(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer createOrUpdateCustomer(Customer customer) {
        if (customer == null){
            throw new RuntimeException("customer record can't be null");
        } else{
            if (customer.getCustomerID() == null){
                customer.setCustomerID(getNextKey());
            }

            customers.put(customer.getCustomerID(), customer);
            return customer;
        }
    }

    private Integer getNextKey(){
        return Collections.max(customers.keySet()) + 1;
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    private void loadCustomers(){
        Customer cust = new Customer();
        cust.setCustomerID(1);
        cust.setFirstName("Fred");
        cust.setLastName("Flintstone");
        cust.setEmail("none");
        cust.setPhone("Just holler");
        cust.setAddress1("111 Stoney Way");
        cust.setAddress2("");
        cust.setCity("Bedrock");
        cust.setState("NY");
        cust.setZip("12345");
        customers.put(1, cust);

        cust = new Customer();
        cust.setCustomerID(2);
        cust.setFirstName("Barney");
        cust.setLastName("Rubble");
        cust.setEmail("haha");
        cust.setPhone("Just yell");
        cust.setAddress1("114 Stoney Way");
        cust.setAddress2("");
        cust.setCity("Bedrock");
        cust.setState("NY");
        cust.setZip("12345");
        customers.put(2, cust);

        cust = new Customer();
        cust.setCustomerID(3);
        cust.setFirstName("Herman");
        cust.setLastName("Munster");
        cust.setEmail("franknstein@gmail.com");
        cust.setPhone("555-5555");
        cust.setAddress1("1313 Mockingbird Lane");
        cust.setAddress2("");
        cust.setCity("Mockingbird Heights");
        cust.setState("CA");
        cust.setZip("90120");
        customers.put(3, cust);

        cust = new Customer();
        cust.setCustomerID(4);
        cust.setFirstName("Mike");
        cust.setLastName("Brady");
        cust.setEmail("franknstein@gmail.com");
        cust.setPhone("762-0799");
        cust.setAddress1("4222 Clinton Way");
        cust.setAddress2("");
        cust.setCity("Studio City");
        cust.setState("CA");
        cust.setZip("90120");
        customers.put(4, cust);
    }
}
