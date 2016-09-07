package streaming;

import streaming.api.SupplyChain;

import java.util.Arrays;
import java.util.stream.Stream;

import static streaming.suppliers.Suppliers.fileSupplier;
import static streaming.suppliers.Suppliers.httpSupplier;

public class StringSupplyTemplates {

    public static Stream<String> templateHttpFirst(int input) {
        return SupplyChain
                .newSupplyChain()
                .take(10).from(httpSupplier(input))
                .then()
                .take(5).from(fileSupplier(input, "filename"))
                .then()
                .takeAll().from(Arrays.asList("this", "is", "my", "static", "backup"))
                .build();
    }

    public static Stream<String> templateFileFirst(int input) {
        return SupplyChain
                .newSupplyChain()
                .take(5).from(fileSupplier(input, "filename"))
                .then()
                .take(10).from(httpSupplier(input))
                .then()
                .takeAll().from(Arrays.asList("this", "is", "my", "static", "backup"))
                .build();
    }



}
