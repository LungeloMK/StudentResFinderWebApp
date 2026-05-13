# StudentResFinderWebApp

Prepared by Mafuleka KL

StudentResFinderWebApp is a Java EE web application for student accommodation discovery. It allows students to search available rooms, landlords to manage listings after verification, and admins to approve or reject landlord accounts before they can publish accommodation.

## Current Status

The project has been completed into a working end-to-end system with:

- full Java model, DAO, business, and servlet layers
- Derby database schema and starter admin account
- GlassFish file-realm login using role-based routing
- student, landlord, and admin dashboards
- listing add, edit, delete, search, and detail views
- landlord verification and rejection workflow
- modern full-width responsive JSP styling
- custom StudentResFinder logo in the header
- polished homepage with color, glass-style navigation, animations, and search entry point
- basic security controls from the module notes

## Team Roles

| Member | Responsibility | Main Files / Tasks |
|---|---|---|
| Mafuleka KL | Project setup, GitHub, integration, final testing, shared pages, styling | `README.md`, `index.jsp`, dashboard pages, `styling.css`, `web.xml`, integration fixes |
| Pulusa MM | Authentication System | `User.java`, `AuthSB.java`, `LoginServlet.java`, `RegisterServlet.java`, `LogoutServlet.java`, `login.jsp`, `register.jsp` |
| Maleka K | Listing Management System | `Listing.java`, `ListingSB.java`, `AddListingServlet.java`, `UpdateListingServlet.java`, `DeleteListingServlet.java`, `addListing.jsp`, `editListing.jsp`, `myListings.jsp` |
| Nkuna KF | Discovery System | `SearchSB.java`, `ViewListingsServlet.java`, `ViewListingDetailsServlet.java`, `SearchServlet.java`, `FilterServlet.java`, `search.jsp`, `searchResults.jsp`, `viewListing.jsp` |
| Ntshudishane P | DAO and Database | `DBConnection.java`, `UserDAO.java`, `ListingDAO.java`, `LandlordDAO.java`, `studentresfinder.sql` |
| Phelo KN | Security and Admin System | `Admin.java`, `Landlord.java`, `VerificationSB.java`, `VerifyLandlordServlet.java`, `RejectLandlordServlet.java`, `verifyLandlords.jsp`, GlassFish realm configuration |

## Main Features

### Authentication and Roles

- Register as a student or landlord
- Login through the GlassFish file realm
- Logout and session clearing
- Role-based redirects after login
- Student, landlord, and admin role checks
- Landlord-only registration fields appear only when Landlord is selected
- Public registration does not allow creating admin accounts

### Student Discovery

- Browse available listings
- Search by location
- Filter by minimum and maximum rent
- View listing details and landlord contact information
- Student dashboard with search entry points

### Landlord Listing Management

- Landlords start with `PENDING` verification
- Verified landlords can add accommodation listings
- Edit listing details
- Delete own listings
- View all own listings from the landlord dashboard

### Admin Verification

- Admin dashboard
- Review pending landlord accounts
- Approve legitimate landlords
- Reject landlord applications
- Keep unverified landlords from managing listings

### User Interface

- Full-width responsive layout across pages
- Custom logo from `web/images`
- Modern homepage with richer colors, cards, stats, and CTA sections
- Outfit font import in `styling.css`
- Glassmorphism-style top navigation and cards
- Hover effects and simple CSS animations such as `fadeInUp` and `revealRight`
- Shared styling for dashboards, forms, tables, errors, access denied, and listing pages

## Security Implementation

The project implements basic security in the manner covered by the module notes:

- GlassFish file realm authentication
- `STUDENT`, `LANDLORD`, and `ADMIN` application roles
- group-to-role mapping in `web/WEB-INF/glassfish-web.xml`
- login handled through `request.login(...)`
- server-side role checks using `SecurityUtil.requireRole(...)`
- HTTP-only session cookies in `web/WEB-INF/web.xml`
- basic response security headers
- password hashing and salting for database users
- input cleaning and output escaping helpers
- access denied and error pages

### GlassFish File Realm Users

Create these users in the GlassFish Admin Console under:

`Configurations -> default-config -> Security -> Realms -> file -> Manage Users`

Recommended local test users:

| User ID | Password | Group |
|---|---|---|
| `admin@studentresfinder.local` | `Admin@12345` | `ADMIN` |
| `lerato@studentmail.com` | choose a password | `STUDENT` |
| `thabo.landlord@mail.com` | choose a password | `LANDLORD` |

The group names must match the application roles exactly: `STUDENT`, `LANDLORD`, and `ADMIN`.

## Database

The app uses Apache Derby.

Default connection:

```text
jdbc:derby://localhost:1527/StudentResFinderDB;create=true
```

Database script:

```text
database/studentresfinder.sql
```

Tables:

| Table | Purpose |
|---|---|
| `users` | Stores all user account records |
| `students` | Stores student profile records |
| `landlords` | Stores landlord business and verification records |
| `admins` | Stores admin profile records |
| `listings` | Stores accommodation listings |

Starter database admin:

```text
Email: admin@studentresfinder.local
Password: Admin@12345
```

## Project Structure

```text
StudentResFinderWebApp/
|-- database/
|   `-- studentresfinder.sql
|-- src/
|   |-- business/
|   |   |-- AuthSB.java
|   |   |-- ListingSB.java
|   |   |-- SearchSB.java
|   |   `-- VerificationSB.java
|   |-- dao/
|   |   |-- DBConnection.java
|   |   |-- LandlordDAO.java
|   |   |-- ListingDAO.java
|   |   `-- UserDAO.java
|   |-- model/
|   |   |-- Admin.java
|   |   |-- Landlord.java
|   |   |-- Listing.java
|   |   |-- Student.java
|   |   `-- User.java
|   |-- servlet/
|   |   |-- AddListingServlet.java
|   |   |-- DeleteListingServlet.java
|   |   |-- FilterServlet.java
|   |   |-- LoginServlet.java
|   |   |-- LogoutServlet.java
|   |   |-- RegisterServlet.java
|   |   |-- RejectLandlordServlet.java
|   |   |-- SearchServlet.java
|   |   |-- UpdateListingServlet.java
|   |   |-- VerifyLandlordServlet.java
|   |   |-- ViewListingDetailsServlet.java
|   |   `-- ViewListingsServlet.java
|   `-- util/
|       |-- PasswordUtil.java
|       `-- SecurityUtil.java
|-- web/
|   |-- WEB-INF/
|   |   |-- glassfish-web.xml
|   |   `-- web.xml
|   |-- css/
|   |   `-- styling.css
|   |-- images/
|   |   |-- StudentResFinder logo.png
|   |   `-- StudentResFinder-logo-header.png
|   |-- accessDenied.jsp
|   |-- addListing.jsp
|   |-- adminDashboard.jsp
|   |-- editListing.jsp
|   |-- error.jsp
|   |-- index.jsp
|   |-- landlordDashboard.jsp
|   |-- login.jsp
|   |-- myListings.jsp
|   |-- register.jsp
|   |-- search.jsp
|   |-- searchResults.jsp
|   |-- studentDashboard.jsp
|   |-- verifyLandlords.jsp
|   `-- viewListing.jsp
|-- build.xml
|-- GITHUB_GUIDE.md
`-- README.md
```

## Application Flow

### Login

```text
login.jsp -> LoginServlet.do -> GlassFish file realm -> role redirect
```

### Registration

```text
register.jsp -> RegisterServlet.do -> AuthSB -> UserDAO / LandlordDAO -> login.jsp
```

### Search Listings

```text
index.jsp or search.jsp -> SearchServlet.do -> SearchSB -> ListingDAO -> searchResults.jsp
```

### Manage Listings

```text
landlordDashboard.jsp -> add/edit/delete servlet -> ListingSB -> ListingDAO -> myListings.jsp
```

### Verify Landlords

```text
adminDashboard.jsp -> verifyLandlords.jsp -> VerifyLandlordServlet or RejectLandlordServlet -> VerificationSB -> LandlordDAO
```

## How to Run Locally

1. Open the nested cloned project in NetBeans:

```text
StudentResFinderWebApp/StudentResFinderWebApp
```

2. Start Derby on port `1527`.

3. Create or connect to:

```text
StudentResFinderDB
```

4. Run:

```text
database/studentresfinder.sql
```

5. Start GlassFish.

6. Configure the file realm users and groups listed above.

7. Build and deploy from NetBeans, or run:

```text
ant clean dist
```

8. Open the deployed application. The local test deployment used:

```text
http://localhost:54586/StudentResFinderWebApp/
```

Your GlassFish may use another HTTP port, commonly `8080`.

## Build Notes

Generated folders are not part of source control:

- `build/`
- `dist/`

Rebuild them locally using Ant or NetBeans.

## Collaboration Rules

1. Pull before working:

```text
git pull origin main
```

2. Work on your assigned files where possible.

3. Do not rename folders without telling the group.

4. Commit with clear messages.

5. Push small changes often.

6. If the project breaks after your push, report it immediately.

## Summary

StudentResFinderWebApp is now a complete Java EE accommodation platform with MVC structure, Derby persistence, GlassFish security, role-based dashboards, landlord verification, listing management, search, and a modern responsive interface.
