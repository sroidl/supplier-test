package streaming;

import streaming.api.Suppliers;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamGenerator {


    public Stream<String> template1(int input) {
        return Suppliers
                .newSupplyChain()
                .take(10).from(httpSupplier(input))
                .then()
                .take(5).from(fileSupplier(input, filename))
                .then()
                .takeAll().from(finiteStringSupplier)
                .build();
    }


}
