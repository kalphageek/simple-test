package me.kalpha.builder.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Sales {
    @Id
    @GeneratedValue
    private Long id;
    private Long ownerId;
    private LocalDate payDate;
    private String calculateCode;
    private int totalAmount;
    private int mobileAmount;
    private int creditCardAmount;
    private int cashAmount;

    public void add(int amount, Payment.Method paymentMethod) {
        if (paymentMethod == Payment.Method.MOBILE) {
            mobileAmount = amount;
        } else if (paymentMethod == Payment.Method.CREDIT_CARD) {
            creditCardAmount = amount;
        } else if (paymentMethod == Payment.Method.CASH) {
            cashAmount = amount;
        }
        totalAmount += amount;
    }
}
