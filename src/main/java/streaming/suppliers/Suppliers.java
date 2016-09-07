package streaming.suppliers;

import java.util.function.Supplier;

public class Suppliers {


    public static Supplier<String> httpSupplier(int inputParameter) {
        return new HttpClient().generateSupplier(inputParameter);
    }

    public static Supplier<String> fileSupplier(int inputParameter, String filename) {
        return new FileClient(filename).generateSupplier(inputParameter);
    }

}
