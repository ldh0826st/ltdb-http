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
