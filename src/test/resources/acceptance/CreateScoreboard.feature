Feature: Create Scoreboard

    Background:
        * url 'http://localhost:8080'

    Scenario: Create new scoreboards
        * def input = read('requests/ScoreboardCreate.json')
        Given path 'scoreboard'
        And request input
        When method POST
        Then status 200
        * print response

    Scenario: Create new scoreboards - Bad Request
        * def input = read('requests/ScoreboardCreateBad.json')
        Given path 'scoreboard'
        And request input
        When method POST
        Then status 400
