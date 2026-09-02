# FinFlow Enterprise — Internal Financial Operations & Expense Management Platform

FinFlow Enterprise is a full-stack, enterprise-grade financial workflow and expense management SaaS platform designed for high-growth software and technology organizations. It streamlines employee business expense claims, receipt ingestion, multi-tiered configurable approval matrices, vendor bill management, department budget tracking, and GAAP-compliant financial reporting.

---

## 🏛️ Technical Architecture

| Layer | Technology Stack |
|:---|:---|
| **Backend Runtime** | Java 21 (LTS), Spring Boot 3.3.x |
| **Persistence & ORM** | PostgreSQL 16, Spring Data JPA, Hibernate, Flyway |
| **Security & Auth** | Spring Security 6, Stateless JWT, RBAC Policy Engine |
| **Caching & Queue** | Redis 7 (Distributed Cache), Kafka (Domain Events) |
| **Object Storage** | MinIO / AWS S3-Compatible Blob Storage |
| **Frontend SPA** | React 18, TypeScript, Vite, Tailwind CSS, Lucide Icons |
| **Export Engines** | Apache POI 5.x (Excel), OpenCSV, JasperReports |
| **Testing Harness** | JUnit 5, Mockito, AssertJ, Testcontainers, Vitest |
| **DevOps & Infra** | Docker, Docker Compose, Kubernetes, Helm, GitHub Actions |

---

## 🚀 Quick Start & Installation

### 1. Prerequisites
* **Java**: JDK 21+
* **Maven**: 3.9+
* **Node.js**: 18+ & npm 9+
* **Docker & Docker Compose**: Installed and running

### 2. Environment Setup
```bash
# Clone the repository
git clone git@github.com:gandhikomarala/fintech_for_software_company.git
cd fintech_for_software_company

# Copy configuration sample
cp config.sample.json config.json
```

### 3. Start Infrastructure Dependencies
```bash
docker-compose up -d postgres redis minio
```

### 4. Build & Run Backend
```bash
cd backend
mvn clean install -DskipTests
mvn spring-boot:run -pl finflow-application
```

### 5. Build & Run Frontend
```bash
cd ../frontend
npm install
npm run dev
```
Open `http://localhost:5173` in your browser.

---

## 🧪 Testing

```bash
# Run backend JUnit 5 & Mockito test suite:
cd backend && mvn test

# Run frontend Vitest suite:
cd frontend && npm run test
```

---

## 📄 License & Proprietary Rights
Copyright © 2026 Gandhiko Marala. All Rights Reserved. Proprietary software.
