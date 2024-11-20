
import com.psj.onlineGrocery.Entities.Customer;
import com.psj.onlineGrocery.Entities.Grocery;
import com.psj.onlineGrocery.Entities.Order;
import com.psj.onlineGrocery.Repositories.CustomerRepository;
import com.psj.onlineGrocery.Repositories.GroceryRepository;
import com.psj.onlineGrocery.Repositories.OrderRepository;
import com.psj.onlineGrocery.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GroceryRepository groceryItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order Not Found"));
    }

    @Override
    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Long> itemIds = order.getGroceries().stream()
                .map(Grocery::getId)
                .collect(Collectors.toList());

        List<Grocery> groceryItems = groceryItemRepository.findAllById(itemIds);

        if (groceryItems.size() != itemIds.size()) {
            throw new RuntimeException("Some Groceries not found");
        }

        double totalPrice = groceryItems.stream()
                .mapToDouble(Grocery::getPrice)
                .sum();
        order.setCustomer(customer);
        order.setGroceries(groceryItems);
        order.setPrice(totalPrice);

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        order.setDate(calendar.getTime());

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order updatedOrder, Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (updatedOrder.getCustomer() != null) {
            Customer customer = customerRepository.findById(updatedOrder.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            existingOrder.setCustomer(customer);
        }

        if (updatedOrder.getGroceries() != null && !updatedOrder.getGroceries().isEmpty()) {
            List<Long> itemIds = updatedOrder.getGroceries().stream()
                    .map(Grocery::getId)
                    .collect(Collectors.toList());

            List<Grocery> groceryItems = groceryItemRepository.findAllById(itemIds);
            if (groceryItems.size() != itemIds.size()) {
                throw new RuntimeException("Some Groceries not found");
            }

            existingOrder.setGroceries(groceryItems);

            double totalPrice = groceryItems.stream()
                    .mapToDouble(Grocery::getPrice)
                    .sum();
            existingOrder.setPrice(totalPrice);
        }

        if (updatedOrder.getDate() != null) {
            existingOrder.setDate(updatedOrder.getDate());
        }

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
      Order order = this.getOrder(id);
      orderRepository.deleteById(order.getId());
    }
}
