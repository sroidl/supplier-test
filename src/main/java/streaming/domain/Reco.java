package streaming.domain;

import com.google.auto.value.AutoValue;

import java.util.Optional;
import java.util.Set;

@AutoValue
abstract class Reco  {

    abstract String getProductId();
    abstract Optional<String> getVariationId();
    abstract int getAge();
    abstract Set<Double> getMoney();

    public static Builder builder(){
        return new AutoValue_Reco.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setProductId(String id);
        abstract Builder setVariationId(Optional<String> id);
        abstract Builder setAge(int age);
        abstract Builder setMoney(Set<Double> money);
        abstract Builder setMoney(Double... money);


        abstract Reco build();
    }
}
