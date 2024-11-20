
import com.psj.onlineGrocery.Entities.Customer;
import com.psj.onlineGrocery.Entities.Grocery;
import com.psj.onlineGrocery.Repositories.GroceryRepository;
import com.psj.onlineGrocery.Services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public List<Grocery> getAllGroceries() {
        return groceryRepository.findAll();
    }

    @Override
    public Grocery getGrocery(Long id) {
        return groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Grocery not found"));
    }

    @Override
    public Grocery createGrocery(Grocery grocery) {
        return groceryRepository.save(grocery);
    }

    @Override
    public Grocery updateGrocery(Grocery grocery, Long id) {
        Grocery existingGroceries = this.getGrocery(id);
        existingGroceries.setCategory(grocery.getCategory());
        existingGroceries.setPrice(grocery.getPrice());
        existingGroceries.setQuantity(grocery.getQuantity());
        return groceryRepository.save(existingGroceries);
    }

    @Override
    public void deleteGrocery(Long id) {
       Grocery grocery = this.getGrocery(id);
       groceryRepository.deleteById(grocery.getId());
    }
}
