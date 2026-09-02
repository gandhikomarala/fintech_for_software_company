# FinFlow Enterprise Development Makefile
.PHONY: all build run test clean docker-build

all: build

build:
	cd backend && mvn clean install -DskipTests
	cd frontend && npm install && npm run build

run:
	docker-compose up -d

test:
	cd backend && mvn test
	cd frontend && npm test

clean:
	cd backend && mvn clean
	docker-compose down -v

docker-build:
	docker build -t finflow-enterprise:latest .
