package streaming.suppliers;

import java.util.function.Supplier;

public class FileClient implements StringSupplier {


    private final String filename;

    public FileClient(String filename) {
        this.filename = filename;
    }

    @Override
    public Supplier<String> generateSupplier(final int inputParameter) {
        return new Supplier<String>() {

            @Override
            public String get() {
                return getLineFromFile(filename, inputParameter);
            }
        };
    }


    private String getLineFromFile(String filename, int line) {
        return "This is the content from the file";
    }
}
