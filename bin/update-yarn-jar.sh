#!/usr/bin/env bash

SCRIPTPATH=$(cd "$(dirname "$0")" && pwd)
export LTDB_HTTP_HOME=$(cd $SCRIPTPATH/.. && pwd)

. "${LTDB_HTTP_HOME}/bin/ltdb-http-env.sh"

while [[ $# -gt 0 ]]
do
  key="$1"
  case $key in
    --hadoop-conf-dir)
    HADOOP_CONF_DIR="$2"
    shift
    shift
    ;;
    *)
    shift
    ;;
  esac
done

LTDB_HTTP_JAR=""
for jar in "${LTDB_HTTP_HOME}"/lib/*
do
  if [[ "$jar" =~ com[.]stlogic[.]ltdb-* && *[.]jar ]];
    then
      LTDB_HTTP_JAR="$jar"
  fi
done

SERVER_CONF="${LTDB_HTTP_HOME}/conf/ltdb-server.conf"

if [ -f "$SERVER_CONF" ]
then
  sed 's/\r$//' $SERVER_CONF > "${SERVER_CONF}_"

  while IFS='=' read -r key value
  do
    key=$(echo $key | tr '.' '_' | tr '-' '_')
    if [ ! -z "$value" ]
    then
      value=$(echo $value | xargs)
      eval ${key}=\${value}
    fi
  done < "${SERVER_CONF}_"
  rm -rf "${SERVER_CONF}_"

  IFS='/' read -r -a array <<< "$ltdb_spark_yarn_jars"
  HDFS_PATH=
  for (( i=0; i < ${#array[@]}-1; i++ ));
  do
    HDFS_PATH="${HDFS_PATH}${array[$i]}/"
  done

  JAR="${array[${#array[@]}-1]}"

  zip -uj ${LTDB_HTTP_JAR} ${HADOOP_CONF_DIR}/core-site.xml /
  zip -uj ${LTDB_HTTP_JAR} ${HADOOP_CONF_DIR}/hdfs-site.xml /
  zip -uj ${LTDB_HTTP_JAR} ${HADOOP_CONF_DIR}/yarn-site.xml /

  zip -uj ${LTDB_HTTP_HOME}/${JAR} ${HADOOP_CONF_DIR}/core-site.xml /
  zip -uj ${LTDB_HTTP_HOME}/${JAR} ${HADOOP_CONF_DIR}/hdfs-site.xml /
  zip -uj ${LTDB_HTTP_HOME}/${JAR} ${HADOOP_CONF_DIR}/yarn-site.xml /

  hadoop fs -rm -f -skipTrash ${ltdb_spark_yarn_jars}
  hadoop fs -put ${LTDB_HTTP_HOME}/${JAR} ${HDFS_PATH}
  echo "Updated ${ltdb_spark_yarn_jars}"
else
  echo "$file not found."
fi