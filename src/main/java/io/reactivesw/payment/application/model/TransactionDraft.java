package io.reactivesw.payment.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.model.Money;
import io.reactivesw.payment.infrastructure.enums.TransactionState;
import io.reactivesw.payment.infrastructure.enums.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel
@Data
public class TransactionDraft {

  @ApiModelProperty(value = "The time at which the transaction took place.", required = false)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime timestamp;

  @ApiModelProperty(value = "The type of this transaction.", required = true)
  private TransactionType type;

  @ApiModelProperty(required = true)
  private Money amount;

  @ApiModelProperty(value = "The identifier that is used by the interface that managed the transaction (usually the PSP). " +
          "If a matching interaction was logged in the interfaceInteractions array, the corresponding interaction should be findable with this ID.",
          required = false)
  private String interactionId;

  @ApiModelProperty(value = "The state of this transaction.",
          required = true)
  private TransactionState state;
}
