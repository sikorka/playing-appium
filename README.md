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
============

- Even though in firewall settings the app accepts incoming connections, still a popup keeps being displayed. 

- Time to start the app with Appium with the described setup is usually >20 minutes. What a bummer. 

- `searchTextField.submit()` does not work. `Method is not implemented` exception thrown. Using `searchTextField.sendKeys("\n")` instead. 


Work left
=========

 - Iterating by titles in photos grid 
 - Cucumber tests 
 - Reading capabilit