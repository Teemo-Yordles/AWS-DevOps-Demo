#!/bin/sh
view_home=/opt/app/view

${view_home}/bin/start_aodb-interface.sh
${view_home}/bin/start_message-processor.sh
${view_home}/bin/start_gateway.sh
${view_home}/bin/start_admin.sh
${view_home}/bin/start_portal.sh
${view_home}/bin/start_websocket.sh
