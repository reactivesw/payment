package io.reactivesw.payment.domain.model;

import io.reactivesw.payment.infrastructure.enums.EventStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Event Message.
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "event")
public class EventMessage {

  /**
   * The id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created Time.
   */
  @Column(name = "created_at")
  private long createdAt;

  /**
   * the event will be expired in expire.
   */
  private long expire;

  /**
   * The Version.
   */
  private Integer version;

  /**
   * Json string data.
   */
  private String data;

  /**
   * The topic.
   */
  private String topic;

  /**
   * The event status.
   */
  private EventStatus status;
}
