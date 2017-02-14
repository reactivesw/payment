package io.reactivesw.payment.application.model.action;

import io.reactivesw.payment.application.model.CreditCardView;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 17/2/3.
 */
@Getter
@Setter
public class AddCreditCardAction {
  /**
   * The Action.
   */
  private String action;

  /**
   * The Credit cart.
   */
  private CreditCardView creditCart;
}