## Enum Grouping
* 추가하는 경우, DB에도 추가 필요.
1. PaymentGroup은 PaymentOption의 배열을 갖고 있다.
2. 특정 PaymentOption이 있을때 이 값이 어느 그룹에 포함될지는 PaymentGroup에 직접 물어보면(findGroup) 된다.
3. select box로 그룹 리스트를 출력해야하는 경우엔 PaymentGroup.values()을 사용.