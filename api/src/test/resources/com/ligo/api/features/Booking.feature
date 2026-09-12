# language: es
@API_AUTOMATION_BOOKING
Feature: Reservas

  Background:
    * url 'https://restful-booker.herokuapp.com'
    * header Content-Type = 'application/json'
    * header Accept = 'application/json'
    * def bookingRequest = read('classpath:com/ligo/api/data/booking-request.json')
    * def expectedCreateResponse = read('classpath:com/ligo/api/data/booking-create-response.json')
    * def expectedExistingResponse = read('classpath:com/ligo/api/data/booking-existing-response.json')
    * def expectedNotFoundResponse = read('classpath:com/ligo/api/data/booking-not-found-response.json')

  @SCENARIO_CREATE_BOOKING @TESTB01
  Scenario: Crear reserva con datos válidos
    Given path '/booking'
    And request bookingRequest
    When method post
    Then status 200
    And match response.booking contains expectedCreateResponse.booking
    And match response.bookingid == '#number'

  @SCENARIO_GET_BOOKING @TESTB02
  Scenario: Consultar reserva existente
    Given path '/booking/1'
    When method get
    Then status 200
    And match response contains expectedExistingResponse

  @SCENARIO_GET_BOOKING_NOT_FOUND  @TESTB03
  Scenario: Consultar reserva inexistente
    Given path '/booking/999999'
    When method get
    Then status 404
    * def notFoundMessage = response.error ? response.error : response
    And match notFoundMessage == expectedNotFoundResponse.error
