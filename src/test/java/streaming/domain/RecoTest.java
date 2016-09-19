package streaming.domain;

import org.junit.Test;

public class RecoTest {

    @Test
    public void shouldCreateReco() {
        Reco r = Reco.builder()
                .setProductId("Hlalo")
                .setAge(12)
                .setMoney(12.4d, 144.42d)
                .build();

        System.out.println("r = " + r);

    }
}