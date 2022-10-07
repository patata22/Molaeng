<img src="/uploads/dcdf641946c9583dde15fbf669403e7d/logo.png" width="30%"></img>
# 🍴 모랭(모두의 냉장고)
사용자가 보유한 재료를 기반으로 레시피 검색 기능을 제공하고, 조리해 먹은 레시피 기록과 그에 따른 절약 현황 및 가계부 기능을 제공하는 모바일 웹 서비스

# 💭 기획 배경
최근 1인 가구가 증가하고 있습니다. 통계 자료에 따르면 1인 가구의 주요 지출 중 가장 큰 비중을 차지하는 것은 외식 및 배달비이며, 이는 낮은 식생활 만족도와 영양 불균형을 초래합니다. 뿐만 아니라 조리를 위해 식재료를 구매하더라도 한번에 모두 사용하지 못해 유통기한을 넘기는 일이 부지기수로 발생합니다.

저희는 이러한 문제에서 착안하여 가지고 있는 식재료를 활용해 만들 수 있는 레시피를 추천해 주고, 해당 레시피에 필요한 조리 비용을 계산하여 외식비와 비교해 주는 기능을 담은 서비스를 기획하였습니다.

# 프로젝트 기간
**2022.08.22 ~ 2022.10.07**

# 👪 팀원 소개
|**이름**|**역할**|
|:---:|:---:|
|이나경|팀장, BE, FE, Infra|
|김승준|BE, FE|
|김지건|BE, FE|
|박세원|BE, FE|
|양희경|BE, FE|
|이지영|BE, FE|

# 🍴 핵심 기능

## 1. 검색 기능

### 재료 기반 레시피 검색
재료 키워드를 선택하고, 선택된 재료를 사용하는 레시피를 검색

![재료로검색](/uploads/c2033914cdc1ba1eabac494ac5ea1bfb/재료로검색.gif)

### 레시피 이름으로 검색
자동완성 기능

![레시피이름으로검색](/uploads/f6c0c2ff4a7c9ccaf5304b1a7f348df2/레시피이름으로검색.gif)

## 2. 조리 비용 VS 외식비 가격 비교

상세 페이지의 재료 태그 선택 시, 활성화 된 재료에 따라 조리 비용이 변화

![재료에따라가격변화](/uploads/b27afd72a3c4cd03d8e10af1f939e1b8/재료에따라가격변화.gif)

필요한 식재료 분량에 따라 음식을 조리하는 데 필요한 비용을 계산하고, 똑같은 레시피를 밖에서 사 먹었을 때와 가격을 비교

![조리비외식비비교](/uploads/5198fece4017c3a2fc95ca2ffaf911c8/조리비외식비비교.gif)

## 3. 모랭일기

레시피 상세 페이지에서 조리해 먹은 레시피를 저장

![모랭일기등록](/uploads/dfaa82cf1f20efdc9616f19757e7c5b7/모랭일기등록.gif)

주차별/월별로 절약한 금액 내역을 시각화하여 제공

모랭일기 페이지에서 그동안 등록한 레시피 정보를 캘린더에서 확인 가능

![모랭일기그래프레시피](/uploads/6ab92b901a6b3f54a67d2cb9d1c1ae32/모랭일기그래프레시피.gif)

# 🍴 그 외 기능
- 소셜 로그인(KaKao)
- 레시피 찜/최근 본 레시피 조회 기능

# 📃 기능 명세서
![기능명세서1](/uploads/43a7f441ca5a51616442be1b354c711d/기능명세서1.png)
![기능명세서2](/uploads/8adde40cb6e2ca9225dcb418fcad1611/기능명세서2.png)
![기능명세서3](/uploads/5a02783cbc5b6cc328f998843eee469c/기능명세서3.png)

# 📃 ERD
![ERDiagram](/uploads/15bb510ed197f22f77cac5a9c3af0552/ERDiagram.png)

# 📃 와이어프레임
![와이어프레임1](/uploads/7d141b7e39ae84e6b9c25777ff719756/와이어프레임1.png)
![와이어프레임2](/uploads/910a8577f47ab5717968b22e962515f9/와이어프레임2.png)

# 📃 컴포넌트 구조도
![컴포넌트구조도](/uploads/43c53d988e25a01348a5107e19875d96/컴포넌트구조도.png)

# 💻 개발 환경 및 기술 스택
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">

<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

<img src="https://img.shields.io/badge/Apache Hadoop-66CCFF?style=for-the-badge&logo=ApacheHadoop&logoColor=white">

<br>

<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">

<img src="https://img.shields.io/badge/vuetify-1867C0?style=for-the-badge&logo=Vuetify&logoColor=white">

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">

<br>

<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white">

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">

<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">

### 백엔드
- IDE : IntelliJ 2022.2
- JAVA : 8
- Framework : Spring boot 2.7.4
- Build : Gradle 7.5
- WAS : Tomcat
- DBMS : MySql 8.0
- DB API : JPA

### 프론트
- IDE : VS Code 1.70.1
- Framework :
    - Vue2 5.0.8
    - Vuetify  2.6.0
    - Chart.js 3.9.1

### 데이터 정제
- Hadoop
- Python
- JSoup

### 서버
- AWS
- Docker
- Nginx
- Jenkins

### 협업
- Git
- Jira

# 아키텍처 구조
![아키텍쳐구조도](/uploads/9514988bcc306dba728665bef3493610/아키텍쳐구조도.png)
