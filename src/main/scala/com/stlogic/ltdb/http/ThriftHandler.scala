package com.stlogic.ltdb.http

import com.google.common.collect.Lists
import com.skt.spark.r2.util.Logging
import com.stlogic.omnisci.thrift.calciteserver.TCompletionHint
import com.stlogic.omnisci.thrift.server._

import java.net.InetAddress
import java.nio.ByteBuffer
import java.util
import scala.util.Try

class ThriftHandler(ltdbServerConf: LTDBServerConf) extends Logging with MapD.Iface {

  import SparkService._
  import ThriftHandler._

  SparkService.init(ltdbServerConf)

  override def connect(user: String, passwd: String, dbname: String): String = {
    lock.lock()
    try {
      logInfo(s"connect: $user, $passwd, $dbname")
      String.valueOf(System.currentTimeMillis)
    } finally {
      lock.unlock()
    }
  }

  override def disconnect(session: String): Unit = logInfo(s"disconnect: $session")

  override def switch_database(session: String, dbname: String): Unit = logError(s"switch_database: $session, $dbname")

  private val serverStatus = new TServerStatus(true, "4.8.1", true, System.currentTimeMillis,
    "ltdb", HOST_NAME, true, TRole.SERVER)

  override def get_server_status(session: String) = {
    logInfo(s"get_server_status: $session")
    serverStatus
  }

  override def get_status(session: String): util.List[TServerStatus] = {
    logInfo(s"get_status: $session")
    Lists.newArrayList(serverStatus)
  }

  override def get_hardware_info(session: String): TClusterHardwareInfo = {
    logError(s"get_hardware_info: $session")
    null
  }

  override def get_tables(session: String): util.List[String] = {
    logInfo(s"get_tables: $session")
    SparkService.getTables()
  }

  override def get_physical_tables(session: String): util.List[String] = {
    logError(s"get_physical_tables: $session")
    null
  }

  override def get_views(session: String): util.List[String] = {
    logError(s"get_views: $session")
    null
  }

  override def get_tables_meta(session: String): util.List[TTableMeta] = {
    logError(s"get_tables_meta: $session")
    null
  }

  override def get_table_details(session: String, table_name: String): TTableDetails = {
    logInfo(s"get_table_details: $session, $table_name")
    SparkService.getTableDetail(table_name)
  }


  override def get_internal_table_details(session: String, table_name: String): TTableDetails = {
    logError(s"get_internal_table_details: $session, $table_name")
    null
  }

  override def get_users(session: String): util.List[String] = {
    logError(s"get_users: $session")
    null
  }

  override def get_databases(session: String): util.List[TDBInfo] = {
    logError(s"get_databases: $session")
    null
  }

  override def get_version(): String = {
    logError(s"get_version")
    null
  }

  override def start_heap_profile(session: String): Unit = {
    logError(s"start_heap_profile: $session")
  }

  override def stop_heap_profile(session: String): Unit = {
    logError(s"stop_heap_profile: $session")
  }

  override def get_heap_profile(session: String): String = {
    logError(s"get_heap_profile: $session")
    null
  }

  override def get_memory(session: String, memory_level: String): util.List[TNodeMemoryInfo] = {
    logError(s"get_memory: $session, $memory_level")
    null
  }

  override def clear_cpu_memory(session: String): Unit = {
    logError(s"clear_cpu_memory: $session")
  }

  override def clear_gpu_memory(session: String): Unit = {
    logError(s"clear_gpu_memory: $session")
  }

  override def set_table_epoch(session: String, db_id: Int, table_id: Int, new_epoch: Int): Unit = {
    logError(s"set_table_epoch: $session, $db_id, $table_id, $new_epoch")
  }

  override def set_table_epoch_by_name(session: String, table_name: String, new_epoch: Int): Unit = {
    logError(s"set_table_epoch_by_name: $session, $table_name, $new_epoch")
  }

  override def get_table_epoch(session: String, db_id: Int, table_id: Int): Int = {
    logError(s"get_table_epoch $session, $db_id, $table_id")
    -1
  }

  override def get_table_epoch_by_name(session: String, table_name: String): Int = {
    logError(s"get_table_epoch_by_name: $session, $table_name")
    -1
  }

  override def get_session_info(session: String): TSessionInfo = {
    logError(s"get_session_info: $session")
    null
  }

  override def sql_execute(session: String, query: String, column_format: Boolean, nonce: String, first_n: Int, at_most_n: Int): TQueryResult = {
    logInfo(s"sql_execute: $session, $query, $column_format, $nonce, $first_n, $at_most_n")
    SparkService.executeSql(query, column_format, nonce, first_n, at_most_n)
  }

  override def sql_execute_df(session: String, query: String, device_type: TDeviceType, device_id: Int, first_n: Int): TDataFrame = {
    logError(s"sql_execute_df: $session, $query, $device_type, $device_id, $first_n")
    null
  }

  override def sql_execute_gdf(session: String, query: String, device_id: Int, first_n: Int): TDataFrame = {
    logError(s"sql_execute_gdf: $session, $query, $device_id, $first_n")
    null
  }

  override def deallocate_df(session: String, df: TDataFrame, device_type: TDeviceType, device_id: Int): Unit = {
    logError(s"deallocate_df: $session, $df, $device_type, $device_id")
  }

  override def interrupt(session: String): Unit = {
    logError(s"interrupt: $session")
  }

  override def sql_validate(session: String, query: String): util.Map[String, TColumnType] = {
    logError(s"sql_validate: $session, $query")
    null
  }

  override def get_completion_hints(session: String, sql: String, cursor: Int): util.List[TCompletionHint] = {
    logError(s"get_completion_hints: $session, $sql, $cursor")
    null
  }

  override def set_execution_mode(session: String, mode: TExecuteMode): Unit = {
    logError(s"set_execution_mode: $session, $mode")
  }

  override def render_vega(session: String, widget_id: Long, vega_json: String, compression_level: Int, nonce: String): TRenderResult = {
    logError(s"render_vega: $session, $widget_id, $vega_json, $compression_level, $nonce")
    null
  }

  override def get_result_row_for_pixel(session: String, widget_id: Long, pixel: TPixel, table_col_names: util.Map[String, util.List[String]], column_format: Boolean, pixelRadius: Int, nonce: String): TPixelTableRowResult = {
    logError(s"get_result_row_for_pixel: $session, $widget_id, $pixel, $table_col_names, $column_format, $pixelRadius, $nonce")
    null
  }

  override def get_frontend_view(session: String, view_name: String): TFrontendView = {
    logError(s"get_frontend_view: $session, $view_name")
    null
  }

  override def get_frontend_views(session: String): util.List[TFrontendView] = {
    logError(s"get_frontend_views: $session")
    null
  }

  override def create_frontend_view(session: String, view_name: String, view_state: String, image_hash: String, view_metadata: String): Unit = {
    logError(s"create_frontend_view: $session, $view_name, $view_state, $image_hash, $view_metadata")
  }

  override def delete_frontend_view(session: String, view_name: String): Unit = {
    logError(s"delete_frontend_view: $session, $view_name")
  }

  override def get_dashboard(session: String, dashboard_id: Int): TDashboard = {
    logError(s"start_heap_profile: $session")
    null
  }

  override def get_dashboards(session: String): util.List[TDashboard] = {
    logError(s"get_dashboards: $session")
    null
  }

  override def create_dashboard(session: String, dashboard_name: String, dashboard_state: String, image_hash: String, dashboard_metadata: String): Int = {
    logError(s"create_dashboard: $session, $dashboard_name, $dashboard_state, $image_hash, $dashboard_metadata")
    -1
  }

  override def replace_dashboard(session: String, dashboard_id: Int, dashboard_name: String, dashboard_owner: String, dashboard_state: String, image_hash: String, dashboard_metadata: String): Unit = {
    logError(s"replace_dashboard: $session, $dashboard_id, $dashboard_name, $dashboard_owner, $dashboard_state, $image_hash, $dashboard_metadata")
  }

  override def delete_dashboard(session: String, dashboard_id: Int): Unit = {
    logError(s"delete_dashboard: $session, $dashboard_id")
  }

  override def share_dashboard(session: String, dashboard_id: Int, groups: util.List[String], objects: util.List[String], permissions: TDashboardPermissions, grant_role: Boolean): Unit = {
    logError(s"share_dashboard: $session, $dashboard_id, $groups, $objects, $permissions, $grant_role")
  }

  override def unshare_dashboard(session: String, dashboard_id: Int, groups: util.List[String], objects: util.List[String], permissions: TDashboardPermissions): Unit = {
    logError(s"unshare_dashboard: $session, $dashboard_id, $groups, $objects, $permissions")
  }

  override def get_dashboard_grantees(session: String, dashboard_id: Int): util.List[TDashboardGrantees] = {
    logError(s"get_dashboard_grantees: $session, $dashboard_id")
    null
  }

  override def get_link_view(session: String, link: String): TFrontendView = {
    logError(s"get_link_view: $session, $link")
    null
  }

  override def create_link(session: String, view_state: String, view_metadata: String): String = {
    logError(s"create_link: $session, $view_state, $view_metadata")
    null
  }

  override def load_table_binary(session: String, table_name: String, rows: util.List[TRow]): Unit = {
    logError(s"load_table_binary: $session, $table_name, $rows")
  }

  override def load_table_binary_columnar(session: String, table_name: String, cols: util.List[TColumn]): Unit = {
    logError(s"load_table_binary_columnar: $session, $table_name, $cols")
  }

  override def load_table_binary_arrow(session: String, table_name: String, arrow_stream: ByteBuffer): Unit = {
    logError(s"load_table_binary_arrow: $session, $table_name, $arrow_stream")
  }

  override def load_table(session: String, table_name: String, rows: util.List[TStringRow]): Unit = {
    logError(s"load_table: $session, $table_name, $rows")
  }

  override def detect_column_types(session: String, file_name: String, copy_params: TCopyParams): TDetectResult = {
    logError(s"detect_column_types: $session, $file_name, $copy_params")
    null
  }

  override def create_table(session: String, table_name: String, row_desc: util.List[TColumnType], file_type: TFileType, create_params: TCreateParams): Unit = {
    logError(s"create_table: $session, $table_name, $row_desc, $file_type, $create_params")
  }

  override def import_table(session: String, table_name: String, file_name: String, copy_params: TCopyParams): Unit = {
    logError(s"import_table: $session, $table_name, $file_name, $copy_params")
  }

  override def import_geo_table(session: String, table_name: String, file_name: String, copy_params: TCopyParams, row_desc: util.List[TColumnType], create_params: TCreateParams): Unit = {
    logError(s"import_geo_table: $session, $table_name, $file_name, $copy_params, $row_desc, $create_params")
  }

  override def import_table_status(session: String, import_id: String): TImportStatus = {
    logError(s"import_table_status: $session, $import_id")
    null
  }

  override def get_first_geo_file_in_archive(session: String, archive_path: String, copy_params: TCopyParams): String = {
    logError(s"get_first_geo_file_in_archive: $session, $archive_path, $copy_params")
    null
  }

  override def get_all_files_in_archive(session: String, archive_path: String, copy_params: TCopyParams): util.List[String] = {
    logError(s"get_all_files_in_archive: $session, $archive_path, $copy_params")
    null
  }

  override def get_layers_in_geo_file(session: String, file_name: String, copy_params: TCopyParams): util.List[TGeoFileLayerInfo] = {
    logError(s"get_layers_in_geo_file: $session, $file_name, $copy_params")
    null
  }

  override def check_table_consistency(session: String, table_id: Int): TTableMeta = {
    logError(s"check_table_consistency: $session, $table_id")
    null
  }

  override def start_query(session: String, query_ra: String, just_explain: Boolean): TPendingQuery = {
    logError(s"start_query: $session, $query_ra, $just_explain")
    null
  }

  override def execute_first_step(pending_query: TPendingQuery): TStepResult = {
    logError(s"execute_first_step: $pending_query")
    null
  }

  override def broadcast_serialized_rows(serialized_rows: TSerializedRows, row_desc: util.List[TColumnType], query_id: Long): Unit = {
    logError(s"broadcast_serialized_rows: $serialized_rows, $row_desc, $query_id")
  }

  override def start_render_query(session: String, widget_id: Long, node_idx: Short, vega_json: String): TPendingRenderQuery = {
    logError(s"start_render_query: $session, $widget_id, $node_idx, $vega_json")
    null
  }

  override def execute_next_render_step(pending_render: TPendingRenderQuery, merged_data: util.Map[String, util.Map[String, util.Map[String, util.Map[String, util.List[TRenderDatum]]]]]): TRenderStepResult = {
    logError(s"execute_next_render_step: $pending_render, $merged_data")
    null
  }

  override def insert_data(session: String, insert_data: TInsertData): Unit = {
    logError(s"insert_data: $session, $insert_data")
  }

  override def checkpoint(session: String, db_id: Int, table_id: Int): Unit = {
    logError(s"checkpoint: $session, $db_id, $table_id")
  }

  override def get_table_descriptor(session: String, table_name: String): util.Map[String, TColumnType] = {
    logError(s"get_table_descriptor: $session, $table_name")
    null
  }

  override def get_row_descriptor(session: String, table_name: String): util.List[TColumnType] = {
    logError(s"get_row_descriptor: $session, $table_name")
    null
  }

  override def get_roles(session: String): util.List[String] = {
    logError(s"get_roles: $session")
    null
  }

  override def get_db_objects_for_grantee(session: String, roleName: String): util.List[TDBObject] = {
    logError(s"get_db_objects_for_grantee: $session, $roleName")
    null
  }

  override def get_db_object_privs(session: String, objectName: String, `type`: TDBObjectType): util.List[TDBObject] = {
    logError(s"get_db_object_privs: $session, $objectName, ${`type`}")
    null
  }

  override def get_all_roles_for_user(session: String, userName: String): util.List[String] = {
    logError(s"get_all_roles_for_user: $session, $userName")
    null
  }

  override def has_object_privilege(session: String, granteeName: String, objectName: String, objectType: TDBObjectType, permissions: TDBObjectPermissions): Boolean = {
    logError(s"has_object_privilege: $session, $granteeName, $objectName, $objectType, $permissions")
    false
  }

  override def set_license_key(session: String, key: String, nonce: String): TLicenseInfo = {
    logError(s"set_license_key: $session, $key, $nonce")
    null
  }

  override def get_license_claims(session: String, nonce: String): TLicenseInfo = {
    logError(s"get_license_claims: $session, $nonce")
    null
  }

  override def get_device_parameters(): util.Map[String, String] = {
    logError(s"get_device_parameters")
    null
  }

  override def register_runtime_udf(session: String, signatures: String, device_ir_map: util.Map[String, String]): Unit = {
    logError(s"register_runtime_udf: $session, $signatures, $device_ir_map")
  }
}

object ThriftHandler extends Logging {
  private val HOST_NAME: String = Try(InetAddress.getLocalHost.getHostName).getOrElse(null)
}
