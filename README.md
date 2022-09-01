# crud
crud-springboot

To configure the mysql:

1. In workbench, create a database `user` (CREATE DATABASE user;)
2. Add the password of the datasource (root) in applications.properties file.
3. Execute the command - `USE user;`
4. Execute the command - `SELECT * FROM table_users;` - To check the entries

## Basic Structure (with example)
`{
    "firstName": "Poojitha",
    "lastName": "Namini",
    "age": 21
}`


## End points

### POST

#### Add a user

End point: `"/signup"`

Has to provide the data for first name, last name, and age.

### GET

#### Get all users

End point: `"/users?page={pageno}&sortBy={attr}"`

This shows the particular page and can be sorted based on firstName, lastName and age.

End point: `"/users"`

To get the all users. This will show the first five users.
#### Get single user

End point: `"/user/{userId}"`

To get the specific user with the id.
### PUT

End point: `"/update/{userId}"`

Has to provide a json input with the updated values of every attribute

### PATCH

End point: `"/update/{userId}"`

Has to provide a json input with the updated values of the required attribute

### DELETE

End point: `"/delete/{userId}"`

The user with such id will be deleted.

  
  
