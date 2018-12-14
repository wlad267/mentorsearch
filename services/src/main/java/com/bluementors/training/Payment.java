package com.bluementors.training;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Payment {
    @Id
    private Long id;
    private BigDecimal amount;
    @OneToOne
    private Training training;

}
