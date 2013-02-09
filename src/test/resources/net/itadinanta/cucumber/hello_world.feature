Feature: Greetings
  Scenario: Hello world
    When I say world
    Then you reply 'Hello, world!'
   
  Scenario: Goodbye Nico
    When I say Nico
    And she says Goodbye
    Then you reply 'Goodbye, Nico!'
