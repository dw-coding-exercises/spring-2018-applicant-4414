# TODOs

If I had more time, and was turning this into an app intended for
actual users, I would make sure I did the following:

- Add internationalization to support non-english speakers (targeting
at least the 3-4 most popular languages in the USA)
- Do an accessibility audit and make sure the HTML works with screen readers and all resolutions
- Figure out how to indicate the time window in which the search results are valid. e.g. they don't seem to include the 2018 Senate elections.
- Include links to all relevant registration information
- Display a map to the polling locations
- Offer an option to "notify me" about upcoming elections, collect the user's
contact info.
- Add some webdriver-based integration tests. I don't think unit tests are particularly valuable for UI-rendering functions, but a test that actually makes some basic assertions in a browser can be valuable for real sites and services.
- Add some integration tests. Unit tests with mocks or stubs to external services are rarely that valuable in my experience: unit tests work best for testing complex logic on pure, local functions. But a good integration test that actually exercises system integrations is very valuable, and unlocks the capability to confidently use additional automation such as continuous deployment.
- Get data on the candidates and offer links to their websites and nonpartisan policy summaries.
