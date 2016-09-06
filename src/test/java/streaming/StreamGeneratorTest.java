package streaming;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamGeneratorTest {

    @Test
    public void shouldProduceStream() {
        StreamGenerator generator = new StreamGenerator();

        Stream<String> stream = generator.getStream();
        stream.limit(40).forEach(System.out::println);
    }
}