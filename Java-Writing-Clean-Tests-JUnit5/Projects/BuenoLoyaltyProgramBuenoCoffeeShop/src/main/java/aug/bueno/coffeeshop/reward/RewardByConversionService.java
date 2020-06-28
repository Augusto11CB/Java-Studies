package aug.bueno.coffeeshop.reward;

import aug.bueno.coffeeshop.product.Product;
import lombok.Getter;

import java.util.List;

public class RewardByConversionService extends RewardService {

    @Getter
    private double amount;

    @Override
    public RewardInformation applyReward(List<Product> order, long customerPoints) {
        RewardInformation rewardInformation = new RewardInformation();

        if (customerPoints >= neededPoints) {
            rewardInformation = new RewardInformation(neededPoints, amount);
        }

        return rewardInformation;
    }


    public void setAmount(double amount) {
        if(amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount should be greater than zero");
        }
    }
}
