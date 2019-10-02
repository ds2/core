#!/usr/bin/env bash

find . -type d -name target -exec rm -rf {} \;
find . -type d -name build -exec rm -rf {} \;
find . -type d -name bin -exec rm -rf {} \;
find . -type d -name out -exec rm -rf {} \;
