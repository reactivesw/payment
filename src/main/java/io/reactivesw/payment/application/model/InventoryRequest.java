package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Inventory request.
 */
@Getter
@Setter
public class InventoryRequest {
  /**
   * The Sku name.
   */
  private String skuName;

  /**
   * The Quantity.
   */
  private Integer quantity;
}
