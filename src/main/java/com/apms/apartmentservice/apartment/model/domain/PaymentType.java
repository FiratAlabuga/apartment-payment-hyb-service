package com.apms.apartmentservice.apartment.model.domain;

import com.apms.apartmentservice.common.model.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "PAYMENT_TYPE")
public class PaymentType extends BaseEntity {
    private String paymentTypeId;
    private String type;
    private String description;
    private String icon;
    private String provider;
    private String providerUrl;
    private String providerApiKey;
    private String providerApiSecret;
    // OneToMany ilişkisi - bir PaymentType birçok Payment'a sahip olabilir
    @OneToMany(mappedBy = "paymentType")
    private List<Payment> payments;
}
