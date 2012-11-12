Taskboard
=========


Installation
------------


- Clone or fork the github repository, e.g. `git clone git://github.com/justjoheinz/oosetaskboard.git`
- Install the GWT eclipse plugin from `https://developers.google.com/eclipse/docs/download`
- Download the GWT SDK libraries seperately, e.g. `https://developers.google.com/web-toolkit/download`

In the `build` directory, create a file `local.properties`:

    ivy.home = /Users/foo/Documents/Tools/ivy
    gwt.home = /Users/foo/Projects/gwt-2.5.0/

and point the variables to the directories where you want your project libraries downloaded and where you have installed GWT.

In eclipse, go to the Preferences and add your local GWT installation to the set of GWT installations at
    
    Google > Web Toolkit

Go to the project settings and select your GWT SDK as the preferred SDK:

    Google > Web Toolkit > Use specific SDK

Open `build/ivy-build.xml` and execute the task `download`

