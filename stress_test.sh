#!/bin/bash
while (true)
do
    curl --location --request POST '10.211.55.5:32750/queue/base/queue' --header 'Content-Type:application/json' --data-raw '{"firstName": "hao","lastName": "xu", "userId": "6771772"}'

done
