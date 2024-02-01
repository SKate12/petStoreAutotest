FROM gradle:jdk11-alpine
COPY ./. /app
CMD cd /app && gradle test