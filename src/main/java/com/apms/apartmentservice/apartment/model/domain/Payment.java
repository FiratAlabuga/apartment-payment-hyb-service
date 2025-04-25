package com.apms.apartmentservice.apartment.model.domain;

import com.apms.apartmentservice.common.model.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "PAYMENT")
public class Payment extends BaseEntity {
    private String paymentId;
    private String userId;
    private String transactionId;
    private BigDecimal amount;
    private String currency;
    // ManyToOne ilişkisi - bir Payment sadece bir PaymentType'a sahiptir
    @ManyToOne
    @JoinColumn(name = "payment_type_id")  // foreign key kolonu
    private PaymentType paymentType;
}
