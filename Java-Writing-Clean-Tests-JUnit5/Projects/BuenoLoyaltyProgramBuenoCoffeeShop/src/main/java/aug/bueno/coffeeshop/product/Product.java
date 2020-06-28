package aug.bueno.coffeeshop.product;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    long id;

    String name;

    double price;

}
