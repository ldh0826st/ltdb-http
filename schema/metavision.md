# Project

```
curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "drop table projects"
 
curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "CREATE table projects(create_date String, data_type long, description string, file_count long, project_id long, project_type long, source string, thumbnail_empty string, thumbnail string, update_date string, user_id long, version string, title string, tags string) USING r2 OPTIONS (table '101', host 'bpp-tview-aiops-search01', port '18100', partitions 'source user_id', mode 'nvkvs', rowstore 'false', at_least_one_partition_enabled 'no')"
```

# Dataset

```
curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "drop table datasets"

curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "CREATE table datasets (create_date string, dataset_id long, description string, project_id long, thumbnail string, title string, update_date string, version string, tags string ) USING r2 OPTIONS (table '201', host 'bpp-tview-aiops-search01', port '18200', partitions 'project_id title', mode 'nvkvs', rowstore 'false', at_least_one_partition_enabled 'no')"
```

# File

```
curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "drop table files"

curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "CREATE table files (action string, bit_rate double, codec string, color_type string, create_date string, dataset_id long, description string, duration double, file_extension string, file_id long, file_name string, file_path string, file_size long, frame_height long, frame_rate double, frame_width long, hash string, is_ir boolean , place_class string, project_id long, thumbnail string , thumbnail_path string, update_date string, tags string ) USING r2 OPTIONS (table '301', host 'bpp-tview-aiops-search01', port '18300', partitions 'project_id dataset_id file_name', mode 'nvkvs', rowstore 'false', at_least_one_partition_enabled 'no')"
```


# Instance

```
curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "drop table instances"

curl --location --request POST http://BPP-TVIEW-AIOPS-SEARCH01:4762/query --header "Content-Type: text/plain" --data "CREATE table instances (instance_id long, project_id long, dataset_id long, file_id long, cropped_image string, track_id long, binding_id long, class_name string, region_type string, region string, create_date string, update_date string, feature_value array<float>, feature_value_3d string, model_id long, extracting_method string, gender string, age string, expression string, action string, posture string, color string, cropped_image_path string, file_id_key long, model_id_is_not_null boolean, tags string) USING r2 OPTIONS (table '401', host 'bpp-tview-aiops-search01', port '18400', partitions 'project_id dataset_id file_id_key model_id_is_not_null', mode 'nvkvs', rowstore 'false', at_least_one_partition_enabled 'no')"
```
