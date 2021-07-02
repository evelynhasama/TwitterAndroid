# Project 3 - *Twitter*

**Twitter** is an android app that allows a user to view their Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **16** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x]	User can **sign in to Twitter** using OAuth login
* [x]	User can **view tweets from their home timeline**
  * [x] User is displayed the username, name, and body for each tweet
  * [x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [x] User can **compose and post a new tweet**
  * [x] User can click a “Compose” icon in the Action Bar on the top right
  * [x] User can then enter a new tweet and post this to Twitter
  * [x] User is taken back to home timeline with **new tweet visible** in timeline
  * [x] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
* [x] User can **see a counter with total number of characters left for tweet** on compose tweet page
* [x] User can **pull down to refresh tweets timeline**
* [x] User can **see embedded image media within a tweet** on list or detail view.

The following **optional** features are implemented:

* [x] User is using **"Twitter branded" colors and styles**
* [ ] User sees an **indeterminate progress indicator** when any background or network task is happening
* [x] User can **select "reply" from home timeline to respond to a tweet**
  * [x] User that wrote the original tweet is **automatically "@" replied in compose**
* [x] User can tap a tweet to **open a detailed tweet view**
  * [x] User can see embedded image media on a recycler view
  * [ ] User can **take favorite (and unfavorite) or retweet** actions on a tweet
* [ ] User can view more tweets as they scroll with infinite pagination
* [ ] Compose tweet functionality is built using modal overlay
* [x] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [ ] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.org/android/Drawables#vector-drawables) where appropriate.
* [x] User can view following / followers list through any profile they view (with TabLayout, PageFragments, RecyclerView)
* [ ] Use the View Binding library to reduce view boilerplate.
* [x] On the Twitter timeline, apply scrolling effects such as [hiding/showing the toolbar](http://guides.codepath.org/android/Using-the-App-ToolBar#reacting-to-scroll) by implementing [CoordinatorLayout](http://guides.codepath.org/android/Handling-Scrolls-with-CoordinatorLayout#responding-to-scroll-events).
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in offline mode.

The following **additional** features are implemented:

* [x] User can view a profile's name, banner, profile picture, bio, followers and following count
* [x] User can view the favorite and retweet counts for each tweet on the home feed 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/evelynhasama/TwitterAndroid/blob/master/walkthroughs/walk1.gif' title='Video Walkthrough 1' width='300' alt='Video Walkthrough 1' /> <img src='https://github.com/evelynhasama/TwitterAndroid/blob/master/walkthroughs/walk2.gif' title='Video Walkthrough 2' width='300' alt='Video Walkthrough 2' /> <img src='https://github.com/evelynhasama/TwitterAndroid/blob/master/walkthroughs/walk3.gif' title='Video Walkthrough 3' width='300' alt='Video Walkthrough 3' />

GIF created with [Kap](https://getkap.co/).

## Notes

Some challenges I faced were with the additional/optional stories, particularly displaying the followers/following users and replying to a tweet. With followers/following users, I used a TabLayout and PageFragments which I were new to me and required much debugging. Replying to a tweet challenged me, as I was not certain how to start the Compose Activity for a result when the reply icon on the tweet was clicked in the recycler view.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2021 Evelyn Hasama

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
