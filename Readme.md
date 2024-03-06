![Java CI](https://github.com/existmaster/digital-library/actions/workflows/java-ci.yml/badge.svg)
![Build and Push Docker image](https://github.com/existmaster/digital-library/actions/workflows/docker.yml/badge.svg)

# Digital Library

디지털 라이브러리 프로젝트는 Java와 Spring Boot를 사용하여 구현된 도서 관리 시스템입니다. 이 시스템을 통해 사용자는 책을 등록하고 관리할 수 있습니다.

## 기능

- 책 등록
- ISBN을 통한 유효성 검사

## 기술 스택

- Java 17
- Spring Boot
- Maven
- H2 Database
- Docker

## 빌드 및 실행 방법

### Maven을 사용하여 빌드하기

```bash
./mvnw clean package
```


### Docker를 사용하여 실행하기

```bash
docker build -t digital-library .
docker run -p 8080:8080 digital-library
```