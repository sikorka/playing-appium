package com.tigerspike.flickrbrowserapp;

import com.tigerspike.flickrbrowserapp.screen.MainScreen;
import com.tigerspike.flickrbrowserapp.screen.PhotoScreen;

/**
 * Represetns <code>FlickrBrowser app</code>.
 */
public class App {
    MainScreen mainScreen;
    PhotoScreen photoScreen;

    public App() {
        mainScreen = new MainScreen();
        photoScreen = new PhotoScreen();
    }
}
