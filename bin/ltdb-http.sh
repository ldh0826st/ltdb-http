#!/bin/bash
#
# ltdb-http
#
## ltdb-http installation directory
SCRIPTPATH=$(cd "$(dirname "$0")" && pwd)
export LTDB_HTTP_HOME=$(cd $SCRIPTPATH/.. && pwd)

##  Path to the pid, runnning info file
pidfile=${PIDFILE-${LTDB_HTTP_HOME}/ltdb-http.pid}
lockfile=${LOCKFILE-${LTDB_HTTP_HOME}/ltdb-http.lock}
RETVAL=0

function classpath() {
  CLASSPATH=""
  for jar in "${LTDB_HTTP_HOME}"/lib/*
  do
    if [[ "$jar" =~ com[.]stlogic[.]ltdb-* && *[.]jar ]];
      then
        CLASSPATH="$jar"${CLASSPATH}
    elif [[ "$jar" == *.jar ]];
      then
        CLASSPATH=${CLASSPATH}:"$jar"
    fi
  done
#  for jar in $(find ${HADOOP_HOME}/share/hadoop/common -name '*.jar'); do CLASSPATH=${CLASSPATH}:"$jar"; done
#  for jar in $(find ${HADOOP_HOME}/share/hadoop/yarn -name '*.jar'); do CLASSPATH=${CLASSPATH}:"$jar"; done
#  for jar in $(find ${HADOOP_HOME}/share/hadoop/hdfs -name '*.jar'); do CLASSPATH=${CLASSPATH}:"$jar"; done
#  for jar in $(find ${HADOOP_HOME}/share/hadoop/mapreduce -name '*.jar'); do CLASSPATH=${CLASSPATH}:"$jar"; done
}

case "$1" in
 start)
        PID_NUM=`ps -eaf|grep ltdb-http|grep java|grep -v grep|wc -l`
        if [ $PID_NUM -eq 1 ];
          then
            echo "Already started."
            exit 1
        fi
        echo "Starting LTDB HTTP"
        classpath
        (${JAVA_HOME}/bin/java -Xmx4096m -cp ${CLASSPATH} -Droot.path=${LTDB_HTTP_HOME} com.stlogic.ltdb.http.LTDBServer) &
        PID=`ps -eaf|grep ltdb-http|grep java|grep -v grep|awk '{print $2}'`
        RETVAL=$?
        [ $RETVAL = 0 ] && touch ${lockfile}
        [ $RETVAL = 0 ] && echo "${PID}" > ${pidfile}
        ;;
 stop)
        PID_NUM=`ps -eaf|grep ltdb-http|grep java|grep -v grep|wc -l`
        if [ $PID_NUM -eq 0 ];
          then
            echo "Already stopped."
            exit 1
        fi
        echo "Stopping LTDB HTTP"
        PID=`ps -eaf|grep ltdb-http|grep java|grep -v grep|awk '{print $2}'`
        kill -9 ${PID}
        RETVAL=$?
        [ $RETVAL = 0 ] && rm -f ${lockfile}
        [ $RETVAL = 0 ] && rm -f ${pidfile}
        ;;
 restart)
        $0 stop
        $0 start
        ;;
 *)
        echo $"Usage: $0 {start|stop|restart}"
        exit 1
        ;;
esac
exit $RETVAL
