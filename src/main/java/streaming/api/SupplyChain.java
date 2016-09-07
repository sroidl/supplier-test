package streaming.api;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static streaming.api.SupplyChain.CollectionSupplierBuilder.NOT_PARALLEL;

public class SupplyChain {




    private Stream<String> currentStream;

    private SupplyChain() {
        this.currentStream = Stream.empty();
    }

    private SupplyChain(Stream<String> stream) {
        this.currentStream = stream;
    }

    public static SupplyChain newSupplyChain() {
        return new SupplyChain();
    }

    private SupplyChain append(Stream<String> newStream) {
        return new SupplyChain(Stream.concat(currentStream, newStream));
    }


    public StreamSupplierBuilder take(int amount) {
        return new StreamSupplierBuilder(amount);
    }

    public SupplyChain then() {
        return this;
    }

    public CollectionSupplierBuilder takeAll() {
        return new CollectionSupplierBuilder();
    }

    public Stream<String> build() {
        return currentStream;
    }

    public class StreamSupplierBuilder {
        private final SupplyChain supplier;

        final int amount;

        private StreamSupplierBuilder(int amount) {
            this.supplier = SupplyChain.this;
            this.amount = amount;
        }

        public SupplyChain from(Supplier<String> stringSupplier) {

            Stream<String> s = Stream.generate(stringSupplier).limit(amount);
            return supplier.append(s);
        }

        public SupplyChain from(Collection<String> stringCollection) {
            Stream s = StreamSupport.stream(stringCollection.spliterator(), NOT_PARALLEL).limit(amount);
            return supplier.append(s);
        }

    }

    public class CollectionSupplierBuilder {

        public static final boolean NOT_PARALLEL = false;
        final SupplyChain supplier;

        public CollectionSupplierBuilder() {
            supplier = SupplyChain.this;
        }


        public SupplyChain from(Collection<String> stringCollection) {
            return supplier.append(StreamSupport.stream(stringCollection.spliterator(), NOT_PARALLEL));
        }
    }
}
