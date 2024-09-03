# 목차
- [개요](#개요)
- [서비스 설명](#서비스-설명)
  * [사용자 페이지](#사용자-페이지-)
  * [리뷰어 페이지](#리뷰어-페이지-)
- [요구사항](#요구사항)
  * [사용자 인터페이스 및 기능 요구사항](#사용자-인터페이스-및-기능-요구사항-)
  * [리뷰어 시스템 요구사항](#리뷰어-시스템-요구사항-)
  * [시스템 아키텍처 및 운영 요구사항](#시스템-아키텍처-및-운영-요구사항-)
- [개발 환경](#개발-환경)
- [사용 기술](#사용-기술)
  * [백엔드](#백엔드)
  * [프론트엔드](#프론트엔드)
- [시스템 아키텍처](#시스템-아키텍처)
- [ER 다이어그램](#er-다이어그램)
- [패키징 구조 (DDD 아키텍처)](#패키징-구조ddd-아키텍처)
- [핵심 기능](#핵심-기능)
  * [세션/쿠키 기반의 로그인 시스템](#세션쿠키-기반의-로그인-시스템-)
  * [전략 패턴과 팩토리 패턴을 활용한 상태 변경](#전략-패턴과-팩토리-패턴을-활용한-상태-변경-)
  * [Sentry 모니터링](#sentry-모니터링-)
  * [에러 로그 관리](#에러-로그-관리-)
  * [Nginx 다중 도메인 서버 설정](#nginx-다중-도메인-서버-설정-)
  * [Nginx와 헬스체크를 활용한 무중단 배포](#nginx와-헬스체크를-활용한-무중단-배포-)
- [문제 해결](#문제-해결)
  * [AWS 프리티어 EC2 인스턴스의 높은 자원 사용률로 인한 서비스 중단 문제](#aws-프리티어-ec2-인스턴스의-높은-자원-사용률로-인한-서비스-중단-문제-%EF%B8%8F)
  * [메모리가 부족한 프리티어 환경에서 원활한 운영을 위해 Swap으로 가상 메모리 설정](#메모리가-부족한-프리티어-환경에서-원활한-운영을-위해-swap으로-가상-메모리-설정-%EF%B8%8F)
  * [이력서 파일 업로드 시 사용자 1초 딜레이](#이력서-파일-업로드-시-사용자-1초-딜레이-%EF%B8%8F)
  * [환경별 파일 업로드 서비스 분기 처리 문제](#환경별-파일-업로드-서비스-분기-처리-문제-%EF%B8%8F)
- [아쉬운 점](#아쉬운-점)
  * [Sentry 모니터링 툴의 한계](#Sentry-모니터링-툴의-한계-)

# 개요 
이 프로젝트는 신입 및 주니어 개발자들이 자신의 이력서를 검토받고 피드백을 받을 수 있는 무료 플랫폼을 제공하는 것을 목적으로 합니다. 

현재 대부분의 개발자들이 이력서 검토를 받기 위해서는 유료 멘토링이나 피드백 서비스를 이용해야 하는데, 

이러한 서비스는 비용적인 부담이 될 수 있어, 특히 경력이 적은 개발자들에게는 접근이 어렵습니다.

이에 따라, 이 프로젝트는 신입 및 주니어 개발자들이 무료로 이력서를 검토받고 전문적인 피드백을 받을 수 있는 시스템을 개발하게 되었습니다.

## 서비스 설명 
사이트 링크 🔗 : [https://seolyu.com](https://seolyu.com)

### 사용자 페이지 🧑‍🎓

![main](https://github.com/coorr/images/blob/main/seolyu/main.png?raw=true)
- 메인 페이지입니다. 
- 샘플 이력서이지만 실제 이력서와 비슷하게 하여 사용자가 관심이 갈 수 있도록 하였습니다.

<br/>

![request](https://github.com/coorr/images/blob/main/seolyu/request.png?raw=true)
- 사용자가 이력서를 업로드할 수 있는 신청 페이지입니다.
- "**이름, 이메일, 포지션, 이력서 파일, 이력서 URL, 요청사항**" 입력을 받습니다. 

<br/>

![success](https://github.com/coorr/images/blob/main/seolyu/success_mail.png?raw=true)
- 왼쪽 이미지는 정상적으로 서류 검토 신청이 완료됨을 알려주는 페이지입니다.
- 오른쪽 이미지는 **이메일로** 신청이 완료되었다는 내용과 검토 결과 기간이 며칠 걸리는지 알려줍니다.

<br/>

![email](https://github.com/coorr/images/blob/main/seolyu/status_complete_cancel.png?raw=true)
- 왼쪽 이미지는 **이메일로** 서류 검토 완료 안내를 합니다. 서류 검토 리포팅은 URL 링크를 통해 확인할 수 있습니다.
- 오른쪽 이미지는 **이메일로** 서류 검토 취소 안내를 합니다.

<br/>

![report](https://github.com/coorr/images/blob/main/seolyu/report.png?raw=true)
- 서류 검토 리포팅 페이지입니다.
- 개개인에게 맞춤형으로 보완 및 어필 포인트를 제안해 드립니다.

<br/>

### 리뷰어 페이지 🧑‍🎓
![login](https://github.com/coorr/images/blob/main/seolyu/login.png?raw=true)
- 로그인 페이지입니다.
- 리뷰어는 관리자가 발급한 아이디와 패스워드를 입력하여 로그인합니다. 

<br/>

![applicant_list](https://github.com/coorr/images/blob/main/seolyu/applicant_list.png?raw=true)
- 신청자 목록 페이지입니다.
- 신청자 정보를 확인할 수 있으며, 상태를 "**대기, 완료, 취소**" 변경할 수 있습니다.

<br/>

![reviewer](https://github.com/coorr/images/blob/main/seolyu/reviewer.png?raw=true)
- 이력서 리뷰 페이지입니다.
- 리뷰어는 신청자의 이력서를 확인할 수 있으며, 리뷰를 작성할 수 있습니다.
- 리뷰 순서는 "**요약, I. 피드백, II. 피드백, III. 피드백, IV. 피드백, 최종 의견**" 순으로 구성되어 있습니다.

<br/>

# 요구사항
### 사용자 인터페이스 및 기능 요구사항 📕
- 메인 페이지 
  - 심플하고 매력적인 디자인을 통해 **사용자에게 호감을 줄 수 있어야 한다.**
  - 샘플 이력서는 실제 이력서와 유사하게 구성하되, 회사명이나 개인정보는 포함하지 않는다.
  - 화면 크기가 850px에서 작아질 때 샘플 이력서 사진은 **미디어 쿼리를** 통해 자동으로 제거된다.
- 이력서 신청 페이지
  - 사용자로부터 **"이름, 이메일, 포지션, 이력서 파일, 이력서 URL, 요청사항"** 을 입력받는다.
  - 이메일 및 이력서 URL은 정규식을 사용해 유효성을 검사한다.
  - 이력서 파일은 PDF 형식만 허용하고, 파일 크기는 최대 20MB로 제한한다.
  - 포지션은 ENUM으로 관리하여 지정된 값만 입력할 수 있으며, 사용자에게는 선택 창을 제공하여 잘못된 값이 입력되지 않도록 한다.
  - 신청 대기 중인 이메일이 존재할 경우 신청을 할 수 없다.
  - 신청이 완료되면 확인 페이지로 이동하고, 이메일을 통해 신청 완료 및 검토 예상 기간을 안내한다.

### 리뷰어 시스템 요구사항 📗
- 리뷰어 로그인
  - 리뷰어는 관리자가 발급한 아이디와 패스워드를 사용하여 로그인할 수 있다.
  - 패스워드는 SHA256 해시 알고리즘, 단방향으로 저장한다.
  - 유효하지 않은 사용자가 리뷰어 페이지에 접근할 경우, 404 Not Found 페이지로 이동한다.
- 이력서 검토 및 상태 변경
  - 리뷰어가 이력서 검토를 완료하고 신청자의 상태를 "대기"에서 "완료"로 변경할 때, 리뷰 테이블에 "요약, 초기 피드백, 최종 의견"이 모두 입력되어 있어야 한다. 그렇지 않으면 상태를 "완료"로 변경할 수 없다.
  - 리뷰어가 신청자의 상태를 "완료" 또는 "취소"로 변경할 때, 자동으로 이메일 회신을 통해 결과를 안내한다.
  - 리뷰어가 신청자의 상태가 "완료" 또는 "취소"에서 "대기"로 변경할 수 없다.


### 시스템 아키텍처 및 운영 요구사항 📙
- 파일 저장
  - 개발 환경과 운영 환경을 분리하여 파일을 관리한다.
  - 개발 환경에서는 NAS에, 운영 환경에서는 AWS S3에 파일을 저장한다.
- 로그인 및 세션 관리
  - 로그인은 세션/쿠키 방식을 사용하며, 세션 정보는 Redis에 저장한다.
  - 세션의 TTL(Time To Live)은 24시간으로 설정한다.
- 서버 구성
  - AWS 프리티어 EC2 한 대에서 프론트엔드와 백엔드 서버를 함께 실행한다.
  - Nginx를 사용하여 다중 도메인 설정을 처리한다.
  - SSL 설정은 무료 인증 기관(CA)인 Let’s Encrypt를 사용하여 적용한다.
- 모니터링 및 에러 관리
  - 운영 서비스에서 예상치 못한 에러가 발생할 경우, 모니터링 도구를 사용하여 에러를 실시간으로 추적한다.
  - Stack Trace를 포함하여 에러 발생 원인을 상세히 기록하고 관리자가 신속히 대응할 수 있도록 한다.
- 무중단 배포
  - 서비스는 24시간 365일 무중단으로 운영되어야 한다.
  - 배포 과정에서 서비스 중단이나 사용자 경험에 영향을 주지 않도록 한다.
  - 비용이 발생하지 않는 무료 도구 및 서비스를 사용하여 무중단 배포를 구현한다.


<br />

# 개발 환경
- IntelliJ
- Visual Studio Code
- Postman
- GitHub
- SourceTree
- DataGrip
- Medis

<br />

# 사용 기술
### 백엔드
#### 주요 프레임워크 / 라이브러리
- Java 17 openjdk
- SpringBoot 3.3.2
- Spring Data JPA
- QueryDsl
- Sentry

#### Build tool
- Gradle

#### Database
- Mysql

#### Infra
- AWS 
  - EC2
  - RDS
  - S3
  - Route53
  - IAM
  - CodeDeploy
- Redis
- Github
- Gmail

<br/>

### 프론트엔드
- React, Redux(React-redux), Bootstrap
- 프론트 링크 🔗 : https://github.com/coorr/seolyu-www

<br />
<br />

# 시스템 아키텍처
![architecture](https://github.com/coorr/images/blob/main/seolyu/architecture.png?raw=true)


<br />

# ER 다이어그램
![erd](https://github.com/coorr/images/blob/main/seolyu/erd.png?raw=true)

<br />

# 패키징 구조(DDD 아키텍처)
<img src="https://github.com/coorr/images/blob/main/seolyu/package.png?raw=true" alt="package" width="300"/>


<br />
<br />

# 핵심 기능
### 세션/쿠키 기반의 로그인 시스템 ✅
로그인 시스템을 구현하면서 직면한 가장 큰 도전은 다양한 클라이언트 환경에서의 인증 관리와 보안, 성능을 동시에 만족시키는 것이었습니다. 

우선, PC와 모바일 디바이스에서의 로그인 상태를 관리해야 하는 문제가 있었습니다. 

문제를 해결하기 위해 PC에서는 UUID를 기반으로 하는 고유한 세션 토큰을 생성하고, 

모바일에서는 device ID를 직접 사용하여 토큰을 생성하고 쿠키에 저장했습니다. 

이렇게 생성된 토큰을 SHA-256으로 해싱하여 보안성을 강화했습니다. 

해싱된 토큰은 Redis에 저장되며, 외부에서 변조되는 것을 방지했습니다.

RDB 보다 상대적으로 Redis가 빠른 조회 성능을 제공하므로 선택하였습니다.

로그인 처리 코드는 다음과 같은 흐름으로 작동합니다:
1. 로그인 요청
   1. 사용자가 로그인하면, PC 환경에서는 UUID 기반으로 토큰을 생성합니다. 
   2. 모바일 환경에서는 device ID 기반으로 토큰을 생성합니다.
   3. 토큰은 SHA-256으로 해싱되어 Redis에 저장됩니다.
3. 세션 검증
   1. 사용자가 API를 호출할 때, Interceptor는 요청의 HTTP 헤더에서 쿠키를 추출하고 쿠키를 통해 Redis에서 해당 세션을 조회하여 유효성을 검사합니다.
   2. 이 과정에서 세션이 유효한 경우에만 API 접근을 허용합니다.

<br />
<br />

### 전략 패턴과 팩토리 패턴을 활용한 상태 변경 ✅
신청자의 상태가 '대기', '취소', '완료' 등으로 변화할 때마다 실행되는 로직이 달랐습니다. 

초기 설계에서는 상태를 enum으로 정의하고, enum 내부에 상태별 비즈니스 로직을 포함시키는 방식으로 접근했습니다. 

그러나 이 접근법에는 몇 가지 문제점이 있었습니다,
1. 의존성 주입(DI)을 사용할 수 없기 때문에, 필요한 다른 클래스를 매개변수로 받아서 처리해야 했습니다. 이로 인해 코드가 복잡해졌습니다.
2. 상태별로 다양한 작업을 수행하다 보니, 단일 책임 원칙(SRP) 지키지 못하는 문제점이 있었습니다.
3. 상태가 추가될 때마다 enum 클래스가 계속 커지면서, 코드 유지보수가 힘들어졌습니다.

이 문제를 해결하기 위해, 전략 패턴과 팩토리 패턴을 도입했습니다. 

전략 패턴을 사용하여 각 상태별 비즈니스 로직을 독립적인 클래스 단위로 분리하고, 

팩토리 패턴을 통해 상태에 따른 적절한 전략 클래스를 동적으로 선택할 수 있도록 했습니다.

[전략 패턴 로직](https://github.com/coorr/seolyu/blob/main/src/main/java/kr/mainstream/seolyu/domain/resumeReview/status/ResumeReviewStatusHandler.java)

[팩토리 패턴 로직](https://github.com/coorr/seolyu/blob/main/src/main/java/kr/mainstream/seolyu/domain/resumeReview/status/ResumeReviewStatusFactory.java)

이 설계는 새로운 상태가 추가되더라도, 새로운 클래스를 정의하고 팩토리에 등록하는 방식으로 쉽게 확장할 수 있습니다. 

enum 클래스 내부에 비즈니스 로직을 추가하는 것보다 훨씬 효율적이며, 코드의 유지보수성이 크게 향상되었습니다.

<br />
<br />

### Sentry 모니터링 ✅
모니터링은 Sentry 툴을 도입하여, 서버에서 발생하는 예외를 실시간으로 모니터링할 수 있도록 설정했습니다. 

GlobalExceptionHandler 클래스에서 check exception 빼고 **uncheck exception 발생했을 때만 이슈를 생성하도록 설정하여**, 치명적인 문제에 집중할 수 있었습니다. 

발생한 이슈는 Sentry를 통해 감지되며, 관리자에게 즉각 알림이 전송됩니다. 현재 관리자는 한 명이므로, 이메일로 직접 알림이 발송되도록 구성했습니다.

이 시스템 덕분에 예상치 못한 문제가 발생했을 때 즉각적으로 인지할 수 있었고, 빠른 대응이 가능할 수 있었습니다.

![monitoring-mail](https://github.com/coorr/images/blob/main/seolyu/monitoring-mail.png?raw=true)
- 왼쪽 이미지는 실제 sentry 에서 에러 이슈에 대한 화면입니다. 
- Stack Trace, HTTP 정보들이 담겨 있어서 에러 로그를 분석하고 개선할 수 있습니다.
- 오른쪽 이미지는 sentry에서 에러 이슈 발생했을 때 관리자에게 메일로 알림이 온 화면입니다.


<br />
<br />

### 에러 로그 관리 ✅
로그 관리는 하루에 두 개의 파일이 생성되는 롤링 정책을 채택했습니다. 

하나의 파일에는 info 레벨의 로그만 기록되도록 하고, 다른 파일에는 error 레벨의 로그만 기록되도록 설정했습니다. 

이렇게 로그를 구분 관리함으로써, 중요한 에러 로그를 신속하게 찾아낼 수 있었고, 

이를 통해 발생한 문제를 보다 빠르고 정확하게 해결할 수 있었습니다.

![log-back](https://github.com/coorr/images/blob/main/seolyu/log-back.png?raw=true)
- 실제 로그 파일 사진

<br />
<br />

### Nginx 다중 도메인 서버 설정 ✅
이전에는 프론트엔드와 백엔드 서버를 각각 별도의 EC2 인스턴스에서 운영하여, 두 대의 서버에 대한 비용이 발생했습니다. 

이를 개선하기 위해, 단일 EC2 인스턴스에 Nginx를 사용하여 여러 도메인을 설정했습니다. 

Nginx의 리버스 프록시 기능을 활용해 각 도메인에 대해 특정 애플리케이션을 라우팅을 하였습니다.

이 설정을 통해 서버 비용을 절감하면서도, 다양한 도메인과 애플리케이션을 안정적으로 운영할 수 있었습니다.

- seolyu.com ---> 프론트 서버
- api.seolyu.com ---> 백엔드 서버

[Nginx 다중 도메인 서버 설정](https://coor.tistory.com/45)


<br />
<br />

### Nginx와 헬스체크를 활용한 무중단 배포 ✅
무중단 배포를 위해 L3 또는 L7 스위치를 사용하는 방법도 있지만, 이는 추가 비용이 발생하므로 다른 대안을 고려했습니다. 

이 대안은 블루-그린 배포 방식으로, 하나의 서버에서 여러 개의 애플리케이션 인스턴스를 실행하고 Nginx와 헬스체크를 활용하여 무중단 배포를 구현했습니다.

새로운 버전을 배포할 때, Nginx는 헬스체크를 통해 현재 실행 중인 인스턴스의 상태를 지속적으로 모니터링하고, 문제없이 작동하는 인스턴스로만 트래픽을 라우팅합니다. 

이 과정에서 사용자에게는 중단 없이 서비스가 제공되며, 배포 중에도 서버의 안정성이 유지됩니다.

[Nginx와 헬스체크를 활용한 무중단 배포](https://coor.tistory.com/64)

<br />
<br />

# 문제 해결
### AWS 프리티어 EC2 인스턴스의 높은 자원 사용률로 인한 서비스 중단 문제 ⚠️
AWS 프리티어 EC2 인스턴스를 사용하여 Spring Boot 프로젝트를 운영하고 있었습니다. 

이 인스턴스는 vCPU 1 Core와 1GB의 메모리를 제공하는 t2.micro 유형으로, **프로젝트를 빌드할 때 메모리와 CPU 사용률이 80~90% 이상으로 증가했습니다.** 

이로 인해 서버에서 간단한 명령어조차 버퍼링이 걸리고, SSH 접속이 불가능해졌으며, 서버를 재부팅해야만 다시 접속할 수 있는 상황이 발생했습니다. 

재부팅을 하게 되면, 애플리케이션이 종료되며 서비스에 큰 영향을 미치게 되었습니다.

#### [해결 ⭐]

이 문제를 해결하기 위해, 먼저 빌드를 운영 서버에서 직접 수행하지 않는 방법을 고민했습니다.

방법을 찾던 중 무료로 사용할 수 있는 **GitHub Actions를 도입해 빌드 프로세스를 자동화하기로 했습니다.**

GitHub Actions는 코드가 변경될 때마다 자동으로 빌드와 테스트를 수행할 수 있게 해주는 도구입니다.

빌드된 결과물은 AWS S3에 안전하게 저장되었고, 이 파일은 배포할 때 사용하게 됩니다.

배포 과정에서는 AWS CodeDeploy를 활용했습니다. CodeDeploy를 사용하면 S3에 저장된 빌드 파일을 운영 서버에 자동으로 배포할 수 있었습니다.


<br />
<br />

### 메모리가 부족한 프리티어 환경에서 원활한 운영을 위해 Swap으로 가상 메모리 설정 ⚠️
AWS 프리티어 EC2 인스턴스는 저사양의 하드웨어로, 메모리 용량이 1GB에 불과합니다. 

이로 인해 여러 애플리케이션이 동시에 실행되거나 메모리 집약적인 작업이 수행될 때, 서버의 메모리가 부족해지는 문제가 발생했습니다. 

메모리가 부족해지면 서버가 느려지거나 심지어 다운될 위험이 있었고, 서비스의 안정성을 보장하기 어려웠습니다.

#### [해결 ⭐]

Swap 메모리를 활용하기로 했습니다. 

스왑 메모리는 물리적 메모리가 부족할 때 디스크 공간을 임시 메모리로 사용하는 방식입니다. 

이를 통해 물리적 메모리가 가득 차더라도 서버가 계속 작동할 수 있게 만들 수 있었습니다.

```bash
sudo fallocate -l 1G /swapfile  # 1GB 스왑 파일 생성
sudo chmod 600 /swapfile        # 스왑 파일 권한 설정
sudo mkswap /swapfile           # 스왑 파일로 포맷
sudo swapon /swapfile           # 스왑 메모리 활성화
```

먼저, 1GB의 스왑 파일을 생성했습니다. 이 스왑 파일은 디스크에 저장되며, 물리적 메모리가 부족할 때 사용할 가상 메모리 역할을 합니다. 

이후, 스왑 파일의 권한을 설정하고, 스왑 메모리로 사용할 수 있도록 활성화했습니다.

<br />
<br />

### 이력서 파일 업로드 시 사용자 1초 딜레이 ⚠️
사용자가 이력서 .pdf 파일을 업로드한 후 신청 버튼을 눌렀을 때, **1초의 딜레이가 발생했습니다.**

비록 1초라는 짧은 시간이지만, 사용자가 이 시간을 체감하면서 웹사이트의 반응성이 떨어진다고 느낄 수 있었습니다.

딜레이의 원인은 업로드된 파일을 AWS S3에 저장하는 과정에서 발생한 것이었습니다. 

파일 전송 과정이 동기적으로 처리되다 보니, 사용자가 결과를 기다려야 했습니다.

#### [해결 ⭐]

이 문제를 해결할 수 있는 방법은 다음과 같습니다.
1. 파일 압축 - AWS lambda 사용하여 파일을 압축하여 전송 크기를 줄이는 방법
2. S3 Direct Upload - 프론트엔드에서 AWS S3에 직접 업로드할 수 있도록 프리사인드 URL을 생성해 제공하는 방식
3. 비동기 처리 - 파일 업로드를 백그라운드에서 처리

이 중에서 **비동기 처리 방법**으로 선택하여 해결하였습니다. 

그 이유는 대용량 파일이 아닌 파일이 한 개이고 사이즈 크기는 20MB 제한되어 있기 때문입니다.

ThreadPoolTaskExecutor를 도입하여 파일 업로드를 백그라운드에서 처리하도록 구현했으며, 

이를 통해 사용자가 체감하는 대기 시간을 1초에서 0.1초로 크게 줄일 수 있었습니다.

<br />
<br />

### 환경별 파일 업로드 서비스 분기 처리 문제 ⚠️
파일을 업로드할 때 개발 환경과 운영 환경에 따라 서로 다른 파일 저장 서비스를 사용해야 했습니다. 

이를 위해 아래와 같은 방식으로 if-else 조건문을 사용해 환경별로 파일 업로드 로직을 분기 처리했습니다.

**코드 예시:**

```java
private final Environment env;
private final S3FileStorageService s3FileStorageService;
private final LocalFileStorageService localFileStorageService;

public void upload(File file) {
     if (env.acceptsProfiles("prd")) {
        s3FileStorageService.upload(file);
     } else {
        localFileStorageService.upload(file);
     }
}
```
이 접근 방식은 각 환경에 맞는 로직을 **if-else 조건문으로 분리하다 보니, 코드가 복잡해지고 가독성이 떨어졌습니다.**

특히, 추가적인 환경이 필요해지면 if-else 조건문이 계속 늘어나면서 복잡도가 증가하게 됩니다.

그리고 실제로 코드를 들여다보지 않으면, 개발과 운영 환경에서 서로 다른 로직이 사용된다는 점을 인지하기 어려웠습니다.

#### [해결 ⭐]

이 문제를 해결할 수 있는 방법은 다음과 같습니다.
1. 빌드 시점에 클래스 파일을 환경에 맞게 포함시키는 방법
2. Spring의 @Profile을 활용하는 방법
3. Spring의 application.yml 파일에서 프로파일별로 다른 설정 정의하는 방법

이 중에서 **빌드 시점에 클래스 파일을 환경에 맞게 포함**하는 방법으로 해결하였습니다.

Gradle의 sourceSets를 사용하여, 빌드 시점에 특정 환경에 맞는 클래스 파일만 포함되도록 설정했습니다. 

이렇게 함으로써, 코드 내에서 불필요한 if-else 조건문을 없애고, 각 환경에 맞는 로직만 포함되도록 하여 유지보수성을 높였습니다.

```java
sourceSets {
    main {
        java {
            srcDirs "src/main/java"
            if (profile.equals('prd')) {
                 exclude "kr/mainstream/seolyu/infrastructure/file/development"
            } else {
                 exclude "kr/mainstream/seolyu/infrastructure/file/production"
            }
        }
    }
}
```
파일 업로드 코드 리팩토링:
```java
private final FileStorageService fileStorageService;  // 인터페이스

public void upload(File file) {
  fileStorageService.upload(file);
}
```

<br />
<br />

# 아쉬운 점
### Sentry 모니터링 툴의 한계 😪
Sentry를 통해 에러를 감지하고, 즉각적인 알림을 받아 빠르게 대응할 수 있었던 점은 매우 유용했지만, 몇 가지 중요한 기능이 부족했습니다.

첫 번째는 서비스 운영 중에 성능 저하를 감지하기 위해 5초 이상 소요되는 슬로우 쿼리나 개선이 필요한 API에 대한 알림이 필요했지만, Sentry는 이러한 성능 모니터링 기능을 지원하지 않았습니다.

두 번째는 JVM의 힙 메모리, CPU 사용량, 그리고 Tomcat의 Xlog 같은 서버 성능 지표를 모니터링할 수 있는 기능을 제공하지 않았습니다. 

서비스가 더욱 확장되면, 현재의 모니터링 툴로는 한계가 있다는 점을 인식했습니다. 

향후 개선 방향으로는 이러한 문제점을 개선할 수 있는 Scouter, Cloud Monitoring, Prometheus, Grafana 같은 더 강력한 모니터링 툴을 도입하여 성능 지표를 모니터링하고, 

실시간으로 문제를 감지할 수 있는 모니터링 툴을 적용해 봐야 겠다.


<br />
<br />
<br />


