# First Try Or Tenth -  Design document

## 1. Project Overview
A web application for the management of the day-to-day activities of a private driving school.

### Problem Statement: 
Managing multiple driving instructors, vehicles, trainees and tracking their progress using regular spreadsheets is tedious and time-consuming.

### Proposed Solution:
An admin web application that allows users to handle everything related to a driving school in a single user interface. 

### Goals:
- Allows users to switch between two languages/locales EN and RO.
- Allows admin users to create other admin users.
- Allows admin users to create driving instructor and trainee users with limited access (after mvp).
- Allows the creation and management of driving instructors, vehicles, trainees and driving sessions.

## 2. System Architecture
**Frontend**: TypeScript Angular with service based RxJs state management  
**Backend**: Java Spring Boot with Data JPA and Hibernate ORM  
**Database**: Relational (PostgreSQL)  
**Authentication**: JWT tokens

## 3. Data Model and Relationships:
- Table users with multiple roles
- Table driving instructors
- Table vehicles
- Table trainees
- Table driving sessions
- A driving instructor can have multiple vehicles, multiple trainees and multiple sessions 
- A vehicle can have multiple driving instructors, multiple trainees, and multiple sessions
- A trainee can have one driving instructor, one vehicle and multiple driving sessions
- A driving session can have one instructor, one vehicle and one trainee

## 4. API Endpoints
### Users
| Method | Endpoint                   | Description                  | 
|--------|----------------------------|------------------------------|
| GET    | /api/users                 | Get a list of all users      |
| GET    | /api/users/{uuid}          | Get user account details     |
| POST   | /api/users/register        | Register new user account    |
| POST   | /api/users/login           | Log user in                  |
| PUT    | /api/users/{uuid}          | Edit user account            |
| PATCH  | /api/users/{uuid}/password | Change user account password |
| DELETE | /api/users/{uuid}          | Delete user acount (soft)    |

### Driving Instructors
| Method | Endpoint                         | Description                              | 
|--------|----------------------------------|------------------------------------------|
| GET    | /api/instructors                 | Get a list of all driving instructors    |
| GET    | /api/instructors/{uuid}          | Get driving instructor details           |
| POST   | /api/instructors/new             | Create a new driving instructor profile  |
| PUT    | /api/instructors/{uuid}/profile  | Edit driving instructor profile          |
| PATCH  | /api/instructors/{uuid}/vehicles | Edit assined vehicles                    |
| PATCH  | /api/instructors/{uuid}/trainees | Edit assined trainees                    |
| PATCH  | /api/instructors/{uuid}/sessions | Edit assined driving sessions            |
| DELETE | /api/instructors/{uuid}          | Delete driving instructor profile (soft) |

### Vehicles
| Method | Endpoint                      | Description                   | 
|--------|-------------------------------|-------------------------------|
| GET    | /api/vehicles                 | Get a list of all vehicles    |
| GET    | /api/vehicles/{uuid}          | Get vehicle details           |
| POST   | /api/vehicles/new             | Create a new vehicle          |
| PUT    | /api/vehicles/{uuid}          | Edit vehicle                  |
| PATCH  | /api/vehicles/{uuid}/vehicles | Edit assined vehicles         |
| PATCH  | /api/vehicles/{uuid}/trainees | Edit assined trainees         |
| PATCH  | /api/vehicles/{uuid}/sessions | Edit assined driving sessions |
| DELETE | /api/vehicles/{uuid}          | Delete vehicles (soft)        |

### Trainee
| Method | Endpoint                        | Description                   | 
|--------|---------------------------------|-------------------------------|
| GET    | /api/trainess                   | Get a list of all trainess    |
| GET    | /api/trainess/{uuid}            | Get trainee details           |
| POST   | /api/trainees/new               | Create a new trainee profile  |
| PUT    | /api/trainees/{uuid}/profile    | Edit trainee profile          |
| PATCH  | /api/trainees/{uuid}/instructor | Edit assined instructor       |
| PATCH  | /api/trainees/{uuid}/vehicle    | Edit assined vehicle          |
| PATCH  | /api/trainees/{uuid}/sessions   | Edit assined driving sessions |
| DELETE | /api/trainees/{uuid}            | Delete trainee profile (soft) |

### Driving sessions
| Method | Endpoint                        | Description                | 
|--------|---------------------------------|----------------------------|
| GET    | /api/sessions                   | Get a list of all sessions |
| GET    | /api/sessions/{uuid}            | Get session details        |
| POST   | /api/sessions/new               | Create a new session       |
| PUT    | /api/sessions/{uuid}            | Edit session               |
| PATCH  | /api/sessions/{uuid}/instructor | Edit assined instructor    |
| PATCH  | /api/sessions/{uuid}/vehicle    | Edit assined vehicle       |
| PATCH  | /api/sessions/{uuid}/trainee    | Edit assined trainee       |
| DELETE | /api/sessions/{uuid}            | Delete session (soft)      |

## 5. User Interface Prototype

### General
- Landing/Login Page: Simple form for credentials.
- AppBar Panel (top):
  - Language toggle
  - Horizontal link buttons for Users, Driving Instructors, Vehicles, Trainees, Sessions. 
  - User Actions button for a Dropdown menu containing: Edit Account, Add User, Logout
- Dashboard Page: Multiple panels with short lists of instructors, vehicles, trainees, sessions that were active lately.

### User Pages
- Add User: Form for registering a new user.
- Users:
  - button for creating a new user
  - table list of all registered users with actions for edit and delete and details.
- Edit User: 
  - prefilled form with user details
  - change password button (enables in page form)
  - delete user button

### Driving Instructor Pages
- Add Driving Instructor: form for adding a new instructor
- Driving Instructors:
  - button for creating a new instructor
  - table list of all driving instructors with actions: details and edit profile
- Instructor Details:
  - button edit profile 
  - button delete 
  - dropdown instructor status
  - table list of active vehicles with:
    - actions assign, unassing, details
    - toggle filters assigned, unassigned (default both)
  - table list of active trainees with:
    - actions assign, unassing, details
    - toggle filters assigned, unassigned (default both)
  - table list of scheduled driving sessions assigned to current instructor with action details
  - button save
- Edit Instructor Profile: form with prefilled details

### Vehicle Pages
- Add Vehicle: form for adding a new vehicle
- Vehicles:
  - button for creating a new vehicle
  - table list of all vehicles with actions: details and edit
- Vehicle Details:
  - form with prefilled details
  - button delete 
  - dropdown vehicle status
  - table list of active driving instructors with:
    - actions assign, unassing, details
    - toggle filters assigned, unassigned (default both)
  - table list of active trainees with:
    - actions assign, unassing, details
    - toggle filters assigned, unassigned (default both)
  - table list of scheduled driving sessions assigned to current vehicle with action details

### Trainee Pages
- Add Trainee: form for adding a new vehicle
- Trainees:
  - button for creating a new trainee profile
  - table list of all trainees with actions: details and edit profile
- Trainee Details:
  - button edit profile 
  - button delete
  - student progress stats
  - dropdown student status
  - button select vehicle which enables an in-page table list of active vehicles with action select
  - button select instructor which enables an in-page table list of active instructors with action select
  - table list of scheduled driving sessions assigned to current trainee with action details
  - button save
 - Edit Trainee Profile: form with prefilled details

### Sessions Pages
- Add/Edit Sessions: 
  - form for adding a new session
  - button select/change session trainee which opens a modal with a table list of active trainees and button select
  - button change session instructor which opens a modal with a table list of active instructors and a button select
  - button change session vehicle which opens a modal with a table list of active vehicles and a button select
  - button create/save session 
- Sessions:
  - button for creating a new vehicle
  - table list of all sessions with:
    - actions details and edit
    - filter for status