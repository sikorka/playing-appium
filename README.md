Intro
=====

Playing Appium with Tigerspike's `FlickrBrowser` app. 


Prerequisites
=============

Mac OSX El Capitan and the following things installed: 

 - Appium 1.5.3
 - Maven 3
 - Java 1.8


Run
===

  1. Launch Appium for iOS 
  2. Execute from command line 

    mvn clean test



Known issues
------------

- Even though in firewall settings the app accepts incoming connections, still a popup keeps being displayed. 
Workaround: disable firewall (not recommended). 

- `searchTextField.submit()` does not work: `Method is not implemented` exception thrown. 
Workaround: using `searchTextField.sendKeys("\n")` instead. 

- Using `PageFactory`'s search mechanisms to get title text does not work. 
Workaround: using driver's regular `findElementByXpath()` instead. 


Work left
---------

 - Cucumber tests 
