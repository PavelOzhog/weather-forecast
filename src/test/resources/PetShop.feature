#language: en


@regress
Feature: pets

  Scenario Outline: pets1
    Given first step with params "<id>", "<quantity>", "<complete>"
    Then get order by param "<id>", "<quantity>"
    Then delete order by "<id>"
    Examples:
      |id|quantity|complete|
      |1 |1       |false   |
      |3 |5       |false   |
      |8 |1       |false   |
