# 간단한 결제를 위한 API 서버

---

> Spring Boot를 공부하며 연습하기 위해 진행하는 프로젝트입니다. 

## 기술스택

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![spring](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![spring-security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![js](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![js](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![js](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

---

## 서비스 소개
```
결제 시스템에서 사용될 통신사와 통신을 하는 gateway api를 간단한 기능으로 구현
실제 통신사와 통신을 진행하진 못하며, Rest Api로 들어온 요청은 성공으로 응답
```
### 📖 API 문서

프로젝트의 API 문서는 [여기](https://223yellow332.github.io/simpleGw/index.html)에서 확인할 수 있습니다.

---

### 📝 기능 정의서
1. 인증
   * 신규 결제 거래번호 생성
   * 고객 사용가능 한도금액 응답
2. 결제
   * 결제 거래번호 확인
   * 결제 성공 응답
3. 취소
   * 결제 거래번호 확인
   * 결제 취소 성공 응답

---

### 📦 파일구조

```markdown
📁 src
└─ 📁 main
   └─ 📁 java.com.calmdown
      └─ 📁 simpleGw
         ├─ 📁 aop
         ├─ 📁 controller
         ├─ 📁 domain
         ├─ 📁 dto
         ├─ 📁 exception
         ├─ 📁 security
         ├─ 📁 service
         └─ 📁 util

```

---

### ⚙️ 개발 환경  
Java 11  
Spring Boot 2.7.17  
gradle 8  
Spring security  
JPA  
H2 Databse  
IntelliJ  
Postman  
Rest Docs  


