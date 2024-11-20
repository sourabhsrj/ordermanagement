
import com.psj.onlineGrocery.Entities.Customer;
import com.psj.onlineGrocery.Repositories.CustomerRepository;
import com.psj.onlineGrocery.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Customer existingCustomer = this.getCustomer(id);
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setMobile(customer.getMobile());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteUser(Long id) {
       Customer customer = this.getCustomer(id);
       customerRepository.deleteById(customer.getId());
    }
}
