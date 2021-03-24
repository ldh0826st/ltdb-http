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
