package me.kalpha.builder;

import lombok.NoArgsConstructor;
import me.kalpha.builder.entity.PaymentDto;
import me.kalpha.builder.entity.Sales;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class SalesConverter {
    public static Sales createSales(List<PaymentDto> payments) {
        Sales sales = Sales.builder()
                .ownerId(payments.get(0).getOwnerId())
                .calculateCode(payments.get(0).getCalculateCode())
                .payDate(payments.get(0).getPayDate())
                .build();

        payments.forEach(paymentDto -> sales.add(paymentDto.getPrice(), paymentDto.getPaymentMethod()));
        return sales;
    }

    public static List<Sales> createSalesList(Stream<List<PaymentDto>> paymentDtoStream) {
        return paymentDtoStream
                .map(SalesConverter::createSales)
                .collect(Collectors.toList());
    }

}
