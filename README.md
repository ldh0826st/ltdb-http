# LTDB HTTP

-------------------------
* LTDB HTTP Server 는 LightningDB 에 데이터를 적재(Ingest)하고 질의(Query)할 수 있는 HTTP 서비스
* RESTFul, Thrift 서비스를 하기 위해 Jetty 와 Scalatra 기반으로 구현되어 있고
* LightningDB 에 연결하기 위해 자체 SparkSession 관리와 Local Hive Metastore 를 사용

## Building

-------------------------
mvn clean package -DskipTests -P release,dist,tgz -Dsite-spec=``사이트명``

* 사이트명은 사이트별 설정을 가르키는 것으로 서비스 포트, Spark 설정, Hadoop 관련 설정 등이 포함
* conf/site-specs 폴더 참조
```bash
예) mvn clean package -DskipTests -P release-yarn,dist-yarn,tgz -Dsite-spec=fbg01
```

## Installation

-------------------------
Build 후 target 폴더의 ltdb-http-1.0.``timestamp``.tar.gz 파일을 압축 해제

아래 설정에 맞게 ltdb-http-1.0-SNAPSHOT-with-deps.jar 파일을 업로드 필요
```
ltdb.spark.yarn.jars=hdfs://BPP-TVIEW-AIOPS-SEARCH01:9001/tmp/ltdb-http-1.0-SNAPSHOT-with-deps.jar
```

## Configuration

-------------------------
### 1) ltdb-http-env.sh

압축 해제한 폴더의 bin/ltdb-http-env.sh 수정

HADOOP_CONF_DIR 설정

```bash
예) export HADOOP_CONF_DIR=/home/nvkvs/stlogic/hadoop-2.7.3/etc/hadoop 
```

### 2) ltdb-server.conf

압축 해제한 폴더의 conf/ltdb-server.conf 수정

|Key|Default Value|Description|
|:---|:---|:---|
|ltdb.http.host|0.0.0.0|IP|
|ltdb.http.port|8080|Port|
|ltdb.http.request-log-retain.days|5|http request log 유지 기간(일)|
|ltdb.http.request-log-retain.days|5|http request log 유지 기간(일)|
|ltdb.spark.master|local[*]|Spark 실행 타입 (local[*], yarn)|
|ltdb.spark.submit.deployMode|client|Spark 실행 타입이 yarn 일 때 사용|
|ltdb.spark.hadoopUserName|yarn|Spark App 실행 Username|
|ltdb.spark.*|-|기타 Spark Config는 ltdb.spark. 을 prefix로 사용|


## Using

-------------------------

### 1) update-yarn-jar.sh
yarn 사용 시 yarn jar 파일 배포

압축 해제한 폴더의 bin/update-yarn-jar.sh 실행

### 1) ltdb-http.sh
압축 해제한 폴더의 bin/ltdb-http.sh 실행

```bash
예) ./bin/ltdb-http.sh [start|stop|restart]
```


## General

-------------------------

### Table 생성, 목록 조회, Table 상세 정보 조회
* Create Table
  * URL: http://``HOST``:``PORT``/query
  * Method: POST
  * cURL
    ```bash
    curl --location --request POST $url --header "Content-Type: text/plain" --data "CREATE TABLE IF NOT EXISTS cam (ID STRING, BBox ARRAY<INT>, Head ARRAY<INT>, Keypoints ARRAY<INT>) USING r2 OPTIONS (host 'st-dstb2-00', port 18100, table '104', mode 'nvkvs', partitions 'ID', rowstore 'false', at_least_one_partition_enabled 'no')"
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/query";
    const query = "CREATE TABLE IF NOT EXISTS cam (ID STRING, BBox ARRAY<INT>, Head ARRAY<INT>, Keypoints ARRAY<INT>) USING r2 OPTIONS (host 'st-dstb2-00', port 18100, table '104', mode 'nvkvs', partitions 'ID', rowstore 'false', at_least_one_partition_enabled 'no')"
    fetch(url, {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        body: query
    })
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```
* Get Table List
  * URL: http://``HOST``:``PORT``/ingest/tables
  * Method: GET
  * cURL
    ```bash
    curl --location --request GET $url
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/ingest/tables";
    fetch(url)
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```
* Get Table Details
  * URL: http://``HOST``:``PORT``/ingest/tables/``TABLE``
  * Method: GET
  * cURL
    ```bash
    curl --location --request GET $url
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/ingest/tables/" + tableName;
    fetch(url)
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```

## Ingest

-------------------------
### Ingest Module 목록, 상세 정보, 데이터 적재 
* Get Ingest Module List
  * URL: http://``HOST``:``PORT``/ingest/modules
  * Method: GET
  * cURL
    ```bash
    curl --location --request GET $url
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/ingest/modules";
    fetch(url)
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```
* Get Ingest Module Details
  * URL: http://``HOST``:``PORT``/ingest/modules/``MODULE``
  * Method: GET
  * cURL
    ```bash
    curl --location --request GET $url
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/ingest/modules/" + moduleName;
    fetch(url)
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```
* Put Ingest Module
  * URL: http://``HOST``:``PORT``/ingest/modules/``MODULE``
  * Method: PUT
  * cURL
    ```bash
    curl --location --request PUT $url --header "Content-Type: text/plain" --data "$moduleContents"
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/ingest/modules/" + moduleName;
    const moduleContents = ...
    fetch(url, {
        method: "PUT",
        mode: "cors",
        cache: "no-cache",
        body: moduleContents
    })
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```
* Ingest Rows
  * URL: http://``HOST``:``PORT``/ingest/``MODULE``/``TABLE``
  * Method: PUT
  * cURL
    ```bash
    curl --location --request PUT $url --header "Content-Type: text/plain" --upload-file ./data/cam3_sample.json
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/ingest/" + moduleName + "/" + tableName;
    const data = ...
    fetch(url, {
        method: "PUT",
        mode: "cors",
        cache: "no-cache",
        body: data
    })
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```

## Query

-------------------------
* Query
  * URL: http://``HOST``:``PORT``/query
  * Method: POST
  * cURL
    ```bash
    curl --location --request POST $url --header "Content-Type: text/plain" --data "$query"
    ```
  * Javascript
    ```javascript
    const url = "http://" + host + ":" + port + "/query";
    const query = ...
    fetch(url, {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        body: query
    })
    .then(function (response) {
        return response.json()
    })
    .then(function (result) {
        console.log(result);
    });
    ```

## Extending Ingest Module (JSON 기준)

-------------------------
#### 1. Ingest Module Class 상속(com.stlogic.ltdb.http.IngestModule)
#### 2. Class 형태로 Data 구조 정의
#### 3. Parse Method 구현

#### 예)
```json
{
    "image": {
        "identifier": "cam3.jpg",
        "imsize": [ 1920, 1080 ]
    },
    "persons": [
        {
            "ID": 1,
            "BBox": [ 1238, 635, 1506, 1024 ],
            "Head": [ 1340, 636, 1439, 759 ],
            "Keypoints": [ 1427, 749, 2, 1366, ... ]
        },
        {
            "ID": 2,
            "BBox": [734, 364, 894, 636 ],
            "Head": [ 831, 365, 880, 416 ],
            "Keypoints": [ 862, 422, 2, 830, ... ]
        },
        ...
    ]
}
```

```scala
import com.stlogic.ltdb.http.IngestModule
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

class Cam extends IngestModule {

  import IngestModule._

  class Image(var identifier: String, var imsize: Array[Int])

  class Person(var ID: String, var BBox: Array[Int], var Head: Array[Int], var Keypoints: Array[Int])

  class Data(var image: Image, var persons: Array[Person])

  override def parse(schema: StructType, data: String): Array[Row] = {
    val parsed: Data = GSON.fromJson(data, classOf[Data])
    parsed.persons.map(person => {
      toRow(schema, person)
    })
  }
}

scala.reflect.classTag[Cam].runtimeClass
```

# Python - Thrift

-------------------------
## Building

-------------------------
mvn clean package -DskipTests -P python

## Installation

-------------------------
Build 후 python 폴더의 omnisci_thrift-``version``.tar.gz 파일을 python pip 로 설치
```bash
예) python -m pip install omnisci_thrift-1.0.dev0.tar.gz
```

## Using

-------------------------
### General
python project 내에서 다음과 같은 코드 구현
```python
from datetime import datetime

from thrift.protocol import TJSONProtocol
from thrift.transport import THttpClient
from thrift.transport import TTransport

from omnisci.common.ttypes import TDatumType
from omnisci.mapd import MapD

if __name__ == '__main__':
    transport = THttpClient.THttpClient('http://127.0.0.1:8080')
    transport = TTransport.TBufferedTransport(transport)
    protocol = TJSONProtocol.TJSONProtocol(transport)

    client = MapD.Client(protocol)
    transport.open()

    session = client.connect("ltdb", "ltdb", "default")
    print('session: {}'.format(session))

    tables = client.get_tables(session)
    print(tables)

    for table_name in tables:
        table = client.get_table_details(session, table_name)
        print('table: {}'.format(table_name))
        for column_type in table.row_desc:
            print('\t{}: {}'.format(column_type.col_name, TDatumType._VALUES_TO_NAMES.get(column_type.col_type.type)))

    query_results = client.sql_execute(session, 'SELECT * FROM cam LIMIT 10', False, None, None, None)
    row_desc = query_results.row_set.row_desc
    rows = query_results.row_set.rows
    for row in rows:
        value = ''
        first = True
        for i in range(len(row.cols)):
            try:
                if not first:
                    value += '|'
                column_type = row_desc[i]
                column = row.cols[i]
                value += column_type.col_name + ': '
                type_name = TDatumType._VALUES_TO_NAMES.get(column_type.col_type.type)
                if column.is_null:
                    value += 'None'
                else:
                    if column_type.col_type.type == TDatumType.STR:
                        value += column.val.str_val
                    elif column_type.col_type.type == TDatumType.SMALLINT or \
                        column_type.col_type.type == TDatumType.INT or \
                            column_type.col_type.type == TDatumType.BIGINT:
                        value += str(column.val.str_val)
                    elif column_type.col_type.type == TDatumType.FLOAT or \
                            column_type.col_type.type == TDatumType.DOUBLE:
                        value += str(column.val.real_val)
                    elif column_type.col_type.type == TDatumType.BOOL:
                        value += str(column.val.int_val == 1)
                    elif column_type.col_type.type == TDatumType.DATE or \
                            column_type.col_type.type == TDatumType.TIMESTAMP:
                        value += str(datetime.datetime.fromtimestamp(column.val.int_val).strftime('%Y-%m-%d %H:%M:%S'))
                    elif column_type.col_type.type == TDatumType.POINT or \
                            column_type.col_type.type == TDatumType.GEOMETRY:
                        value += column.val.str_val
            finally:
                first = False
        print(value)

    transport.close()
```
### Pagination

페이징 처리의 경우 Spark LocalIterator를 사용하기 때문에 얼마만큼 끊어 받을 것인가(limit)에 대한 설정 추가.
response의 sequence number로 오류 체크가 가능하고, has next로 결과가 더 있는지 확인 가능.

```python
from datetime import datetime

from thrift.protocol import TJSONProtocol
from thrift.transport import THttpClient
from thrift.transport import TTransport

from omnisci.common.ttypes import TDatumType
from omnisci.mapd import MapD

if __name__ == '__main__':
    transport = THttpClient.THttpClient('http://fbg01:4762')
    transport = TTransport.TBufferedTransport(transport)
    protocol = TJSONProtocol.TJSONProtocol(transport)

    client = MapD.Client(protocol)
    transport.open()

    session = client.connect("ltdb", "ltdb", "default")
    print('session: {}'.format(session))

    tables = client.get_tables(session)
    print(tables)

    for table_name in tables:
        table = client.get_table_details(session, table_name)
        print('table: {}'.format(table_name))
        for column_type in table.row_desc:
            print('\t{}: {}'.format(column_type.col_name, TDatumType._VALUES_TO_NAMES.get(column_type.col_type.type)))

    sql = """select project_id, instance_id, feature_value, cropped_image_path from ltdb_instance2,
                 (select max(update_date) as latest_date, instance_id as latest_instance_id from ltdb_instance2
                     where model_id_is_not_null = true and model_id = 1 and (project_id = 5 OR project_id = 108) group by instance_id)
             where
                 update_date = latest_date and instance_id = latest_instance_id and model_id_is_not_null = true
                 and model_id = 1 and (project_id = 5 OR project_id = 108)"""
    done = False
    count = 0
    while done is False:
        query_results = client.sql_execute(session, sql, False, None, 30, None)
        row_desc = query_results.row_set.row_desc
        nonce = query_results.nonce
        strings = nonce.split(',')
        sequence = int(strings[0]) # sequence number
        done = 'false' == strings[1].lower() # has next
        rows = query_results.row_set.rows
        for row in rows:
            value = ''
            first = True
            for i in range(len(row.cols)):
                try:
                    if not first:
                        value += '|'
                    column_type = row_desc[i]
                    column = row.cols[i]
                    value += column_type.col_name + ': '
                    type_name = TDatumType._VALUES_TO_NAMES.get(column_type.col_type.type)
                    if column.is_null:
                        value += 'None'
                    else:
                        if column_type.col_type.type == TDatumType.STR:
                            value += column.val.str_val
                        elif column_type.col_type.type == TDatumType.SMALLINT or \
                            column_type.col_type.type == TDatumType.INT or \
                                column_type.col_type.type == TDatumType.BIGINT:
                            value += str(column.val.str_val)
                        elif column_type.col_type.type == TDatumType.FLOAT or \
                                column_type.col_type.type == TDatumType.DOUBLE:
                            value += str(column.val.real_val)
                        elif column_type.col_type.type == TDatumType.BOOL:
                            value += str(column.val.int_val == 1)
                        elif column_type.col_type.type == TDatumType.DATE or \
                                column_type.col_type.type == TDatumType.TIMESTAMP:
                            value += str(datetime.datetime.fromtimestamp(column.val.int_val).strftime('%Y-%m-%d %H:%M:%S'))
                        elif column_type.col_type.type == TDatumType.POINT or \
                                column_type.col_type.type == TDatumType.GEOMETRY:
                            value += column.val.str_val
                finally:
                    first = False
            print(value)
        count += len(rows)
        print('{}: {} rows fetched'.format(sequence, count))

    transport.close()
```

# VectorTile - Heatmap

-------------------------

mapbox gl 수정으로 vectortile request function 사용 가능.

examples의 heatmap.html 참고

![img.png](examples/img/heatmap.png)
