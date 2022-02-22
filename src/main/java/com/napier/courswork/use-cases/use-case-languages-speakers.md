USE CASE 5: Produce Population Reports (for certain languages-speakers)

REPORTS:
the number of people who speak Chinese, English, Hindi, Spanish, Arabic
- from greatest number to smallest,
- the percentage of the world population:

Goal in Context: [a longer statement of the goal, if needed] As a board member of Lufthansa, I want to produce reports of athe number of people who speak Chinese, English, Hindi, Spanish, Arabic (from greatest number to smallest and the percentage of the world population), 
so that I can indicate how many employees to hire in the customer service for every language indicated 
Scope: [what system is considered black-box under design] company
Level: [one of: Summary, Primary task, subfunction] summary
Preconditions: [what we expect is already the state of the world] we have access to data about population
Success End Condition: [the state of the world upon successful completion] the company is able to correctly visualize the reports above
Failed End Condition: [the state of the world if goal abandoned] the company is not able to visualize the correct reports
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
