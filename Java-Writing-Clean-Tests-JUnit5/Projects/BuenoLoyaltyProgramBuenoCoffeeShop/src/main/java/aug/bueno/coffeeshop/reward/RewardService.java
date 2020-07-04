package aug.bueno.coffeeshop.reward;

import aug.bueno.coffeeshop.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


public abstract class RewardService {

    @Getter
    @Setter
    protected long neededPoints;

    public abstract RewardInformation applyReward(List<Product> order, long customerPoints);

    protected double calculateTotal(List<Product> order) {
        double sum = 0;

        if (Objects.nonNull(order)){
            sum = order.stream().mapToDouble(Product::getPrice).sum();
        }

        return sum;
    }


}
