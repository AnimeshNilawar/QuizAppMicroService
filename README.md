# Quiz App Microservices

A comprehensive microservices-based quiz application built with Spring Boot and Spring Cloud ecosystem, demonstrating modern microservices architecture patterns and best practices.

## 🏗️ Architecture Overview

This project implements a distributed quiz application using microservices architecture with the following components:

- **Service Registry (Eureka Server)** - Service discovery and registration
- **API Gateway** - Single entry point for all client requests
- **Question Service** - Manages quiz questions and categories
- **Quiz Service** - Handles quiz creation, management, and scoring

## 🚀 Technologies Used

### Core Technologies

- **Java 21** - Programming language
- **Spring Boot** - Application framework
- **Spring Cloud** - Microservices framework
- **Maven** - Build and dependency management

### Microservices Stack

- **Netflix Eureka** - Service discovery and registration
- **Spring Cloud Gateway** - API Gateway for routing and load balancing
- **OpenFeign** - Declarative REST client for inter-service communication
- **Spring Cloud Load Balancer** - Client-side load balancing

### Database & Persistence

- **PostgreSQL** - Primary database
- **Spring Data JPA** - Object-relational mapping
- **Hibernate** - ORM framework

### Development Tools

- **Lombok** - Reduces boilerplate code
- **Spring Boot Starter Test** - Testing framework

## 🎯 Key Features

### Microservices Patterns Implemented

- **Service Discovery**: Automatic service registration and discovery using Eureka
- **API Gateway**: Centralized routing and cross-cutting concerns
- **Inter-service Communication**: RESTful APIs with OpenFeign clients
- **Load Balancing**: Distributed load balancing across service instances
- **Configuration Management**: Externalized configuration per service

### Application Features

- **Question Management**: CRUD operations for quiz questions
- **Quiz Creation**: Dynamic quiz generation from question pools
- **Category Management**: Organize questions by categories
- **Score Calculation**: Automated quiz scoring and results

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or later installed
- **Maven 3.6+** installed
- **PostgreSQL** database server running
- **Git** for version control

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd QuizAppMicroService
```

### 2. Database Setup

Create PostgreSQL databases:

```sql
-- For Question Service
CREATE DATABASE "QuestionDB";

-- For Quiz Service
CREATE DATABASE "QuizService";

-- Create user (if not exists)
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE "QuestionDB" TO admin;
GRANT ALL PRIVILEGES ON DATABASE "QuizService" TO admin;
```

### 3. Build All Services

```bash
# Build all services
mvn clean install
```

### 4. Start Services (in order)

#### Start Service Registry (Port: 8761)

```bash
cd service-registry
mvn spring-boot:run
```

#### Start API Gateway (Port: 8765)

```bash
cd api-gateway
mvn spring-boot:run
```

#### Start Question Service (Port: 8080)

```bash
cd QuestionService
mvn spring-boot:run
```

#### Start Quiz Service (Port: 8090)

```bash
cd QuizService
mvn spring-boot:run
```

## 🔗 Service Endpoints

### Service Registry (Eureka Dashboard)

- **URL**: http://localhost:8761
- **Description**: View all registered services and their status

### API Gateway

- **Base URL**: http://localhost:8765
- **Routes**:
  - `/question-service/**` → Question Service
  - `/quiz-service/**` → Quiz Service

### Question Service

- **Direct URL**: http://localhost:8080 (for development)
- **Via Gateway**: http://localhost:8765/question-service

### Quiz Service

- **Direct URL**: http://localhost:8090 (for development)
- **Via Gateway**: http://localhost:8765/quiz-service

## 📊 Service Communication Flow

```
Client Request → API Gateway → Load Balancer → Target Service
                     ↓
            Service Registry (Eureka)
                     ↓
            Service Discovery & Registration
```

## 🏗️ Project Structure

```
QuizAppMicroService/
├── service-registry/          # Eureka Server
│   ├── src/main/java/
│   └── pom.xml
├── api-gateway/               # Spring Cloud Gateway
│   ├── src/main/java/
│   └── pom.xml
├── QuestionService/           # Question Management Service
│   ├── src/main/java/
│   │   └── com/moddynerd/questionservice/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── dao/
│   │       └── model/
│   └── pom.xml
├── QuizService/              # Quiz Management Service
│   ├── src/main/java/
│   │   └── com/moddynerd/quizservice/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── dao/
│   │       ├── model/
│   │       └── feign/
│   └── pom.xml
└── README.md
```


## 🧪 Testing

Run tests for all services:

```bash
mvn test
```

Run tests for specific service:

```bash
cd QuestionService
mvn test
```

## 📈 Monitoring and Health Checks

- **Eureka Dashboard**: http://localhost:8761 - Monitor service health and registration
- **Gateway Routes**: Available through Spring Cloud Gateway actuator endpoints
- **Service Health**: Each service exposes health endpoints via Spring Boot Actuator

## 🔄 Development Workflow

1. **Service Development**: Each service can be developed independently
2. **Service Registration**: Services automatically register with Eureka on startup
3. **API Gateway Routing**: Gateway automatically discovers and routes to services
4. **Inter-service Communication**: Services communicate via OpenFeign clients
5. **Load Balancing**: Multiple instances are automatically load-balanced

## 🚧 Future Enhancements

- **Distributed Tracing**: Implement Zipkin/Jaeger for request tracing
- **Centralized Configuration**: Add Spring Cloud Config Server
- **Circuit Breaker**: Implement Hystrix/Resilience4j for fault tolerance
- **API Documentation**: Add Swagger/OpenAPI documentation
- **Containerization**: Docker and Kubernetes deployment
- **Security**: OAuth2/JWT authentication and authorization
- **Monitoring**: Prometheus and Grafana integration

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 👨‍💻 Author

<div align="center">
  <img src="https://avatars.githubusercontent.com/AnimeshNilawar?s=120" alt="Animesh Nilawar" style="border-radius: 50%; border: 3px solid #0366d6;">
  
  **Animesh Nilawar**
  
  *Backend Developer & Microservices Enthusiast*
  
  [![GitHub](https://img.shields.io/badge/GitHub-AnimeshNilawar-black?style=for-the-badge&logo=github)](https://github.com/AnimeshNilawar)
  [![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=for-the-badge&logo=linkedin)](https://in.linkedin.com/in/animesh-nilawar)
  [![Email](https://img.shields.io/badge/Email-Contact-red?style=for-the-badge&logo=gmail)](mailto:nilawaranimesh@gmail.com)
  
  ---
  
  💡 *Passionate about building scalable distributed systems and exploring modern software architecture patterns*
</div>

## 📄 License

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.


## 🙏 Acknowledgments

- Spring Cloud team for excellent microservices framework
- Netflix OSS for pioneering microservices patterns
- PostgreSQL community for robust database solution

---

_This project serves as a learning exercise in microservices architecture and demonstrates practical implementation of Spring Cloud ecosystem components._
