# MiniProject 5조 백엔드  

## 서비스 : https://dashing-tiramisu-cbdade.netlify.app/accommodation/1


## 프로젝트 내용
> - 프로젝트 목적 :  프론트엔드와 협업을 통한 숙박 예약 서비스 완성
> - 프로젝트 기간 : 11/20(월) ~ 12.01(금)

## 기술스택 & 구현환경
> - ### Java : ![Java](https://img.shields.io/badge/java-11-red.svg)
> - ### FrameWork : ![Spring Boot](https://img.shields.io/badge/springboot-3.1.5-brightgreen.svg)  ![Spring Security](https://img.shields.io/badge/springsecurity-brightgreen.svg) ![Spring Data JPA](https://img.shields.io/badge/spring%20data%20JPA-brightgreen.svg)  ![Spring Web](https://img.shields.io/badge/spring%20web-brightgreen.svg)
> - ### Build : ![Gradle](https://img.shields.io/badge/Build-Gradle-blue.svg)
> - ### VCS : ![Git](https://img.shields.io/badge/VCS-Git-orange.svg) ![GitHub](https://img.shields.io/badge/Github-black.svg)
> - ### Database : ![GCP Cloud SQL](https://img.shields.io/badge/Database-GCP%20Cloud%20SQL-yellow.svg)
> - ### DBMS : ![MySQL](https://img.shields.io/badge/DBMS-MySQL-blue.svg)
> - ### 배포환경 : ![GCP VM](https://img.shields.io/badge/배포%20환경-GCP%20VM%20ubuntu%2020-blue.svg)

## 컨벤션
> - ![Code Convention](https://img.shields.io/badge/Code%20Convention-IntelliJ%20Java%20Google%20Style-brightgreen.svg)
> - ![GitFlow](https://img.shields.io/badge/GitFlow-Workflow-orange.svg)

## 패키지 구조
 com.example.yanolja  
 ├── domain  
 │   ├── user  
 │   ├── accommodation  
 │   ├── review  
 │   ├── reservation  
 │   ├── accommodationLikes  
 │   ├── basket  
 │　├── wishlist  
 │   ...  
 └── global  
    ├── springsecurity  
    ├── entity  
    ├── config  
    ├── exception  
    ├── jwt  
    └── util  


## API명세 / 설계  
# [링크](https://documenter.getpostman.com/view/14269013/2s9YeBfu36#f3b52c96-ce6c-42fb-b1e5-cc8c93017f97)  

## ERD  
- ![ERD](https://www.notion.so/11-30-README-1ba40cb04c374d259b29bd6fde644f3e?pvs=4#5ebefac76675428e891820abe31e5fcc)  
- ![ERD}(https://www.notion.so/11-30-README-1ba40cb04c374d259b29bd6fde644f3e?pvs=4#0b411bf5515b4b84bce918b6cb143b8d)  

## Persistence View
![PV](https://www.notion.so/11-30-README-1ba40cb04c374d259b29bd6fde644f3e?pvs=4#e6f290ef662f48cbb6fdd734095a6ead)  

## FlowChart
![FC](https://www.notion.so/11-30-README-1ba40cb04c374d259b29bd6fde644f3e?pvs=4#1cd24f6d24544e289deded535b739540)  

  
  

## 세부기능 구현

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
