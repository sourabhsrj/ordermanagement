import com.psj.onlineGrocery.Entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Long id);

    void deleteUser(Long id);
}