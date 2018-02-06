#!/bin/bash
export GRAILS_HOME=/usr/lib/grails/grails-3.3.2
export PATH="$GRAILS_HOME/bin:$PATH"
grails $2 war
unzip -d $1/ROOT build/libs/*.war