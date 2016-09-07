package streaming.suppliers;

import java.util.function.Supplier;

public interface StringSupplier  {

    Supplier<String> generateSupplier(int inputParameter);

}
