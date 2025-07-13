Feature: User Registration

  Scenario Outline: Register a new user
    Given user is on the registration page
    When user clicks on account and registration buttons
    And user enters registration details "<FirstName>", "<LastName>", "<Email>", "<Password>"
    And user clicks register button on account page
    Then registration should be "<ExpectedResult>"

    Examples:
      | FirstName | LastName | Email           | Password | ExpectedResult |
      | John      | Doe      | john@doe.com    | pass123  | Success        |
      | Jane      | Smith    | jane@smith.com  | pass456  | Failed         | 