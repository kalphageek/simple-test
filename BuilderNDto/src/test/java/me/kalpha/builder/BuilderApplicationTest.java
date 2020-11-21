package me.kalpha.builder;

import me.kalpha.builder.entity.Payment;
import me.kalpha.builder.entity.PaymentDto;
import me.kalpha.builder.entity.Sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BuilderApplicationTest {
    private List<Payment> payments;

    @BeforeEach
    public void setup() {
        //given
        //ownerId + payDate + calculateCode 의 Hashcode로 Map의 유일성 검증 -> 처음 3개는 동일
        payments = Arrays.asList(
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2020, 10,21))
                        .calculateCode("C001")
                        .price(2000)
                        .paymentMethod(Payment.Method.CASH)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2020, 10,21))
                        .calculateCode("C001")
                        .price(3000)
                        .paymentMethod(Payment.Method.CREDIT_CARD)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2020, 10,21))
                        .calculateCode("C001")
                        .price(5000)
                        .paymentMethod(Payment.Method.MOBILE)
                        .build(),
                Payment.builder()
                        .ownerId(2L)
                        .payDate(LocalDate.of(2020, 11,20))
                        .calculateCode("C001")
                        .price(2000)
                        .paymentMethod(Payment.Method.CASH)
                        .build(),
                Payment.builder()
                        .ownerId(2L)
                        .payDate(LocalDate.of(2020, 11,21))
                        .calculateCode("C002")
                        .price(3000)
                        .paymentMethod(Payment.Method.CREDIT_CARD)
                        .build(),
                Payment.builder()
                        .ownerId(2L)
                        .payDate(LocalDate.of(2020, 11,21))
                        .calculateCode("C003")
                        .price(5000)
                        .paymentMethod(Payment.Method.MOBILE)
                        .build()
        );
    }

    @Test
    public void classfy_succ_payment() {
        //given
        List<List<PaymentDto>> classfied = PaymentDto.classify(payments).collect(Collectors.toList());
        List<PaymentDto> firstPaymentDtos = classfied.get(0);

        //then
        assertEquals(classfied.size(), 4);
        assertEquals(firstPaymentDtos.size(), 3);
        assertEquals(firstPaymentDtos.get(0).getPaymentMethod(), Payment.Method.CASH);
        assertEquals(firstPaymentDtos.get(1).getPaymentMethod(), Payment.Method.CREDIT_CARD);
        assertEquals(firstPaymentDtos.get(2).getPaymentMethod(), Payment.Method.MOBILE);
    }

    @Test
    public void transform_succ_sales() {
        //given
        List<Sales> salesList = SalesConverter.createSalesList(PaymentDto.classify(payments));
        Sales sales = salesList.get(0);

        //then
        assertEquals(sales.getTotalAmount(), 10000);
        assertEquals(sales.getCreditCardAmount(), 3000);
        assertEquals(sales.getCashAmount(), 2000);
        assertEquals(sales.getMobileAmount(), 5000);
    }
}