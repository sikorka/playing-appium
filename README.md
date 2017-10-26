Intro
=====

Playing Appium with Tigerspike's `FlickrBrowser` app. 

This repo contains iOS UI tests comparying [Flickr photos endpoint](https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1&tags=london) with what app returns. 
It contains JUnit tests for rest. 


Prerequisites
=============

Mac OSX El Capitan and the following things installed: 

 - Appium 1.5.3
 - Maven 3
 - Java 1.7

It might work with higher version of Appium and OSX, however not checked. 


Run
===

Setup your capabilities in [app/app.properties](app/app.properties).

  1. Launch Appium for iOS 
  2. Execute from command line 

    mvn clean test

To execute only non-buggy scenarios run: 

    mvn clean test -Dcucumber.options="--tags ~@ignored"


Known issues
------------

- Even though in firewall settings the app accepts incoming connections, still a popup keeps being displayed. 
Workaround: disable firewall (not recommended permanently). 

- The given [Flickr photos endpoint](https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1&tags=london) 
can't be trusted. Given test data which was entered and is not modified, I should be getting that same test data back in response - if 
anything, in a wider set - but it happens it does not come back at all.

- `searchTextField.submit()` does not work: `Method is not implemented` exception thrown. 
Workaround: using `searchTextField.sendKeys("\n")` instead. 

- Using `PageFactory`'s search mechanisms to get title text does not work. 
Workaround: using driver's regular `findElementByXpath()` instead. 


