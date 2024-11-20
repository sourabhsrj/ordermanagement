
import com.psj.onlineGrocery.Entities.Order;
import com.psj.onlineGrocery.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllGOrders(){
        List<Order> orderList = orderService.getAllOrders();
        if (!orderList.isEmpty()) {
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(orderList, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id){
        try {
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order){
        try{
            Order updatedOrder = orderService.updateOrder(order, id);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        try{
            orderService.deleteOrder(id);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
