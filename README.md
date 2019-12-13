# CCIMPS
CS506 Fall 2019 Project\
\
Instructions:\
\
1.Download android studio\
\
2.Clone git repo\
\
3.Open cloned repo in android studio\
\
4.Checkout branch - geetika\
\
5.Compile and run application\
\
6.Register is functional, but we have made already made accounts for testing ease. Please feel free to register new accounts if you’d like\
\
7. Login info is provided below:\
  1)Login Info as a Customer: \
    Email : customer@ccimp.com\
    Password: 123\
    \
  2)Login Info as a Business:\
    Email: business@ccimp.com\
    Password: 123\
    \
  3)Login Info as a Supplier\
    Email: supplier@ccimp.com\
    Password: 123\
    \
8.After you click the login button, you would go to different screens based on the type you used , and you could use the bottom navigation bar to switch to different screens. You should be able to click on the table entries, it will bring you to detail pages or menu lists with static information. Button functionality that corresponds to adding orders/requests/items are disabled.

9.Running tests:
  1) Unit tests: In android studio, right click on the folder named com.example.ccimp (app/src/test/java/com.example.ccimp)    and select "Run tests in ccimp with coverage". This will run all the unit tests, and provide code coverage information.
  
  2) Instrumented tests: In android studio, right click on the folder named com.example.ccimp (app/src/androidTest/java/com.example.ccimp) and select "Run tests in com.example.ccimp". This will run all the instrumented tests in the emulator, and show the test results in the console. Note that the emulator must be open and active for these tests.
  
  3) Automatic Regression tests: In order to automatically run all the unit tests whenever a commit is pushed to a remote repository, place the following script in a file named pre-push, in .git/hooks:
    
#!/bin/bash \
CMD="./gradlew clean test" \
\
commits=`git log @{u}..` \
if [ -z "$commits" ]; then \
 exit 0 \
fi \
$CMD \
RESULT=$? \
if [ $RESULT -ne 0 ]; then \
 echo "failed $CMD" \
 exit 1 \
fi \
exit 0 \
  4) Firebase test labs testing: In order to evaluate the app performance in firebase test labs, follow the instructions for            performing a firebase robo test. When firebase says to upload the apk, click Build -> Build Bundle(s) / APK(s) -> Build APK(s) in android studio to generate the apk. This apk is found at app/builds/output/apk/debug/app-debug.apk
    
  
