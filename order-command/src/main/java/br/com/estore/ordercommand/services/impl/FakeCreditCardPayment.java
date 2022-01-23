package br.com.estore.ordercommand.services.impl;

import br.com.estore.ordercommand.domain.dtos.PaymentDTO;
import br.com.estore.ordercommand.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class FakeCreditCardPayment implements PaymentService {

    @Override
    public Boolean isValidCard(final PaymentDTO payment) {
        return true;
    }

}
