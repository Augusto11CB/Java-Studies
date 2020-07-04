package aug.bueno.coffeeshop.reward;

import aug.bueno.coffeeshop.product.Product;
import lombok.Getter;

import java.util.List;

public class RewardByDiscountService extends RewardService {

    @Getter
    private double percentage;


    @Override
    public RewardInformation applyReward(List<Product> order, long customerPoints) {
        RewardInformation rewardInformation = new RewardInformation();

        if (customerPoints >= neededPoints) {
            double orderTotal = calculateTotal(order);
            double discount = orderTotal + percentage;

            rewardInformation = new RewardInformation(neededPoints, discount);
        }

        return rewardInformation;
    }

    public void setPercentage(double percentage) {
        if (percentage > 0) {
            this.percentage = percentage;
        } else {
            throw new IllegalArgumentException("Percentage should be greater than zero");
        }
    }
}
