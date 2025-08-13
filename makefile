COMPOSE_FILE=docker-compose.yml
WAIT_TIME=5
GRADLE_CMD=./gradlew bootRun
MAVEN_CMD=mvn spring-boot:run
USE_GRADLE=$(shell test -f build.gradle || test -f build.gradle.kts && echo true || echo false)

wait-for-postgres:
	@echo "Waiting for postgres.."
	@until docker exec postgres-container pg_isready -U postgres > /dev/null 2>&1; do \
		sleep 1; \
	done
	@echo "Postgres ready"

wait-for-kafka:
	@echo "Waiting for kafka.."
	@until docker exec kafka-container kafka-topics.sh --bootstrap-server localhost:9092 --list > /dev/null 2>&1; do \
		sleep 1; \
	done
	@echo "Kafka kafka"

docker-up:
	docker compose -f $(COMPOSE_FILE) up -d

docker-down:
	docker compose -f $(COMPOSE_FILE) down

start: docker-up wait-for-postgres wait-for-kafka
	@echo "Waiting for app..."
	@if [ "$(USE_GRADLE)" = "true" ]; then \
		$(GRADLE_CMD); \
	else \
		$(MAVEN_CMD); \
	fi

clean:
	docker compose -f $(COMPOSE_FILE) down -v
	@echo "🧹 Limpieza completada"