# binah-over-flow
Android Coding Challenge

**Intro**

`BinahOverFlowActivity` is our navHost, inside the navigation graph we have two fragments:

`BinahListFragment` : contains recyclerview for stackExchanges questions and simple filter button.

`BinahWebViewFragment` : contains the webView

![graph](https://firebasestorage.googleapis.com/v0/b/viewfinder-202b1.appspot.com/o/Screen%20Shot%202021-06-23%20at%208.14.05.png?alt=media&token=53697459-c648-4969-8c36-c34a01d4c12e)

`BinahListFragment` will fetch questions from stackExchange using `BinahListViewModel`.

The ViewModel will handle api calls and caching (simple object cache) from `QuestionsRepository`

* **kotlin**
* App aritechture: MVVM

* HTTP Client: Retrofit 2

* Image loading: Glide

* Android JetPack Navigation

* dataBinding & viewBinding

* Testing with Espresso: Din't have the chance to complete this section of the task, there is a test that i added, but im not sure about it.

