#!/bin/bash
#
# Basic installation script for this Roller plugin
# ROLLER_INSTALL_DIR=/usr/local/tomcat/webapps/roller ./install.sh
#
if [ -n "${ROLLER_INSTALL_DIR+x}" ]; then
    echo 'Starting install...'
    cp -v dist/roller-defensio.jar $ROLLER_INSTALL_DIR/WEB-INF/lib/
    echo ''
    echo 'Files are copied to your Roller installation directory.'
    echo 'Please edit your roller-custom.properties, and restart the web application.'
else
    echo 'You need to set the ROLLER_INSTALL_DIR environment variable.'
fi
