### Java Spring template project

#build

mvn clean compile package

#run

mvn -pl service -am spring-boot:run -Dspring-boot.run.arguments=--server.port=${PORT}

http://localhost:8080/client360-autocomplete/swagger-ui/

http://localhost:8080/client360-autocomplete/api/v1/customer?phone=%2B79522700430


### CI/CD with Auto DevOps

This template is compatible with [Auto DevOps](https://docs.gitlab.com/ee/topics/autodevops/).

If Auto DevOps is not already enabled for this project, you can [turn it on](https://docs.gitlab.com/ee/topics/autodevops/#enabling-auto-devops) in the project settings.