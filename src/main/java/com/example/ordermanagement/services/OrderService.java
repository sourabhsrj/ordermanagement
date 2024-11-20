import com.psj.onlineGrocery.Entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getAllOrders();

    Order getOrder(Long id);

    Order createOrder(Order order);

    Order updateOrder(Order order, Long id);

    void deleteOrder(Long id);
}
