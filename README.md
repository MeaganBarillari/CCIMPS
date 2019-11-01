# CCIMPS
CS506 Fall 2019 Project\
Instructions:\
1.Download android studio\
2.Clone git repo\
3.Open cloned repo in android studio\
4.Checkout branch - geetika\
5.Compile and run application\
6.Register is functional, but we have made already made accounts for testing ease. Please feel free to register new accounts if you’d like\
7. Login info is provided below:\
  1)Login Info as a Customer: \
    Email : customer@ccimp.com\
    Password: 123\
  2)Login Info as a Business:\
    Email: business@ccimp.com\
    Password: 123\
  3)Login Info as a Supplier\
    Email: supplier@ccimp.com\
    Password: 123\
8.After you click the login button, you would go to different screens based on the type you used , and you could use the bottom navigation bar to switch to different screens. You should be able to click on the table entries, it will bring you to detail pages or menu lists with static information. Button functionality that corresponds to adding orders/requests/items are disabled.\
Using JUnit Test How to:\
We have implemented four unit tests for both login and register activities. To run the test. Go to the folder called com.example.ccimp(test) in Android Studio, which is under the same folder of our user interface. Right click on the test and run the test. The result will be shown in the Run window. \
login_Success: test if login using the correct email and password would show the message “login success”, which means the login information is correct\
Incorrect_email: test if login using the wrong email would show the message “login fail”\
Incorrect_password: test if login using the wrong password and correct email would show the message “login fail”\
register_Success: test if register using the information provided will show a message of “Register success” and write the registered user to the database \

