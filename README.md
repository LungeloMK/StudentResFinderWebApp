# StudentResFinderWebApp

StudentResFinderWebApp is a Java web application that helps students find safe and convenient accommodation.  
The system allows students to browse listings, landlords to manage accommodation, and admins to verify landlords to reduce scams.

## Overview

The project follows an MVC architecture and combines four core systems:

1. Authentication System
2. Listing Management System
3. Discovery System
4. Trust and Verification System

## Features

### Authentication System

- User registration
- User login
- User logout
- Role-based access control
- Session handling

### Listing Management System

- Add listing
- Edit listing
- Delete listing
- View own listings

### Discovery System

- View all listings
- Search by location
- Filter by price
- View listing details

### Trust and Verification System

- Verify landlords
- Reject landlords
- Only verified landlords can post listings

## Technologies Used

### Backend

- Java
- Servlets
- EJB (Stateless Session Beans)

### Frontend

- JSP
- HTML
- CSS

### Database

- Apache Derby

### Server

- GlassFish

### Development Tool

- NetBeans

### Version Control

- GitHub

## Security (GlassFish Realm)

This project uses GlassFish Realm for authentication and authorization.

### Purpose

- Secure login system
- Manage users and roles
- Protect restricted pages

### Roles

- Student
- Landlord
- Admin

### How It Works

- Users are stored in the Realm
- Login is validated by GlassFish
- Access is controlled based on roles

## System Architecture

```text
Presentation Layer (JSP, HTML)
        ->
Controller Layer (Servlets)
        ->
Business Logic Layer (EJB)
        ->
Data Access Layer (DAO)
        ->
Database (Derby)
```

## Project Structure

```text
StudentResFinderWebApp/
|
|-- src/
|   |-- model/
|   |   |-- User.java
|   |   |-- Student.java
|   |   |-- Landlord.java
|   |   |-- Admin.java
|   |   `-- Listing.java
|   |
|   |-- dao/
|   |   |-- DBConnection.java
|   |   |-- UserDAO.java
|   |   |-- ListingDAO.java
|   |   `-- LandlordDAO.java
|   |
|   |-- business/
|   |   |-- AuthSB.java
|   |   |-- ListingSB.java
|   |   |-- SearchSB.java
|   |   `-- VerificationSB.java
|   |
|   `-- servlet/
|       |-- LoginServlet.java
|       |-- RegisterServlet.java
|       |-- LogoutServlet.java
|       |-- AddListingServlet.java
|       |-- UpdateListingServlet.java
|       |-- DeleteListingServlet.java
|       |-- ViewListingsServlet.java
|       |-- ViewListingDetailsServlet.java
|       |-- SearchServlet.java
|       |-- FilterServlet.java
|       |-- VerifyLandlordServlet.java
|       `-- RejectLandlordServlet.java
|
|-- web/
|   |-- index.jsp
|   |-- login.jsp
|   |-- register.jsp
|   |-- studentDashboard.jsp
|   |-- landlordDashboard.jsp
|   |-- adminDashboard.jsp
|   |-- addListing.jsp
|   |-- editListing.jsp
|   |-- myListings.jsp
|   |-- search.jsp
|   |-- searchResults.jsp
|   |-- viewListing.jsp
|   |-- verifyLandlords.jsp
|   |-- error.jsp
|   `-- accessDenied.jsp
|
`-- database/
    `-- studentresfinder.sql
```

## Database Tables

| Table | Purpose |
|---|---|
| `users` | Stores all users |
| `students` | Student records |
| `landlords` | Landlord verification records |
| `admins` | Admin records |
| `listings` | Accommodation listings |

## System Flow

### Search Listings

`search.jsp` -> `SearchServlet` -> `SearchSB` -> `ListingDAO` -> `Database` -> `searchResults.jsp`

### Add Listing

`addListing.jsp` -> `AddListingServlet` -> `ListingSB` -> `ListingDAO` -> `Database` -> `myListings.jsp`

### Verify Landlord

`verifyLandlords.jsp` -> `VerifyLandlordServlet` -> `VerificationSB` -> `LandlordDAO` -> `Database` -> `verifyLandlords.jsp`

## How to Run

1. Open the project in NetBeans.
2. Start the GlassFish server.
3. Configure the Apache Derby database.
4. Run the SQL script in `database/studentresfinder.sql`.
5. Configure Realm in the GlassFish Admin Console.
6. Deploy the project.
7. Open in browser:

```text
http://localhost:8080/StudentResFinderWebApp
```
## Team Roles (6 Members)

## Team Roles (6 Members)

| Member          | Responsibility                                                  | Main Files / Tasks |
|-----------------|------------------------------------------------------------------|-------------------|
| LungeloMK       | Project setup, GitHub, integration, final testing, shared pages | README.md, index.jsp, dashboard pages, web.xml, integration fixes |
| Pulusa MM       | Authentication System                                           | User.java, AuthSB.java, LoginServlet.java, RegisterServlet.java, LogoutServlet.java, login.jsp, register.jsp |
| Maleka K        | Listing Management System                                       | Listing.java, ListingSB.java, AddListingServlet.java, UpdateListingServlet.java, DeleteListingServlet.java, addListing.jsp, editListing.jsp, myListings.jsp |
| Nkuna KF        | Discovery System                                                | SearchSB.java, ViewListingsServlet.java, ViewListingDetailsServlet.java, SearchServlet.java, FilterServlet.java, search.jsp, searchResults.jsp, viewListing.jsp |
| Ntshudishane P  | DAO + Database                                                  | DBConnection.java, UserDAO.java, ListingDAO.java, LandlordDAO.java, studentresfinder.sql |
| Phelo KN        | Security + Admin System                                         | Admin.java, Landlord.java, VerificationSB.java, VerifyLandlordServlet.java, RejectLandlordServlet.java, verifyLandlords.jsp, GlassFish Realm configuration |

## Key Concepts

- MVC Architecture
- `HttpSession`
- Stateless EJB
- DAO Pattern
- Role-Based Access Control (RBAC)
- GlassFish Realm
- 
## GitHub Collaboration Rules

1. Always pull before working:
   git pull origin main

2. Work only on your assigned files.

3. Do not rename folders without telling the group.

4. Commit with clear messages.

5. Push small changes often.

6. If the project breaks after your push, report it immediately.
   
## Summary

StudentResFinderWebApp is a structured Java web application that:

- Helps students find accommodation
- Allows landlords to manage listings
- Ensures security using GlassFish Realm
- Follows MVC for clean and maintainable design
