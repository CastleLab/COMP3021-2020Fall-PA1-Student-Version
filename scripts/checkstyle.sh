#!/bin/bash

java -jar ../lib/checkstyle-8.36-all.jar \
  -c ./style_checks.xml \
  com.puppycrawl.tools.checkstyle.gui.Main \
  ../src/main/java/castle/comp3021/assignment/*
