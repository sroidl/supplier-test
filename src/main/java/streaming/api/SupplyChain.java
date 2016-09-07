package streaming.api;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SupplyChain {


    private static final Stream<String> EMPTY_STREAM = Stream.empty();

    private Stream<String> currentStream;

    private SupplyChain() {
        this.currentStream = EMPTY_STREAM;
    }

    private SupplyChain(Stream<String> stream) {
        this.currentStream = stream;
    }

    public static SupplyChain newSupplyChain() {
        return new SupplyChain();
    }

    private SupplyChain add(Stream<String> newStream) {
        return new SupplyChain(Stream.concat(currentStream, newStream));
    }


    public InfiniteStringSupplierBuilder take(int amount) {
        return new InfiniteStringSupplierBuilder(amount);
    }

    public SupplyChain then() {
        return this;
    }

    public FiniteSupplierBuilder takeAll() {
        return new FiniteSupplierBuilder();
    }

    public Stream<String> build() {
        return currentStream;
    }

    public class InfiniteStringSupplierBuilder {
        private final SupplyChain supplier;

        final int amount;

        private InfiniteStringSupplierBuilder(int amount) {
            this.supplier = SupplyChain.this;
            this.amount = amount;
        }

        public SupplyChain from(Supplier<String> stringSupplier) {

            Stream<String> s = Stream.generate(stringSupplier);
            return supplier.add(s.limit(amount));
        }

    }

    public class FiniteSupplierBuilder {

        public static final boolean NOT_PARALLEL = false;
        final SupplyChain supplier;

        public FiniteSupplierBuilder() {
            supplier = SupplyChain.this;
        }


        public SupplyChain from(Collection<String> stringCollection) {
            return supplier.add(StreamSupport.stream(stringCollection.spliterator(), NOT_PARALLEL));
        }
    }
}
