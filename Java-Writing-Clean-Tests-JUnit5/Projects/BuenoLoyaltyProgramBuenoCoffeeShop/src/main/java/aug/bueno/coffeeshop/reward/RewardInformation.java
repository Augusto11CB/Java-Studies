package aug.bueno.coffeeshop.reward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardInformation {

    long pointsRedeemed;
    double discount;

}
