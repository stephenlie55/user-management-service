package com.myproject.usermanagementservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_DATA")
public class UserData implements Serializable {
    private static final long serialVersionUID = -3231134897393261050L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 20)
    private Long id;

    @Column(name = "USER_NAME", length = 50)
    private String userFullName;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @Column(name = "BALANCE", length = 100)
    private BigDecimal accountBalance;
}
