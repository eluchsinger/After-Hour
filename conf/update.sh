#!/usr/bin/env bash
echo ---------------- >> update.log
date '+%A %W %Y %X' >> update.log
git pull git@github.com:eluchsinger/After-Hour.git >> update.log