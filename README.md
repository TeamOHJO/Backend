# MiniProject 5조 백엔드  

## 📌서비스 : https://dashing-tiramisu-cbdade.netlify.app


## 📌프로젝트 내용
> - 프로젝트 목적 :  프론트엔드와 협업을 통한 숙박 예약 서비스 완성
> - 프로젝트 기간 : 11/20(월) ~ 12.01(금)

## 📌기술스택 & 구현환경
> - ### Java : ![Java](https://img.shields.io/badge/java-17-red.svg)
> - ### FrameWork : ![Spring Boot](https://img.shields.io/badge/springboot-3.1.5-brightgreen.svg)  ![Spring Security](https://img.shields.io/badge/springsecurity-brightgreen.svg) ![Spring Data JPA](https://img.shields.io/badge/spring%20data%20JPA-brightgreen.svg)  ![Spring Web](https://img.shields.io/badge/spring%20web-brightgreen.svg)
> - ### Build : ![Gradle](https://img.shields.io/badge/Build-Gradle-blue.svg)
> - ### VCS : ![Git](https://img.shields.io/badge/VCS-Git-orange.svg) ![GitHub](https://img.shields.io/badge/Github-black.svg)
> - ### Database : ![GCP Cloud SQL](https://img.shields.io/badge/Database-GCP%20Cloud%20SQL-yellow.svg)
> - ### DBMS : ![MySQL](https://img.shields.io/badge/DBMS-MySQL-blue.svg)
> - ### 배포환경 : ![GCP VM](https://img.shields.io/badge/배포%20환경-GCP%20VM%20ubuntu%2020-blue.svg)

## 📌실행환경 설정 방법
- **`.env` 파일을 만들어서 최상단 디렉토리에 위치하여야 합니다.**
- env 파일은 아래의 형식으로 만들어야 합니다.
```properties
JWT_SECRET_KEY1=key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1
JWT_SECRET_KEY2=key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2
JWT_SECRET_KEY3=key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3
TEST_ID=id
TEST_ID_PASSWORD=password
TEST_ID_EMAIL=email
```

- **docker 실행 후 docker-compose.yml을 실행해 데이터베이스 환경을 만들어야 합니다.**


## 📌컨벤션
> - ![Code Convention](https://img.shields.io/badge/Code%20Convention-IntelliJ%20Java%20Google%20Style-brightgreen.svg)
> - ![GitFlow](https://img.shields.io/badge/GitFlow-Workflow-orange.svg)

## 📌패키지 구조
 com.example.yanolja  
 ├── domain  
 │   ├── user  
 │   ├── accommodation  
 │   ├── review  
 │   ├── reservation  
 │   ├── accommodationLikes  
 │   ├── basket  
 │   ├── wishlist  
 │   ...  
 └── global  
   │  ├── springsecurity  
   │  ├── entity  
   │  ├── config  
   │  ├── exception  
   │  ├── jwt  
   │  └── util  


## 📌API명세 / 설계  
# [링크](https://documenter.getpostman.com/view/14269013/2s9YeBfu36#f3b52c96-ce6c-42fb-b1e5-cc8c93017f97)  

## 📌ERD  
![accommodation](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/65541248/16f75b06-04e1-4def-ad51-219ff9ac9f20)

![YBE_20231130_174414](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/65541248/e64a81db-6bcf-4aac-92a3-d49fbd59d270)

## 📌Persistence View  
![persistence view](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/65541248/20759a28-3aec-48cf-9c52-66e6cfffa346)

## 📌FlowChart  
![flow chart](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/65541248/0903d4ea-37d1-4df7-ac62-7293a628ba39)


  
  

## 📌세부기능 구현

### 1. 회원인증
- 회원가입
    - 이메일 인증 / 이메일 중복확인 / 정보 유효성검사
- 로그인 / 로그아웃

### 2. 좋아요, 리뷰
- 좋아요 토글
- 리뷰 CRUD
- 리뷰 상세조회

### 3. 메인페이지
- 상품 조회
    - 전체 / 개별 상품 조회
- 원하는 숙소 검색
- 로그인 전, 후 UI 변동
- 숙박업체 카테고리별 조회

### 4. 마이페이지
- 예약 내역
    - 예약 취소
- 내 리뷰
    - 리뷰 내역 / 리뷰 상세내용 조회
- 내 정보 관리
    - 개인정보 변경 / 탈퇴기

### 5. 상세페이지
- 상품 옵션 선택
- 상품 예약 / 장바구니에 추가

### 6. 장바구니
- 담기 / 보기
    - 예약 가능, 예약 불가능 숙소 조회 / 삭제
- 주문하기
- 결제하기
- 주문 결과 확인

### 7. 위시리스트
- 좋아요 누른 숙소들을 리스트로 담아 조회 / 좋아요 토글
