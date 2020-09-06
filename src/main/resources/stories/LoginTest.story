Scenario: Checking validation with wrong creds
Given I open yandex page
When I log in using not existing Aleh and 123456789
Then I have an error ???????? ??????
