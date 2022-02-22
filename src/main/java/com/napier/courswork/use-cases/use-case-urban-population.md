USE CASE 2: Produce Population Reports (on urban population and not-urban population)

REPORTS:
The population of people, people living in cities, and people not living in cities in each continent.
The population of people, people living in cities, and people not living in cities in each region.
The population of people, people living in cities, and people not living in cities in each country.

Goal in Context: [a longer statement of the goal, if needed] As a board member of Lufthansa, I want to produce reports on the urban and not-urban population of different cities,  so that I can support the logistic team in the planning of new connections between the airport and relevant cities
Scope: [what system is considered black-box under design] company
Level: [one of: Summary, Primary task, subfunction] summary
Preconditions: [what we expect is already the state of the world] we have access to data about population
Success End Condition: [the state of the world upon successful completion] the company is able to visualize reports about about population, people living in cities and people not living in cities in each continent, region, country
Failed End Condition: [the state of the world if goal abandoned] the company is not able to visualize reports about about population, people living in cities and people not living in cities in each continent, region, country
Primary Actor: [a role name for the primary actor, or description] company
Trigger: [the action upon the system that starts the use case, may be a time event] a request for the report is sent to data analysts
Main Success Scenario: [put here the steps of the scenario from trigger to goal delivery, and any cleanup after]
                        1. the manager requests the reports to the data analysts
                        2. the data analysts captures the requested information to display on the reports
                        3. the data analysts extracts the current reports from the database
                        4. the manager receives the reports
Extension: [what might happen at a given step to stop the use case] 
        None
Sub variation: [any other branches that a step can take] 
        None
Schedule: [when does the use case needs to be delivered]
        March 2nd