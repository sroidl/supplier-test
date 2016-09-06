package streaming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HttpClient implements StringSupplier{


    @Override
    public Supplier<String> generateSupplier(int inputParameter) {
        return supplyStringsViaHttpRequest(inputParameter);
    }

    private Supplier<String> supplyStringsViaHttpRequest (final int inputParam) {

        return new Supplier<String>() {

            Queue<String> buffer = new LinkedList<>();

            // only needed to simulate call to http;
            int call = 0;
            private void refill() {
                /* Refill buffer by making call to distant server */
                Stream.iterate(call, i -> i+1)
                        .map(i -> "s" + i)
                        .limit(10)
                        .forEach(buffer::offer);
                call+= 10;
            }

            @Override
            public String get() {
                if (buffer.isEmpty()) {
                    System.out.println("Called refill");
                    refill();
                }
                return buffer.poll();
            }
        };
    }




}
