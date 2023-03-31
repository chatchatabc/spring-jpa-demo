# Spring JPA Demo

## Plans

- Getting Started
- Docker Setup
- Spring Boot Setup
- History and description of JPA
- Explain PagingAndSortingRepository
- Explain CrudRepository
- Explain JPARepository
- Explain Features
- Explain Annotations
- Explain Entities
- Explain Services
- Create User Examples
- Create Post Examples
- Relationships
    - One-to-One Relationship
    - One-to-Many Relationship
    - Many-to-Many Relationship
    - Many-to-One Relationship
- Directions
    - Unidirectional
    - Bidirectional
- ID Generation Strategies
    - UUID
    - Identity
- Application Properties
    - Database
    - Hibernate
- List of compatible databases
    - MySQL
    - PostgreSQL
- Pageable for Pagination
- Transactions
- Migrations
    - Auto
    - Manual
- Important of DTOs
    - Mapping
- Other methods
    - Rest
    - GraphQL
- JsonIgnores
- Fetch Types
    - Lazy
    - Eager
- Cascade Types
    - All
    - Merge
    - Persist
    - Refresh
    - Remove
    - Detach
- Query
    - Method
    - Language
- Versioning of APIs

## TODO

- [ ] Slidev Presentation
- [ ] Diagrams
    - [ ] Process Flow
    - [ ] Entity Relationship Diagram

## Features

### User

- Registration (`/api/auth/register`)
    - [x] Kotlin
    - [x] Java
- Login (`/api/auth/register`)
    - [x] Kotlin
    - [x] Java
- View Profile (`/api/user/profile`)
    - [x] Kotlin
    - [x] Java
- View Profile of other user (`/api/user/profile/view/{username}`)
    - [x] Kotlin
    - [x] Java
- Update Profile TRANSACTIONAL (`/api/user/profile/update`)
    - [x] Kotlin
    - [x] Java
- Change Password TRANSACTIONAL (`/api/user/profile/change-password`)
    - [x] Kotlin
    - [x] Java

### Post (Many-to-One)

- Create
    - [ ] Kotlin
    - [ ] Java
- Read
    - [ ] Kotlin
    - [ ] Java
- Update
    - [ ] Kotlin
    - [ ] Java
- Delete
    - [ ] Kotlin
    - [ ] Java

### Roles (Many-to-Many)

- Admin
    - [ ] Kotlin
    - [ ] Java
- User
    - [ ] Kotlin
    - [ ] Java

### Country (One-to-Many)

- Get with Pagination and Sorting (`/api/country/get`)
    - [x] Kotlin
    - [x] Java
- Get all users assigned to country with Pagination and Sorting (`/api/country/get/{countryId}`)
    - [x] Kotlin
    - [x] Java
- Create (`/api/country/create`)
    - [x] Kotlin
    - [x] Java
- Update (`/api/country/update/{countryId}`)
    - [x] Kotlin
    - [x] Java
- Assign Country to User (`/api/country/assign`)
    - [x] Kotlin
    - [x] Java
- Remove Assigned Country to User (`/api/country/unassign`)
    - [x] Kotlin
    - [x] Java
- Delete if not in use (`/api/country/delete`)
    - [x] Kotlin
    - [x] Java

### Passport (One-to-One)

- Create
    - [x] Kotlin
    - [ ] Java
- Get Passport with Pagination and Sorting
    - [x] Kotlin
    - [ ] Java
- Get by User
    - [x] Kotlin
    - [ ] Java
- Update
    - [ ] Kotlin
    - [ ] Java
- Delete
    - [x] Kotlin
    - [ ] Java

### Others

- [ ] Add more documentation
- [ ] Unit Testing

## References