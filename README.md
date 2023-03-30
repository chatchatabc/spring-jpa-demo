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

## TODO

- [ ] Slidev Presentation
- [ ] Diagrams
    - [ ] Process Flow
    - [ ] Entity Relationship Diagram

## Features

### User

- [x] Registration (`/api/auth/register`)
    - [x] Kotlin
    - [x] Java
- [x] Login (`/api/auth/register`)
    - [x] Kotlin
    - [x] Java
- [x] View Profile (`/api/user/profile`)
    - [x] Kotlin
    - [x] Java
- [x] View Profile of other user (`/api/user/profile/view/{username}`)
    - [x] Kotlin
    - [x] Java
- [x] Update Profile TRANSACTIONAL (`/api/user/profile/update`)
    - [x] Kotlin
    - [x] Java
- [x] Change Password TRANSACTIONAL (`/api/user/profile/change-password`)
    - [x] Kotlin
    - [x] Java

### Post (Many-to-One)

- [ ] Create
    - [ ] Kotlin
    - [ ] Java
- [ ] View
    - [ ] Kotlin
    - [ ] Java
- [ ] Update
    - [ ] Kotlin
    - [ ] Java
- [ ] Delete
    - [ ] Kotlin
    - [ ] Java

### Roles (Many-to-Many)

- [ ] Admin
    - [ ] Kotlin
    - [ ] Java
- [ ] User
    - [ ] Kotlin
    - [ ] Java

### Country (One-to-Many)

- [ ] Get with Pagination and Sorting (`/api/country/get`)
    - [x] Kotlin
    - [ ] Java
- [ ] Get all users assigned to country with Pagination and Sorting
    - [x] Kotlin
    - [ ] Java
- [ ] Create (`/api/country/create`)
    - [x] Kotlin
    - [ ] Java
- [ ] Update (`/api/country/update/{countryId}`)
    - [x] Kotlin
    - [ ] Java
- [ ] Assign Country to User (`/api/country/assign`)
    - [x] Kotlin
    - [ ] Java
- [ ] Remove Assigned Country to User (`/api/country/unassign`)
    - [x] Kotlin
    - [ ] Java
- [ ] Delete if not in use (`/api/country/delete`)
    - [x] Kotlin
    - [ ] Java

### Passport (One-to-One)

- [ ] Get
    - [ ] Kotlin
    - [ ] Java
- [ ] Create
    - [ ] Kotlin
    - [ ] Java
- [ ] Update
    - [ ] Kotlin
    - [ ] Java
- [ ] Delete
    - [ ] Kotlin
    - [ ] Java

### Others

- [ ] Unit Testing
- [ ] Add more documentation

## References