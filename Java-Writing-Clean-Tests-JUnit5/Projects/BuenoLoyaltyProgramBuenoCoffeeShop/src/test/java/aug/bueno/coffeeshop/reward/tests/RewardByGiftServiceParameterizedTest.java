package aug.bueno.coffeeshop.reward.tests;

import aug.bueno.coffeeshop.product.Product;
import aug.bueno.coffeeshop.product.SpecialProductsEnum;
import aug.bueno.coffeeshop.reward.RewardByGiftService;
import aug.bueno.coffeeshop.reward.RewardInformation;
import aug.bueno.coffeeshop.reward.tests.argument.converter.ProductArgumentConverter;
import aug.bueno.coffeeshop.reward.tests.argument.provider.ProductIdArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RewardByGiftServiceParameterizedTest {
    private RewardByGiftService reward = null;

    @BeforeEach
    void setUp() {
        reward = new RewardByGiftService();
        reward.setNeededPoints(100);
        System.out.println("BeforeEach");
    }

    @ParameterizedTest
    @ValueSource(longs = { 1, 2, 3, 4 })
    void discountShouldBeApplied(long productId) {
        reward.setGiftProductId(productId);
        RewardInformation info = reward.applyReward(
                getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    //@EnumSource(SpecialProductsEnum.class)
    //@EnumSource(value = SpecialProductsEnum.class, names = { "BIG_LATTE", "BIG_TEA" })
    //@EnumSource(value = SpecialProductsEnum.class, names = { "BIG_LATTE", "BIG_TEA" }, mode = EnumSource.Mode.EXCLUDE)
    @EnumSource(value = SpecialProductsEnum.class, names = "^BIG.*", mode = EnumSource.Mode.MATCH_ALL)
    void discountShouldBeAppliedEnumSource(SpecialProductsEnum product) {
        reward.setGiftProductId(product.getProductId());
        RewardInformation info = reward.applyReward(
                getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @MethodSource("productIds")
    void discountShouldBeAppliedMethodSource(long productId) {
        reward.setGiftProductId(productId);
        RewardInformation info = reward.applyReward(
                getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @MethodSource("productIdsCustomerPoints")
    void discountShouldBeAppliedMethodSourceMultipleParams(long productId, long customerPoints) {
        reward.setGiftProductId(productId);
        RewardInformation info = reward.applyReward(
                getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @CsvSource({ "1, 200", "2, 150", "5, 100" })
    void discountShouldBeAppliedCsvSource(long productId, long customerPoints) {
        reward.setGiftProductId(productId);
        RewardInformation info = reward.applyReward(
                getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/product-point-data.csv")
    void discountShouldBeAppliedCsvFileSource(long productId, long customerPoints) {
        reward.setGiftProductId(productId);
        RewardInformation info = reward.applyReward(
                getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @ArgumentsSource(ProductIdArgumentsProvider.class)
    void discountShouldBeAppliedArgumentsSource(long productId, long customerPoints) {
        reward.setGiftProductId(productId);
        RewardInformation info = reward.applyReward(
                getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1; Small Decaf; 1.99", "2; Big Decaf; 2.49" })
    void discountShouldBeApplied(
            @ConvertWith(ProductArgumentConverter.class) Product product) {
        System.out.println("Testing product " + product.getName());
        reward.setGiftProductId(product.getId());
        RewardInformation info = reward.applyReward(
                getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
    }

    static Stream<Arguments> productIdsCustomerPoints() {
        return productIds()
                .mapToObj(
                        productId ->
                                Arguments.of(productId, 100 * productId) // Object ... arguments
                );
    }

    private static LongStream productIds() {
        return LongStream.range(1, 6);
    }

    private List<Product> getSampleOrder() {
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        Product bigDecaf = new Product(2, "Big Decaf", 2.49);
        Product bigLatte = new Product(3, "Big Latte", 2.99);
        Product bigTea = new Product(4, "Big Tea", 2.99);
        Product espresso = new Product(5, "Espresso", 2.99);
        return Arrays.asList(
                smallDecaf, bigDecaf, bigLatte, bigTea, espresso);
    }
}
