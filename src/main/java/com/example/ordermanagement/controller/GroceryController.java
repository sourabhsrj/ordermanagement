

import com.psj.onlineGrocery.Entities.Grocery;
import com.psj.onlineGrocery.Services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery")
public class GroceryController {

    @Autowired
    GroceryService groceryService;

    @PostMapping
    public ResponseEntity<Grocery> createGrocery(@RequestBody Grocery grocery){
        try {
            Grocery createdGrocery = groceryService.createGrocery(grocery);
            return new ResponseEntity<>(createdGrocery, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Grocery>> getAllGroceries(){
        List<Grocery> groceryList = groceryService.getAllGroceries();
        if (!groceryList.isEmpty()) {
            return new ResponseEntity<>(groceryList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(groceryList, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grocery> getGrocery(@PathVariable Long id){
        try {
            Grocery grocery = groceryService.getGrocery(id);
            return new ResponseEntity<>(grocery, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grocery> updateGrocery(@PathVariable Long id, @RequestBody Grocery grocery){
        try{
            Grocery updatedGrocery = groceryService.updateGrocery(grocery, id);
            return new ResponseEntity<>(updatedGrocery, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrocery(@PathVariable Long id){
        try{
            groceryService.deleteGrocery(id);
            return new ResponseEntity<>("Grocery deleted successfully", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Grocery not found", HttpStatus.NOT_FOUND);
        }
    }
}
