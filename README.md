# Description
A accident report website for users to visulize all accidents in US since 2016.  
Users can self-report accidents with or without usernames.  
Admin will handle all reports.

# Demo
### Home
![Image of Homepage](https://github.com/jgncsy/ACCI-SAFE/blob/master/Screen%20Shot%202020-04-18%20at%2010.24.24%20PM.png)
### Searching Accidents By Road
![Searching Roads](https://github.com/jgncsy/ACCI-SAFE/blob/master/Screen%20Shot%202020-04-18%20at%2010.27.58%20PM.png)
### Factor Analysis
![Factor Analysis](https://github.com/jgncsy/ACCI-SAFE/blob/master/Screen%20Shot%202020-04-18%20at%2010.28.30%20PM.png)
### Slef Report
![Self Report](https://github.com/jgncsy/ACCI-SAFE/blob/master/Screen%20Shot%202020-04-18%20at%2010.40.58%20PM.png)
### User Report History
![report History](https://github.com/jgncsy/ACCI-SAFE/blob/master/Screen%20Shot%202020-04-18%20at%2010.42.04%20PM.png)
### Admin DashBoard
![Admin DashBoard](https://github.com/jgncsy/ACCI-SAFE/blob/master/Screen%20Shot%202020-04-18%20at%2010.39.49%20PM.png)

# Function
### Main Page
1. Show numbers of accidents per state in the map.
2. Show numbers of accidents per county per state in the map
3. Searching numbers of accidents by road, city and state and show in google map.
4. Analysis and visualize number of accidents by humidity.
5. Analysis and visualize number of accidents by visibility.
6. Analysis and visualize number of accidents by weather-condition.
### User page
1. Resiger a new account with username, password, email, city, state, phone number.
2. Reset passwords if forget by username, email, phone number, city ,state.
3. Login and logout.
4. View my account page including username, city, state, email, phone number.
5. Update account page.
6. Reset password after logging in by enter the old password.
7. Update report settings weather by username or anonymous.
8. Self report by username, road, county, city, state, time and severity.
9. View all history report.
10. Withdraw pending report.
### Admin page
1. Login and Logout.
2. Handle all user reports, approve and deny by comments.
3. View all reports.
4. Update all reports.
5. Delete reports for untrue.
6. Delete users.

# RESTFUL APIs
||Operation|API|Description|
|:---|---|---|---|
||GET|api/accidents/numbersByState|Select numbers of accidents per state|
||GET|api/accidents/numbersByCounty/:state|Select numbers of accidents per county by state|
||GET|api/accidents/accidentsByRoad/:state/:city/:road|Searching by road, city and state|
||GET|api/accidents/numbersByHumidity|Select numbers of accidents by humidity|
||GET|api/accidents/numbersByVisibility|Select numbers of accidents by visibility|
||GET|api/accidents/numbersByWeatherCondition|Select numbers of accidents by weather condition|
||POST|api/user/signup|User registration|
||GET|api/user/infoCheck|Check info is matched or not|
||PUT|api/user/updatePassword/:username|Reset password for user|
||POST|api/user/login|Check info is matched or not|
||GET|api/user/:username|Get all user info|
||PUT|api/user/updateAllInfo/:username|update new info for user|
||PUT|api/user/updateSettings/:username|Update report settings|
||POST|api/user/self-report/:username|Self-report accidents|
||GET|api/user/reports/:username|View all history reports|
||DELETE|api/user/:username/:reportID|Delete pending report|
||POST|api/admin/login|Check info is matched for admin|
||PUT|api/admin/:reportID|Update report details|
||DELETE|api/admin/:reportID|Delete user|
||DELETE|api/admin/:username|Delete user|
||GET|api/admin/allUser|Get all users|
||GET|api/admin/allAccidents|Get top 100 recent accidents|
