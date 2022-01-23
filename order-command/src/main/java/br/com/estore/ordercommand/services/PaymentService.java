package br.com.estore.ordercommand.services;

import br.com.estore.ordercommand.domain.dtos.PaymentDTO;

public interface PaymentService {

    Boolean isValidCard(final PaymentDTO payment);

}
