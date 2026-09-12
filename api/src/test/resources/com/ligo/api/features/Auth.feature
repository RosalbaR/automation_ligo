# language: es
@API_AUTOMATION_AUTH
Feature: Autenticación

  Background:
    * url 'https://restful-booker.herokuapp.com'
    * header Content-Type = 'application/json'
    * header Accept = 'application/json'

  @SCENARIO_LOGIN_SUCCESS @TESTA01
  Scenario: Login exitoso con credenciales válidas
    Given path '/auth'
    And request { username: 'admin', password: 'password123' }
    When method post
    Then status 200
    And match response.token == '#string'
    And match response.token == '#notnull'

  @SCENARIO_LOGIN_FAILURE @TESTA02
  Scenario: Login fallido con credenciales inválidas
    Given path '/auth'
    And request { username: 'baduser', password: 'badpass' }
    When method post
    Then status 200
    And match response.reason == 'Bad credentials'
