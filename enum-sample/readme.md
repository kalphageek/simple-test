## Code테이블 대체
> Entity 클래스에 선언한 @Enumerated(javax.persistence.EnumType.STRING)을 entity의 필드에 선언하시면, 해당 enum 타입의 name이 DB에 저장됩니다.
(여기서는 WOOWA_SISTERS, WOOWA_CHILDREN 등이 DB에 저장되며, DB에서 조회하면 Affiliate.Code.WOOWA_SISTERS와 Affiliate.Code.WOOWA_CHILDREN으로 값이 할당됩니다.)
## 코드별 function 직접 적용
> 어느 코드에서든 특정 금액에 대해 타입별 계산금이 어떻게 되는지는 이제 그 타입에 직접 물어보면 된다.
## Enum Grouping
* 추가하는 경우, DB에도 추가 필요.
1. PaymentGroup은 PaymentOption의 배열을 갖고 있다.
2. 특정 PaymentOption이 있을때 이 값이 어느 그룹에 포함될지는 PaymentGroup에 직접 물어보면(findGroup) 된다.
3. select box로 그룹 리스트를 출력해야하는 경우엔 PaymentGroup.values()을 사용.