# 키친포스

## 요구 사항
- [x] 1.domain별 요구사항 정리
- [ ] 2.요구사항을 토대로 테스트코드 작성

### 1.domain별 요구사항 정리
#### 상품
* 상품의 이름과 가격을 입력받아 저장할 수 있다.
    * 상품의 가격은 0 이상이다.
* 전체 상품 목록을 조회할 수 있다.

#### 메뉴그룹
* 메뉴그룹의 이름을 입력받아 저장할 수 있다.
* 전체 메뉴그룹의 목록을 조회할 수 있다.

#### 메뉴
* 메뉴 이름, 가격, 메뉴그룹, 메뉴상품 목록을 입력받아 저장할 수 있다.
  * 가격은 0 이상이다.
  * 메뉴그룹은 존재하는 메뉴그룹만 지정할 수 있다.
  * 가격이 메뉴상품 목록 세부 가격의 합보다 작거나 같아야 한다.
* 전체 메뉴의 목록을 조회할 수 있다.

#### 주문
* 주문테이블, 주문 항목을 입력받아 저장할 수 있다.
  * 주문 항목은 반드시 1개 이상이어야 한다.
  * 주문 항목은 각기 다른 메뉴여야 한다.
  * 주문테이블은 존재하는 주문테이블만 지정할 수 있다.
  * 주문테이블이 주문을 등록할 수 있는 테이블이어야 한다.
* 전체 주문 목록을 조회할 수 있다.
* 주문  ID와 주문 상태를 입력받아 주문의 현재 상태를 변경할 수 있다.
  * 존재하는 주문만 상태를 변경할 수 있다.
  * 완료 상태의 주문이 아니어야 상태를 변경할 수 있다.

#### 테이블
* 손님수, 주문을 등록할 수 있는 테이블 여부를 입력받아 저장할 수 있다.
* 전체 테이블 목록을 조회할 수 있다.
* 테이블 ID와 주문을 등록할 수 있는 테이블 여부를 입력받아 테이블의 주문을 등록할 수 있는 테이블 여부를 변경할 수 있다.
  * 존재하는 테이블만 상태를 변경할 수 있다.
  * 연결된 테이블 그룹이 존재하지않아야 한다.
  * 주문이 상태가 진행 중 (조리 or 식사)이지 않아야 한다.
* 테이블 ID와 손님수를 입력받아 테이블의 손님수를 변경할 수 있다.
  * 손님 수는 0 이상이어야 한다.
  * 존재하는 테이블만 손님수를 변경할 수 있다.
  * 주문을 등록할 수 있는 테이블만 손님수를 변경할 수 있다.

#### 테이블그룹
* 그룹으로 지정될 테이블 목록을 입력받아 테이블 그룹을 저장할 수 있다.
  * 그룹으로 지정되는 테이블은 2개 이상이어야 한다.
  * 그룹으로 지정되는 테이블은 서로 다른 테이블이어야 한다.
  * 주문을 등록할 수 있는 테이블이거나 이미 다른 테이블 그룹에 묶인 테이블은 테이블그룹으로 저장할 수 없다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
