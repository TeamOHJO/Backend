### 👤팀원 소개
|Backend Lead|Backend|Backend|Backend|
|:--------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------:|
|<img src="https://avatars.githubusercontent.com/u/105612931?v=4" width=130px alt="최혜미">| <img src="https://avatars.githubusercontent.com/u/65541248?v=4" width=130px alt="이유상"/> | <img src="https://avatars.githubusercontent.com/u/64956292?v=4" width=130px alt="백인권"/> | <img src="https://avatars.githubusercontent.com/u/137012201?v=4" width=130px alt="안수지"/> | 
|[최혜미](https://github.com/ghrltjdtprbs)|[이유상](https://github.com/liyusang1)|[백인권](https://github.com/BackInGone)|[안수지](https://github.com/deltawing71911)|
|💡회원가입, 탈퇴<br/>💡이메일 인증  <br/>💡상세 페이지 조회<br/>💡사용자 리뷰 조회<br/>💡사용자 정보 수정|💡spring security<br/>💡프로젝트 배포<br/>💡장바구니, 결제<br/>💡사용자 예약조회<br/> 💡로그인(OAuth2)<br/>💡Querydsl|💡메인페이지 조회<br/>💡오픈api 활용 |💡리뷰 crud 기능<br/> 💡좋아요 등록, 취소<br/>💡위시 리스트| 

-----------------------
### 📌기술스택 & 구현환경
> -  Java : ![Java](https://img.shields.io/badge/java-17-red.svg)
> -  FrameWork : ![Spring Boot](https://img.shields.io/badge/springboot-3.1.5-brightgreen.svg)  ![Spring Security](https://img.shields.io/badge/springsecurity-brightgreen.svg) ![Spring Data JPA](https://img.shields.io/badge/spring%20data%20JPA-brightgreen.svg)  ![Spring Web](https://img.shields.io/badge/spring%20web-brightgreen.svg)
> -  Build : ![Gradle](https://img.shields.io/badge/Build-Gradle-blue.svg)
> -  VCS : ![Git](https://img.shields.io/badge/VCS-Git-orange.svg) ![GitHub](https://img.shields.io/badge/Github-black.svg)
> -  Database : ![GCP Cloud SQL](https://img.shields.io/badge/Database-GCP%20Cloud%20SQL-yellow.svg)
> -  DBMS : ![MySQL](https://img.shields.io/badge/DBMS-MySQL-blue.svg)
> -  배포환경 : ![GCP VM](https://img.shields.io/badge/배포%20환경-GCP%20VM%20ubuntu%2020-blue.svg)
> -  컨벤션 : ![Code Convention](https://img.shields.io/badge/Code%20Convention-IntelliJ%20Java%20Google%20Style-brightgreen.svg)
> -  브랜치 전략 : ![GitFlow](https://img.shields.io/badge/GitFlow-Workflow-orange.svg)

### 📌프로젝트 내용
> - 프로젝트 목적 :  프론트엔드와 협업을 통한 숙박 예약 서비스 완성
> - 프로젝트 기간 : 11/20(월) ~ 12.01(금)
> - 서비스 : https://dashing-tiramisu-cbdade.netlify.app
> - API 명세서 : https://documenter.getpostman.com/view/14269013/2s9YeBfu36#f3b52c96-ce6c-42fb-b1e5-cc8c93017f97



### 📌실행환경 설정 방법
> - **`.env` 파일을 만들어서 최상단 디렉토리에 위치하여야 합니다.**
> - env 파일은 아래의 형식으로 만들어야 합니다.
> ```properties
> JWT_SECRET_KEY1=key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1key1
> JWT_SECRET_KEY2=key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2key2
> JWT_SECRET_KEY3=key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3key3
> TEST_ID=id
> TEST_ID_PASSWORD=password
> TEST_ID_EMAIL=email
> ```
> - docker 실행 후 docker-compose.yml을 실행해 데이터베이스 환경을 만들어야 합니다.


### 📌패키지 구조
```
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
      ├── springsecurity  
      ├── entity  
      ├── config  
      ├── exception  
      ├── jwt  
      └── util  
```

--------------------

### ⭐ERD  
![image](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/105612931/f907dc34-eef5-4ad1-8af4-80b844b0d679)

### ⭐FlowChart  
![flow chart](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/65541248/0903d4ea-37d1-4df7-ac62-7293a628ba39)

### ⭐Project Architecture
![image](https://github.com/TeamOHJO/yanoljaProject-Backend/assets/105612931/a30897c7-1436-434b-a6a8-348f3596eb3b)


-------------------
  

### 📗세부기능 구현

**1. 회원인증**
> - 회원가입
>     - 이메일 인증 / 이메일 중복확인 / 정보 유효성검사 / 탈퇴 계정 복구
> - 회원탈퇴
> - 로그인 / 로그아웃
> - 소셜 로그인(네이버, 구글)

**2. 좋아요, 리뷰**
> - 좋아요 등록, 취소
> - 리뷰 CRUD
> - 리뷰 상세조회

**3. 메인페이지**
> - 숙소 조회
>     - 전체 / 개별 숙소 조회
> - 숙소 검색
> - 로그인 전, 후 UI 변동
> - 숙박업체 카테고리별 필터링

**4. 마이페이지**
> - 예약 내역
>     - 예약 취소
>     - 내 예약 조회
> - 내 리뷰
>     - 내 리뷰 조
> - 내 정보 관리
>     - 개인정보(닉네임, 전화번호, 비밀번호) 변경

**5. 상세페이지**
> - 숙소 상세 조회/ 방 상세 조회
> - 인원, 날짜로 예약 가능 숙소 필터링(품절 여부 표시)
> - 숙소 옵션(인원, 체류 날짜) 선택
> - 상품 예약 or 장바구니에 추가

**6. 장바구니**
> - 담기 / 보기
>     - 예약 가능, 예약 불가능 숙소 조회 / 삭제
> - 주문하기
> - 결제하기
> - 주문 결과 확인

**7. 위시리스트**
> - 사용자가 좋아요 등록한 숙소 조회
> - 좋아요 등록, 취소


-----------------
### 🛠️리팩토링
- 전체적인 코드 리팩토링(코드리뷰 반영)
- OAuth2 추가(구글, 네이버)
- 메인페이지 업체 조회시 QueryDSL 적용
- 기존 주석 Javadoc으로 교체
- 레디스 적용
-------------------
### 😊개인 역량 회고

<details>
<summary>최혜미</summary>
<div markdown="1">
짧은 기간 동안 빠르게 개발하려다 보니 마음이 조급했던 것 같습니다. <br/>
그러다 보니 작동하는 코드를 작성하는 데 급급해서 리팩토링을 진행할 때 매우 힘들었습니다.<br/>
이 경험으로 프로젝트 설계를 철저하게 해야한다는 것을 깨닫게 되었습니다. 좋은 팀원들과 함께해서 실력이 많이 늘었어요!😊
</div>
</details>

<details>
<summary>이유상</summary>
<div markdown="1">

❗ 짧은 기간동안 빠르게 기능구현을 위주로 개발을 하려고 하다보니 다시 돌아와서 코드를 보았을때 로직이 복잡하고 좋지 않은 코드들이 많이 보였던거 같습니다. 다음에 비지니스 로직을 구현할때 있어서 좀 더 생각을 하고 좋은 설계를 바탕으로 코드를 짜면 더 좋을거 같습니다. 이번경험을 바탕으로 코드작성에 있어서 더 생각을 해 볼 수 있을거 같습니다. 좋은 경험이었습니다.<br/><br/>
❗ 도커 위에서 작업을 하고, 도커이미지를 통해 배포를 해보았는데 이 경험이 매우 좋았습니다. 도커이미지로 배포를 했기때문에 배포환경에 구애받지 않고 필요한 프로그램들 설치 없이 언제 어디서든 똑같은 배포환경을 유지할 수 있다는 점이 매우 편리했습니다.<br/>
이 과정은 기존 jar로 배포하던것보다 훨씬 간편했던거 같고 유용한 기술이었습니다.<br/>
Docker에 대해 좀 더 공부해보고 더 다양한 기술들을 써보고 싶은 계기가 되었던거 같습니다.<br/><br/>
❗ querydsl과 oauth를 추가적으로 적용해 볼 수 있어서 좋았고 이 기술들을 사용해볼 수 있어서 좋았던 것 같습니다 redis를 사용해 캐시db를 활용하는 것도 해 볼 예정입니다.<br/><br/>
❗ 처음 써보는 새로운 기술들을 적용하면서 여러가지 문제점이 있었지만 팀원들과 같이 의논하고 같이 개발하면서 문제를 해결했었던것 같습니다. 덕분에 실력이 많이 늘어서 좋았습니다.

</div>
</details>

<details>
<summary>백인권</summary>
<div markdown="1">

??

</div>
</details>

<details>
<summary>안수지</summary>
<div markdown="1">
❗ 비즈니스 로직을 구현하는데 아직 시간이 많이 소요되는 부분이 가장 어려웠으며 많은 연습이 필요하다는 조언을 받았습니다. <br/>
그리고 전반적인 프로젝트 구조를 이해하는데 어려움이 많았으며 이 점은 팀원들과의 소통을 통해 어느정도 파악하는 데에는 성공하였으나 스스로 혼자서 구현하는 데에는 노력이 필요할 것 같다는 생각이 들었습니다. <br/><br/>
❗ 팀 프로젝트에 많은 부분을 기여하지는 못했지만, 웹 백엔드 개발의 프로세스에 대해 지식과 경험들을 쌓을 수 있었던 아주 좋은 기회였다고 생각합니다.<br/>
다른 팀원들의 코드를 분석하고 리뷰를 하는 연습이 많이 필요할 것 같습니다. 사실 시간이 부족해 바로 팀원들의 PR을 Approve했었는데 차후 다른 프로젝트를 진행할 때엔 꼼꼼히 리뷰를 할 생각입니다.

</div>
</details>
