## Database configuration.

### PostgresSQL

#### Setup

spring.application.name=library_management_system

spring.datasource.url=jdbc:postgresql://localhost:5432/<DB name>

spring.datasource.username=< Username>

spring.datasource.password=< YourPassword>

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=create-drop