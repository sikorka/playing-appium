Feature: Search
  As a user
  I want to use a search by tag in app
  So that I don't need to open browser (?)


  Scenario: Search by single existing tag
    Given Kasia opens the app on her phone
    When  She submits single tag 'u1l2a3l4a5' to search by
    Then  The photos titles displayed are equal to the ones returned from Photos API


  Scenario: Search by single, non-existing, tag
    Given Kasia opens the app on her phone
    When  She enters single non-existing tag to search by
    Then  Nothing is displayed in the app


  Scenario: Search by multiple tags
    Given Kasia opens the app on her phone
    When  She enters two <tags> to search by
      |       tags       |
      | "london bondon"  |
      | "london, bondon" |
    Then  The photos titles displayed are equal to the ones returned from Photos API


  Scenario: Submit search with no tag
    Given Kasia opens the app on her phone
    When  She enters nothing into search box and submits
    Then  Nothing is displayed in the app

