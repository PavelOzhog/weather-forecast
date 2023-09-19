#language: en


@regress
Feature: weather

  Scenario Outline: weather forecast
    Given first step with params "<city>"

    Examples:
      | city     |
      | New York |
      | Moscow   |
      | Boston   |
      | Tokio    |
