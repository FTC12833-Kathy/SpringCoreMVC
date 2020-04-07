package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testList() throws Exception{
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn(customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/listcustomers"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    void testShow()throws Exception {
        Integer id = 1;

        when(customerService.getByID(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/showcustomer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    void testEdit()throws Exception {
        Integer id = 1;

        when(customerService.getByID(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/maintaincustomer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    void testNew()throws Exception {
        Integer id = 1;

        verifyNoInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/maintaincustomer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    void testCreateOrUpdate() throws Exception {
        Integer id = 1;
        String firstName = "first";
        String lastName = "last";
        String email = "email";
        String phone = "phone";
        String address1 = "address 1";
        String address2 = "address 2";
        String city = "city";
        String state = "state";
        String zip = "zip";
        
        Customer returnCustomer = new Customer();
        returnCustomer.setId(1);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setEmail(email);
        returnCustomer.setPhone(phone);
        returnCustomer.setAddress1(address1);
        returnCustomer.setAddress2(address2);
        returnCustomer.setCity(city);
        returnCustomer.setState(state);
        returnCustomer.setZip(zip);
        
        when(customerService.createOrUpdate(ArgumentMatchers.<Customer>any())).thenReturn(returnCustomer);
        
        mockMvc.perform(post("/customer")
            .param("id", "1")
            .param("firstName", "first")
            .param("lastName", "last")
            .param("email", "email")
            .param("phone", "phone")
            .param("address1", "address 1")
            .param("address2", "address 2")
            .param("city", "city")
            .param("state", "state")
            .param("zip", "zip"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phone", is(phone))))
                .andExpect(model().attribute("customer", hasProperty("address1", is(address1))))
                .andExpect(model().attribute("customer", hasProperty("address2", is(address2))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zip", is(zip))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).createOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(phone, boundCustomer.getValue().getPhone());
        assertEquals(address1, boundCustomer.getValue().getAddress1());
        assertEquals(address2, boundCustomer.getValue().getAddress2());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(state, boundCustomer.getValue().getState());
        assertEquals(zip, boundCustomer.getValue().getZip());
    }

    @Test
    void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/"));

        verify(customerService, times(1)).delete(id);
    }
}