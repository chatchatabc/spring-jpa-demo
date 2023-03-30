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

## TODO

- [ ] Slidev Presentation
- [ ] Diagrams
    - [ ] Process Flow
    - [ ] Entity Relationship Diagram

### Java Version

- [ ] User
    - [x] Registration (`/api/auth/register`)
    - [x] Login (`/api/auth/register`)
    - [x] View Profile (`/api/user/profile`)
    - [x] View Profile of other user (`/api/user/profile/view/{username}`)
    - [x] Update Profile TRANSACTIONAL (`/api/user/profile/update`)
    - [x] Change Password TRANSACTIONAL (`/api/user/profile/change-password`)
- [ ] Post (Many-to-One)
    - [ ] Create
    - [ ] View
    - [ ] Update
    - [ ] Delete
- [ ] Roles (Many-to-Many)
    - [ ] Admin
    - [ ] User
- [ ] Country (One-to-Many)
    - [ ] Get
    - [ ] Create
    - [ ] Update
    - [ ] Assign country to user
    - [ ] Delete
- [ ] Passport (One-to-One)
    - [ ] Get
    - [ ] Create
    - [ ] Update
    - [ ] Delete
- [ ] Unit Testing

### Kotlin Version

- [ ] User
    - [x] Registration (`/api/auth/register`)
    - [x] Login (`/api/auth/register`)
    - [x] View Profile (`/api/user/profile`)
    - [x] View Profile of other user (`/api/user/profile/view/{username}`)
    - [x] Update Profile TRANSACTIONAL (`/api/user/profile/update`)
    - [x] Change Password TRANSACTIONAL (`/api/user/profile/change-password`)
- [ ] Post (Many-to-One)
    - [ ] Create
    - [ ] View
    - [ ] Update
    - [ ] Delete
- [ ] Roles (Many-to-Many)
    - [ ] Admin
    - [ ] User
- [ ] Country (One-to-Many)
    - [x] Get
    - [x] Create
    - [x] Update
    - [ ] Assign Country to User
    - [ ] Delete
- [ ] Passport (One-to-One)
    - [ ] Get
    - [ ] Create
    - [ ] Update
    - [ ] Delete
- [ ] Unit Testing

### Others

- [ ] Add more documentation

## References