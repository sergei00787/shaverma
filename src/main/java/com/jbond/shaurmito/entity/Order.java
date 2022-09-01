//tag::all[]
//tag::allButValidation[]
package com.jbond.shaurmito.entity;


import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@Entity
@Table(name="shaverma_order", schema = "shaverma_schm")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
  @SequenceGenerator(name = "order_seq", sequenceName = "order_id_seq", schema = "shaverma_schm")
  private Long id;

  private Date placedAt;

  @NotBlank(message="Delivery name is required")
  private String deliveryName;

  @NotBlank(message="Street is required")
  private String deliveryStreet;

  @NotBlank(message="City is required")
  private String deliveryCity;

  @NotBlank(message="State is required")
  @Length(max = 2)
  private String deliveryState;

  @NotBlank(message = "Zip code is required")
  private String deliveryZip;

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
          message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCvv;

  @ManyToMany(targetEntity = Shaverma.class)
  @JoinTable(
          name = "shaverma_order_shavermas",
          joinColumns = { @JoinColumn(name = "shavermaOrder") },
          inverseJoinColumns = { @JoinColumn(name = "shaverma")},
          schema = "shaverma_schm"
  )
  private List<Shaverma> shavermas = new ArrayList<>();

  public void addDesignShaverma(Shaverma shaverma) {
    this.shavermas.add(shaverma);
  }

  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }

}

