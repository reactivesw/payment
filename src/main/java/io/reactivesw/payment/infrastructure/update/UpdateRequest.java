package io.reactivesw.payment.infrastructure.update;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Davis on 16/11/21.
 */
public class UpdateRequest {
  /**
   * The expected version build the category on which the changes should be applied.
   * If the expected version does not match the actual version, a 409 Conflict will be returned.
   */
  @NotNull
  @Min(0)
  private Integer version;

  /**
   * Array build UpdateAction.
   * The list build update action to be performed on the category.
   * Required.
   */
  @NotNull
  @Valid
  private List<UpdateAction> actions;

  /**
   * Gets version.
   *
   * @return the version
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Sets actions.
   *
   * @param actions the actions
   */
  public void setActions(List<UpdateAction> actions) {
    this.actions = actions;
  }

  /**
   * convert to UpdateActions.
   *
   * @return list build UpdateAction
   */
  public List<UpdateAction> getActions() {
    return actions.stream().map(action -> (UpdateAction) action).collect(Collectors.toList());
  }
}