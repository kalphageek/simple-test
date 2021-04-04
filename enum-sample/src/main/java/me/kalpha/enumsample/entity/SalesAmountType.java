package me.kalpha.enumsample.entity;

import java.util.function.Function;

public enum SalesAmountType {
    ORIGIN_AMT("원금액", amount -> amount),
    SUPPLY_AMT("공급가액", amount -> Math.round(amount.doubleValue() * 10 / 11)),
    VAT_AMT("부가세", amount -> Math.round(amount.doubleValue() / 11)),
    NOT_USED("사용안함", amount -> 0L);

    private String viewName;
    // 1개의 파라미터와 리턴값을 갖는 Lambda function 타입 선언
    private Function<Long, Long> expression;

    SalesAmountType(String viewName, Function<Long, Long> expression) {
        this.viewName = viewName;
        this.expression = expression;
    }

    public long calculate(long amount) {
        return expression.apply(amount);
    }

    public String getViewName() {
        return this.viewName;
    }
}
