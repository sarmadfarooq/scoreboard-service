Feature: Get Scoreboard

    Background:
        * url 'http://localhost:8080'

    Scenario: Get All scoreboards

        Given path 'scoreboards'
        When method GET
        Then status 200
        * print response

    Scenario: Get scoreboard with id 1

        * def expected = read('expectations/Scoreboard1.json')
        Given path 'scoreboard/65'
        When method GET
        Then status 200
        And match response == expected
