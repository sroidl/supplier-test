package streaming;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamGeneratorTest {

    @Test
    public void shouldProduceStreamTakingHttpFirst() {
        System.out.println("HttpFirst");
        Stream<String> stringStream = StringSupplyTemplates.templateHttpFirst(10);

        stringStream.limit(40).forEach(System.out::println);

    }

    @Test
    public void shouldProduceStreamTakingFileFirst() {
        System.out.println("FileFirst");
        Stream<String> stringStream = StringSupplyTemplates.templateFileFirst(40);

        stringStream.limit(40).forEach(System.out::println);

    }


}