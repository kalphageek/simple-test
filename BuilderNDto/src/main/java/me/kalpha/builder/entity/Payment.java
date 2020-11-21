package me.kalpha.builder.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Long ownerId;
    private LocalDate payDate;
    private String calculateCode;
    private Payment.Method paymentMethod;
    private int price;

    @Getter
    public enum Method {
        MOBILE("휴대폰"),
        CREDIT_CARD("카드"),
        CASH("현금");

        private String text;
        Method(String text) {
            this.text = text;
        }
    }
}
