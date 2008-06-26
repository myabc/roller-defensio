Roller Defensio Plugin
======================

Prerequisites
-------------

* JDK 1.5 or greater.
* Roller 4.0 or greater.

Copyright and Licensing
-----------------------

The source code to this plugin is released under an **MIT License**.

> Copyright 2008 Alex Coles.

A copy of the License may be found in the MIT-LICENSE file.


Additionally, this plugin bundles [Defensio for Java][http://defensio.com/downloads/java/], written by
[Remy Giard][http://blog.remygiard.com/projects/]. Code in the com.defensio.*
packages is copyrighted as follows:

> Copyright 2007 Remy Giard


Installation
------------

### Install files

You can use the installer script provided to perform step 1 for you:
    `ROLLER_INSTALL_DIR=/usr/local/tomcat/webapps/roller ./install.sh`

1.  Copy the following libraries to your `ROLLER_INSTALL_DIR/WEB-INF/lib`:
    *  `roller-defensio.jar`: _this plugin_

### Configuration

2.  Edit your `roller-custom.properties` with your favourite editor. If you
    installed Roller to Tomcat for example, your Roller configuration will
    most likely be found in `$CATALINA_HOME/common/classes/roller-custom.properties`.

    You'll need to go to http://defensio.com/ and get an API key. Once you've
    done that, set the following property in your roller-custom.properties:

    `comment.validator.defensio.key=<get one at defensio.com>`

    and add the DefensioCommentValdiator to the list of comment validators:

    `comment.validator.classnames=\
    org.apache.roller.weblogger.ui.rendering.plugins.comments.BlacklistCommentValidator,\
    org.apache.roller.weblogger.ui.rendering.plugins.comments.ExcessLinksCommentValidator,\
    org.apache.roller.weblogger.ui.rendering.plugins.comments.ExcessSizeCommentValidator,\
    com.ikonoklastik.roller.ui.plugins.comments.defensio.DefensioCommentValidator`

3.  Once you're done, restart your Roller web application.

### Building the Source

This plugin was developed with the NetBeans IDE. A NetBeans project is included
along with the source code.

To compile the Roller Markdown plugin, you'll need to download either the Roller
source or binaries:

    wget http://apache.mult	idist.com/roller/roller-4/v4.0.0/bin/apache-roller-4.0.zip
    unzip apache-roller-4.0.zip

The plugin has the following dependencies, which you will need to include in
your compile path:

* roller-business
* roller-core
* roller-web
* commons-httpclient
* commons-logging

_More detailed instructions for building the source will be added._

Support
-------

Use is at your own risk. Comments, feedback and patches are welcome though. You
can contact the developer at <alex@alexcolesportfolio.com>.
