package me.kalpha.builder.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Getter
@EqualsAndHashCode (exclude = {"id", "paymentMethod", "price"})
public class PaymentDto {
    private Long id;
    private Long ownerId;
    private LocalDate payDate;
    private String calculateCode;
    private Payment.Method paymentMethod;
    private int price;

    PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.ownerId = payment.getOwnerId();
        this.payDate = payment.getPayDate();
        this.calculateCode = payment.getCalculateCode();
        this.paymentMethod = payment.getPaymentMethod();
        this.price = payment.getPrice();
    }

    /*
     * EqualsAndHashCode를 이용해 dto로 Map<dto>을 검색해서 get 한다.
     */
    public static Stream<List<PaymentDto>> classify (List<Payment> payments) {
        Map<PaymentDto, List<PaymentDto>> classifiedPayment = new LinkedHashMap<>();

        for (Payment payment:payments) {
            PaymentDto dto = new PaymentDto(payment);
            List<PaymentDto> list = classifiedPayment.get(dto);

            if (list != null) {
                list.add(dto);
            } else {
                // 원소가 1개 일때 singleton
                classifiedPayment.put(dto, new ArrayList<>(Collections.singleton(dto)));
            }
        }
        return classifiedPayment.entrySet().stream()
                .map(Map.Entry::getValue);
    }
}
