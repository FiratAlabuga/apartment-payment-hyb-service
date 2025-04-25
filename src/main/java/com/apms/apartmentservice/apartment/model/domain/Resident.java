package com.apms.apartmentservice.apartment.model.domain;

import com.apms.apartmentservice.common.model.domain.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "RESIDENT")
public class Resident extends BaseEntity {
    private String residentId;
    private String apartmentId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String block;
    private String number;
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
