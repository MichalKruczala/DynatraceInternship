import it.dynatrace.internship.clients.NbpApiClient;
import it.dynatrace.internship.configuration.AppConfiguration;
import it.dynatrace.internship.model.ExchangeRates;
import it.dynatrace.internship.services.NbpService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class NbpServiceTest {

    @MockBean
    private NbpApiClient nbpApiClient;

    @Autowired
    private NbpService nbpService;

    @Test
    public void testGetMidWhenApiReturnsValue() {

        ExchangeRates exchangeRates = new ExchangeRates();
        ExchangeRates.Rate rate = new ExchangeRates.Rate();
        rate.setMid(4.2244);
        exchangeRates.setRates(Collections.singletonList(rate));
        when(nbpApiClient.getMid("usd", "2023-04-19")).thenReturn(Optional.of(exchangeRates));
        Optional<Double> result = this.nbpService.getMid("usd", "2023-04-19");
        assertTrue(result.isPresent());
        assertEquals(4.2244, result.get(), 0.004);
    }

    @Test
    public void testGetMidWhenApiReturnsEmptyOptional() {
        when(this.nbpApiClient.getMid("usd", "2022-04-22")).thenReturn(Optional.empty());
        Optional<Double> result = this.nbpService.getMid("usd", "2022-04-22");
        assertFalse(result.isPresent());
    }
}