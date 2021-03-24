#!/bin/bash

host=127.0.0.1
port=8080

query_url="http://$host:$port/query"
ingest_url="http://$host:$port/ingest"

echo "-------------------------------------"
url="${query_url}"
echo "$url (POST): Create Table"
curl --location --request POST $url --header "Content-Type: text/plain" --data "CREATE TABLE IF NOT EXISTS cam (ID STRING, BBox ARRAY<INT>, Head ARRAY<INT>, Keypoints ARRAY<INT>) USING r2 OPTIONS (host 'st-dstb2-00', port 18100, table '104', mode 'nvkvs', partitions 'ID', rowstore 'false', at_least_one_partition_enabled 'no')"
echo ""
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
url="${ingest_url}/cam/cam"
echo "$url (PUT): Insert Rows"
curl --location --request PUT $url --upload-file ./data/cam3_sample.json
echo ""
echo "-------------------------------------"
url="${query_url}"
echo "$url (POST): Query Rows"
curl --location --request POST $url --header "Content-Type: text/plain" --data "SELECT * FROM cam LIMIT 2"
echo ""
