#language: en


@regress
Feature: Weather forecast negative

  Scenario: Invalid city

    Given Method GET invalid city "qwerty"