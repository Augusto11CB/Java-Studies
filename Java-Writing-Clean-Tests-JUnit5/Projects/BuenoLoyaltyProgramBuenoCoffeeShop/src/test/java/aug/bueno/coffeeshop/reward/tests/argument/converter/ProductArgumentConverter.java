package aug.bueno.coffeeshop.reward.tests.argument.converter;


import aug.bueno.coffeeshop.product.Product;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, source.getClass(), "Can only convert from String");
        assertEquals(Product.class, targetType, "Can only convert to Product");

        String[] productString = source.toString().split(";");

        Product product = new Product(
                Long.parseLong(productString[0]),
                productString[1].trim(),
                Double.parseDouble(productString[2])
        );

        return product;
    }
}