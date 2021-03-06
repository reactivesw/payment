package io.reactivesw.payment.application.model.mapper;

import com.google.common.collect.Sets;

import com.braintreegateway.Transaction;

import io.reactivesw.payment.application.model.PaymentMethodInfo;
import io.reactivesw.payment.domain.model.value.LocalizedStringValue;
import io.reactivesw.payment.domain.model.value.PaymentMethodInfoValue;


/**
 * Created by Davis on 17/2/6.
 */
public final class PaymentMethodInfoMapper {

  /**
   * Instantiates a new Payment method info mapper.
   */
  private PaymentMethodInfoMapper() {
  }

  /**
   * Of payment method info value.
   *
   * @param transaction the transaction
   * @return the payment method info value
   */
  public static PaymentMethodInfoValue build(Transaction transaction) {
    PaymentMethodInfoValue result = new PaymentMethodInfoValue();

    String method = transaction.getPaymentInstrumentType();
    result.setMethod(method);
    LocalizedStringValue name = LocalizedStringValue.build("en", method);
    result.setName(Sets.newHashSet(name));
    // TODO: 17/2/6 set payment intreface
    result.setPaymentInterface(null);

    return result;
  }

  /**
   * Entity to model payment method info.
   *
   * @param entity the entity
   * @return the payment method info
   */
  public static PaymentMethodInfo toModel(PaymentMethodInfoValue entity) {
    PaymentMethodInfo model = new PaymentMethodInfo();

    model.setPaymentInterface(entity.getPaymentInterface());
    model.setMethod(entity.getMethod());
    model.setName(LocalizedStringMapper.toModelDefaultNull(entity.getName()));

    return model;
  }
}