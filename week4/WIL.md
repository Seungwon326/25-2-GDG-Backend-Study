# 4주차 - ERD, DB, Entity

- ERD(Entity-Relationship Diagram) - 데이터 청사진
    - Entity: 데이터를 가진 대상
        - ex. 회원, 상품, 주문 내역은 어떤 속성, 데이터를 가지고 있는지
    - Relation: 객체 사이의 연관성
        - ex. 어떤 회원이 어떤 상품을 주문했는지
- Entitiy: 관리해야 할 데이터의 주체
    - ex. 회원, 상품, 주문
    - 자바와 DB가 소통하는 단위
    - 엔티티 클래스를 정의 → JPA: 엔티티 클래스 정의를 참고하여 테이블 생성 SQL문을 작성하고 실행
        - JPA는 SQL 작성 시간을 단축시켜줌
- 속성(=필드, 칼럼): 각 엔티티가 가지는 구체적 정보
    - ex. member: id, name, address
- 기본 키(PK): 고유하게 식별하는 데 사용되는 하나 이상의 컬럼
    - ex. member_id, product_id
- 외래 키(FK): 다른 테이블의 PK를 참조하는 속성(테이블 간 연결고리)
    - ex. Order 테이블 안 member_id
- 관계: 개체(entity) 사이의 연관성, 업무 규칙
    - 테이블 또는 외래 키로 구현
- 1:N - 1명의 회원은 여러 개의 주문 내역을 가진다.(member와 order은 1:N 관계)
    - Order 테이블은 member_id를 FK로 가짐
    
    ⇒ 외래 키로 관계를 구현한다.
    
- N:N - 한 명의 학생이 여러 강의를 들을 수 있고, 한 강의를 여러 학생이 들을 수 있음
    - 외래 키로 구현 X
    - 해결책: 중간 테이블(연결 엔티티) 도입
        - 수강 신청 테이블을 새로 구현하고 학생과 수업을 외래 키로 설정하여 관계를 구현
            
- ERD
    - 식별 관계 강한 연관 관계
        - 관계 대상의 PK를 자신의 PK로도 사용
    - 비식별 관계: 느슨한 연관 관계(일반적)
        - 관계 대상의 PK를 자신의 FK로만 사용
- 엔티티 구현
    1. 엔티티 클래스
        - @Entity, @Id 어노테이션 필요
        - Id(고유 식별자) 값을 자동 생성: @GeneratedValue 사용 → 키 값 결정을 DB에게 위임
        - @Column으로 컬럼 명, 컬럼 타입 등을 지정
    2. 외래 키(FK)
        - 엔티티 객체를 필드로 넣은 후 @JoinColumn, @ManyToOne 사용
            - @JoinColumn: FK 컬럼 정보를 명시(name 등)
            - @ManyToOne, @OneToOne, @OneToMany, @ManyToMany: 해당 외래키로 생기는 연관 관계 종류를 나타내는 어노테이션
                - @ManyToOne에서 fetch 속성은 LAZY로 지정
        - 엔티티 객체를 필드로 지정 → ORM(JPA)가 외래키로 알아서 처리
    3. 엔티티 생성자
        - JPA가 엔티티를 사용하려면 인자 없는 생성자 필요 → @NoArgsConstructor
        - access 속성을 통해 접근 제한자를 protected로 설정 → JPA는 사용 가능, 외부 사용 차단
        - 엔티티 객체에 @Getter 추가 → 모든 필드에 getter 생성
