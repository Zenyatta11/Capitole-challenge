# Capitole Challenge - Retail Price Search
====================

## Service Execution
--------------------------------

### Step 1:
In a terminal, run the service with:
- Linux: `./gradlew bootRun`
- Windows: `gradlew bootRun`

You should be able to access the Swagger at `http://localhost:8080/swagger-ui/index.html#/` and the healthcheck endpoint at `http://localhost:8080/health`

## Postman
A collection of one endpoint is provided. 

## Jmeter - Smoke Tests - E2E Test
A collection of three end to end tests are provided. 

### Query

This endpoint will return a price listing based on:
- product_id: Integer
- brand_id: Long
- application_date: DateTime format (YYYY-MM-DDTHH:MM:SS)

It will return a price listing of highest priority for the specified date based on the existing listings on the database. Should such an entry not exist, it will return 404 with a brief description of "Product not found". 

The data provided in the challenge will be populated automatically upon service start (`data.sql` file is provided)

This project has been built upon Java 17 as requested by the challenge specifications.