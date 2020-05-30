# KAKAOPAY 사전과제

***개발환경***
java8 (1.8.0_171 ver)
Spring 2.3.0
Maven
Mysql (MariaDB)

***문제해결 전략***

<쿠폰생성 규칙>
1) 쿠폰번호는 16자리로 한다
2) [0-9][a-z][A-Z]의 문자만 사용한다.
3) 중복된 쿠폰번호는 발행할 수 없도록 한다.


1. 쿠폰 N개 생성 
쿠폰 생성만 할 수 있도록 생성 할 개수만 받고 별도의 사용자 정보는 받지 않는다.
쿠폰 번호 생성 후 INSERT 할 때 등록일을 별도로 두어 사용자에게 지급된 날과 구분할 수 있도록 한다.

URL : /api/coupon/create
Method : POST


2. 쿠폰 지급 
사용자를 구분할 수 있도록 이메일 주소를 받는다.
이메일 주소 형식이 맞는지 검증한다.
쿠폰 TABLE에서 한 개의 쿠폰을 SELECT 하고
사용자 고유의 값이자 사용,취소 시 본인임을 인증하는 데 사용할 ID 값을 생성한다.
ID 값은 이메일 주소를 쿠폰번호로 encrypt 한 값을 사용한다.
지급일은 당일 날짜, 만료일은 당일 + 한 달 뒤 값을 사용한다.
쿠폰에 대한 정보(이메일, ID, 날짜)를 UPDATE 하고
쿠폰 정보를 RETURN 한다

URL : /api/coupon/provice/{email}
Method : GET



3. 쿠폰 조회 
사용자 고유의 값인 이메일 주소를 받아서 사용한다.
이메일 주소 형식이 맞는지 검증한다.
TABLE에서 해당 값들을 RETURN 해 준다.
SELECT 시 사용하지 않았고, 만료일이 아직 남은 것을 조건으로 한다.

URL : /api/coupon/show/{email}
Method : GET


4. 쿠폰 사용
받은 쿠폰번호로 해당 쿠폰의 정보를 SELECT 한다.
SELECT 시 만료일이 남아 있는지 확인하는 조건을 넣는다.
SELECT 한 데이터 중 EMAIL값과
ID를 쿠폰번호로 decrypt한 값이 같은지 확인한 후 사용 처리(1)를 한다.

URL : /api/coupon/use/{code}
Method : PUT


5. 사용 취소
받은 쿠폰번호로 해당 쿠폰의 정보를 SELECT 한다.
SELECT 시 만료일이 남아 있는지 확인하는 조건을 넣는다.
SELECT 한 데이터 중 EMAIL값과
ID를 쿠폰번호로 decrypt한 값이 같은지 확인한 후 사용 취소(0)를 한다.

URL : /api/coupon/cancel/{code}
Method : PUT


6. 당일 만료된 전체 쿠폰 목록 조회
당일 만료인 데이터 전체를 조회한다.
데이터의 만료날짜와 오늘 날짜가 같은 데이터를 조회 한다.

URL : /api/coupon/expire
Method : GET



***빌드 및 실행***
java -jar kakaopay-0.0.1-SNAPSHOT.jar

***루트 페이지**
http://localhost:8000/
