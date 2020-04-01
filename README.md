# Parquet File Writer POC
This is a simple Java POC to create Parquet files This is a Spring Boot project.

## How to Build
Once you clone the repository, ```cd``` into the project root directory and run the following command to build the project:

```shell script
mvn clean install
``` 

## How to Run
Once you build the project, the build command will generate a ```.jar``` file in the ```target/``` directory. 
The name of the ```.jar``` file will be ```parquet_file_writer_poc-1.0-SNAPSHOT.jar```. 
You can run this project by executing the following command from the project root directory:

```shell script
java -jar target/parquet_file_writer_poc-1.0-SNAPSHOT.jar
```

## Output Files
The output files will be generated in the ```resources/output``` directory. You can check this directory after 
running the project to check your output files.