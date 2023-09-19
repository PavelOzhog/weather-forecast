#language: en


@regress
Feature: Weather forecast negative

  Scenario: Forecast negative

    Given Method GET negative step
      | Moscow |
      | Tokio  |
