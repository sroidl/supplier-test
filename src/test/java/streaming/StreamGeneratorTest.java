package streaming;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamGeneratorTest {

    @Test
    public void shouldProduceStream() {
        Stream<String> stringStream = StringSupplyTemplates.templateHttpFirst(10);

        stringStream.limit(40).forEach(System.out::println);

    }
}