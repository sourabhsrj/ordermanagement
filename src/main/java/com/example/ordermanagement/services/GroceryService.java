import com.psj.onlineGrocery.Entities.Grocery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroceryService {

    List<Grocery> getAllGroceries();

    Grocery getGrocery(Long id);

    Grocery createGrocery(Grocery grocery);

    Grocery updateGrocery(Grocery grocery, Long id);

    void deleteGrocery(Long id);
}
