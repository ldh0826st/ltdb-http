#!/bin/bash

host=127.0.0.1
port=8080

query_url="http://$host:$port/query"
ingest_url="http://$host:$port/ingest"

echo "-------------------------------------"
url="${ingest_url}/tables"
echo "$url (GET): Get Tables"
curl --location --request GET $url
echo ""
echo "-------------------------------------"
url="${ingest_url}/tables/cam"
echo "$url (GET): Get Table Details"
curl --location --request GET $url
echo ""
echo "-------------------------------------"
url="${ingest_url}/modules"
echo "$url (GET): Get Modules"
curl --location --request GET $url
echo ""
echo "-------------------------------------"
url="${ingest_url}/modules/cam"
echo "$url (GET): Get Module Details"
curl --location --request GET $url
echo ""
echo "-------------------------------------"
url="${query_url}"
echo "$url (POST): Query Rows"
curl --location --request POST $url --header "Content-Type: text/plain" --data "SELECT * FROM cam LIMIT 2"
echo ""
