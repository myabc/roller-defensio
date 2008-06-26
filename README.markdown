Roller Defensio Plugin
======================

Prerequisites
-------------

* JDK 1.5 or greater.
* Roller 4.0 or greater.

Copyright and Licensing
-----------------------

The plugin source code is released under an **MIT License**.

> Copyright 2008 Alex Coles.

A copy of the License may be found in the MIT-LICENSE file.

Additionally, this plugin bundles the following software:

* Defensio

Installation
------------

### Install files

You can use the installer script provided to perform step 1 for you:
    `ROLLER_INSTALL_DIR=/usr/local/tomcat/webapps/roller ./install.sh`

1.  Copy the following libraries to your `ROLLER_INSTALL_DIR/WEB-INF/lib`:
    *  `roller-defensio.jar`: _this plugin_

### Configuration

4.  Edit your `roller-custom.properties` with your favourite editor. If you
    installed Roller to Tomcat for example, your Roller configuration will
    most likely be found in `$CATALINA_HOME/common/classes/roller-custom.properties`.

### Building the Source

This plugin was developed with the NetBeans IDE. A NetBeans project is included
along with the source code.

To compile the Roller Markdown plugin, you'll need to download either the Roller
source or binaries:

    wget http://apache.mult	idist.com/roller/roller-4/v4.0.0/bin/apache-roller-4.0.zip
    unzip apache-roller-4.0.zip

The plugin has the following dependencies, which you will need to include in
your compile path:

* roller-core
* roller-business
* roller-web
* commons-lang
* commons-logging

_More detailed instructions for building the source will be added._

Support
-------

Use is at your own risk. Comments, feedback and patches are welcome though. You
can contact the developer at <alex@alexcolesportfolio.com>.
