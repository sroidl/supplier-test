package streaming;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamGenerator {


    private List<Supplier<String>> suppliers;


    public Stream<String> getStream() {
        Supplier<String> stringSupplier = new HttpClient().generateSupplier(12);

        Stream<String> firstStringSource = Stream.generate(stringSupplier);
        Stream<String> secondSupplier = Stream.iterate("#", s1 -> s1.concat("b"));

        return Stream.concat(firstStringSource.limit(25), secondSupplier);
    }


}
