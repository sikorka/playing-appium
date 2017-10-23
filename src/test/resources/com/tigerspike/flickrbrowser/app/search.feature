Feature: Search
  As a user
  I want to search by tag in app
  So I don't need to waste time browsing web


  Scenario: Search by single existing tag
    Given Michelle opens the app on her phone
    When  She submits single tag u1l2a3l4a5 to search by
    Then  The photos titles displayed are equal to the ones returned from Photos API


  Scenario: Search by single non-existing tag
    Given Michelle opens the app on her phone
    When  She submits single non-existing tag to search by
    Then  Nothing is displayed in the app


  Scenario: Search by multiple tags
    Given Michelle opens the app on her phone
    When  She enters two tags u1l2a3l4a5,  ulalaGdzieKurekSzesc to search by
    Then  The photos titles displayed are equal to the ones returned from Photos API