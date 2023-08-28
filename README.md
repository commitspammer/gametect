# GameTect

## Dependencies

* Java 17
* npm
* MySQL

## Setup

1. Run MySQL Server on port 3306
2. Create MySQL 'gametectuser' user with password 'gametectuser'
3. Execute [scriptBD.sql](./scriptDB.sql) in MySQL to initialize 'gametect' database
4. Allow read and write access for 'gametectuser' in 'gametect' db.

## Execution

### Running

#### Spring backend

```
./gradlew bootRun       #LINUX
gradlew.bat bootRun     #WINDOWS
```
#### Vue.js frontend

```
npm install
npm run serve
```
