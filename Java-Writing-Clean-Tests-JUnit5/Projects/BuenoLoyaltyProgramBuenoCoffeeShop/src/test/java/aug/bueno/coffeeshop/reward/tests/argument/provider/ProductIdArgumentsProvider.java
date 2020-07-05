package aug.bueno.coffeeshop.reward.tests.argument.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ProductIdArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return LongStream.range(1, 6)
                .mapToObj(
                        productId ->
                                Arguments.of(productId, 200 * productId)
                );
    }
}