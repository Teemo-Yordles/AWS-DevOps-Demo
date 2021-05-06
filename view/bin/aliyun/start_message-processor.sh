#!/bin/sh
view_home=/opt/app/view
view_log=${view_home}/logs
nohup java -jar ${view_home}/view-message-processor-2.0.1-snapshot.jar --spring.profiles.active=alicloud 1>${view_log}/message-processor.log 2>&1 &

