Feature: Search
  As a user
  I want to search by tag in app
  So I don't need to waste time browsing web


  Scenario: Search by single non-existing tag
    Given Michelle opens the app on her phone
    When  She submits single non-existing tag to search by
    Then  Nothing is displayed in the app

#
#  Scenario: Search by existing tag(s)
#    Given Michelle opens the app on her phone
#    When  She submits <description> '<tags>' to search by
#      | description                | tags                              |
#      | single tag                 | u1l2a3l4a5                        |
#      | two tags                   | u1l2a3l4a5,  ulalaGdzieKurekSzesc |
#      | tag with special chars     | u1l2a3l4a5/&?                     |
#      | tags separated with spaces | u1l2a3l4a5 ulalaGdzieKurekSzesc   |
#    Then  The photos titles displayed are equal to the ones returned from Photos API
#