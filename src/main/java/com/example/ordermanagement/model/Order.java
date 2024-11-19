@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_groceryIterms",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "grocery_id")
    )
    private List<Grocery> groceries;

    private Date date;

    private double price;
}
