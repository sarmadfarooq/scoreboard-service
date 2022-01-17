Feature: Update Scoreboard

    Background:
        * url 'http://localhost:8080'

    Scenario: Update an existing scoreboards
        * def input = read('requests/ScoreboardUpdate.json')
        * def expected = read('requests/ScoreboardUpdate.json')
        Given path 'scoreboard/98'
        And request input
        When method PUT
        Then status 200
        * print response
        And match response == expected

    Scenario: Update an existing scoreboards - Bad Request
        * def input = read('requests/ScoreboardUpdate.json')
        * def expected = read('requests/ScoreboardUpdate.json')
        Given path 'scoreboard/99'
        And request input
        When method PUT
        Then status 400
        * print response


