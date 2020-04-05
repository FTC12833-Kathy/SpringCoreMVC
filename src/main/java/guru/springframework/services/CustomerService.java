package guru.springframework.services;

import guru.springframework.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomers();
    Customer getCustomerByID(Integer id);
    Customer createOrUpdateCustomer(Customer customer);
    void deleteCustomer(Integer id);
}
