package me.kalpha.enumsample.entity;

import java.util.Arrays;

/**
 * 추가하는 경우, DB에도 추가 필요.
 * 1. PaymentGroup은 PaymentOption의 배열을 갖고 있다.
 * 2. 특정 PaymentOption이 있을때 이 값이 어느 그룹에 포함될지는 PaymentGroup에 직접 물어보면(findGroup) 된다.
 * 3. select box로 그룹 리스트를 출력해야하는 경우엔 PaymentGroup.values()을 사용.
 */
public enum PaymentGroup {
    CASH("현금", new PaymentOption[]{
            PaymentOption.BANK_TRANSFER, PaymentOption.DEPOSITLESS, PaymentOption.FIELD_PAYMENT, PaymentOption.TOSS
    }),
    PG("결제대행사", new PaymentOption[]{
            PaymentOption.MOBILE, PaymentOption.CREDIT_CARD, PaymentOption.SIMPLE_PAY
    }),
    ETC("기타", new PaymentOption[]{
            PaymentOption.POINT, PaymentOption.COUPON
    }),
    EMPTY("없음", new PaymentOption[]{});

    private String viewName;
    private PaymentOption[] containPayment;

    PaymentGroup(String viewName, PaymentOption[] containPayment) {
        this.viewName = viewName;
        this.containPayment = containPayment;
    }

    public String getViewName() {
        return viewName;
    }

    public static PaymentGroup findGroup(PaymentOption searchTarget) {
        return Arrays.stream(PaymentGroup.values())
                .filter(group -> hasPaymentOption(group, searchTarget))
                .findAny()
                .orElse(PaymentGroup.EMPTY);
    }

    private static boolean hasPaymentOption(PaymentGroup group, PaymentOption searchTarget) {
        return Arrays.stream(group.containPayment)
                .anyMatch(containPay -> containPay == searchTarget);
    }
}
