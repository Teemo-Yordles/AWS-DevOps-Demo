#!/bin/sh
view_home=/opt/app/view
view_log=/var/log/view
nohup java -jar ${view_home}/view-admin-2.0.1-snapshot.jar --spring.profiles.active=test_office 1>${view_log}/admin.log 2>&1 &

