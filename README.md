# spring-security-6
practice spring security

# Back end
1. Start postgresql via docker of local

SQL inserts statement for roles
```sql
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

2. sql query to check db

```sql
select * from user_roles;
select * from tbl_users;
select * from tbl_roles;
```

3. edit resources/application.properteis

# Front end

Using practice-front end to test login and send request. (not yet implement sign up ui)

## Install
```bash
npm install express ejs express nodemon
```

## Run
with nodemon
```bash
npm run dev
```
**servce at**:
first step: create using using cli
```bash
http post localhost:8080/api/auth/signup \
username="user" \
password="useruser
```
- Login page: [http://localhost:3000](http://localhost:3000) (after login auto redirect to resource)
- Resource page: [http://localhost:3000/resource](http://localhost:3000/resource)

Login page:
<img src="https://prnt.sc/L642SXzJWkRC" alt="login form" />
