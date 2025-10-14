# Android Project 5 - *BitFit-Part1*

Submitted by: **Jose Valle**

**BitFit-Part1** is a health metrics app that allows users to track their daily food intake and calories. It provides a simple UI to add new foods with their calorie values, stores them locally using Room (SQLite), and displays them in a scrollable list. Entries are persistent across app restarts.

Time spent: **8.5** hours spent in total

## Required Features

The following **required** functionality is completed:

- [x] **At least one health metric is tracked (based on user input)**
    - Chosen metric(s): `Food / Calories`
- [x] **There is a "create entry" UI that prompts users to make their daily entry**
- [x] **New entries are saved in a database and then updated in the RecyclerView**
- [x] **On application restart, previously entered entries are preserved (i.e., are *persistent*)**

The following **optional** features are implemented:

- [ ] **Create a UI for tracking averages and trends in metrics**
- [ ] **Improve and customize the user interface through styling and coloring**
- [ ] **Implement orientation responsivity**
- [ ] **Add a daily photo feature**

The following **additional** features are implemented:

- [ ] Added simple input validation (requires name + numeric calories)
- [ ] Entries stored with timestamps for future trend features

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='bitFitWalkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Kap](https://getkap.co/)

## Notes

The main challenges were:
- Configuring Room database with proper package names to avoid unresolved reference errors.
- Dealing with `android:exported` requirement in Android 12+.
- Ensuring `submitList` worked correctly with Kotlin (removing named argument).

## License

    Copyright 2025 Jose Valle

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
