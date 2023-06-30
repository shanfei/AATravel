Feature: User functionalities
  This feature contains a list of functionalities related to user

  Scenario Outline: create a user with provided user name

    Given user with provided username <provided_name> doesn't exists
    When username <provided_name> is passed in to create an new user
    Then The user with <user_name> is created

    Examples:
      | provided_name  |user_name |
      | Fei            |Fei       |
      | Shannon        |Shannon   |

