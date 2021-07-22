# Cloud Storage

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.  

## About the project

1. The back-end with Spring Boot
2. The front-end with Thymeleaf
3. Application tests with Selenium

### The Back-End

The back-end is all about security and connecting the front-end to database data and actions. 

1. Managing user access with Spring Security
2. Handling front-end calls with controllers
3. Making calls to the database with MyBatis mappers


### The Front-End

1. Login page
2. Sign Up page
3. Home page


 i. Files
  - The user can to upload files and see any files they previously uploaded. 
  - The user can view/download or delete previously-uploaded files.
  - Any errors related to file actions should be displayed. For example, a user cannot upload two files with the same name


 ii. Notes
  - The user can create notes and see a list of the notes they have previously created.
  - The user can edit or delete previously-created notes.

 iii. Credentials
 - The user can store credentials for specific websites and see a list of the credentials they've previously stored.
 - The user can view/edit or delete individual credentials. When the user views the credential, they can see the unencrypted password.
