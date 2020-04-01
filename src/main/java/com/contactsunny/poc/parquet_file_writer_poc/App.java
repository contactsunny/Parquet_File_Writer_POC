package com.contactsunny.poc.parquet_file_writer_poc;

import com.contactsunny.poc.parquet_file_writer_poc.parquet.CsvParquetWriter;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Value("${schema.filePath}")
    private String schemaFilePath;

    @Value("${output.filePath}")
    private String outputPath;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<List<String>> columns = getDataForFile();
        MessageType schema = getSchemaForParquetFile();
        CsvParquetWriter writer = getParquetWriter(schema);

        for (List<String> column : columns) {
            logger.info("Writing line: " + column.toArray());
            writer.write(column);
        }
        logger.info("Finished writing Parquet file.");

        writer.close();
    }

    private CsvParquetWriter getParquetWriter(MessageType schema) throws IOException {
        String outputFilePath = outputPath + "/" + System.currentTimeMillis() + ".parquet";
        File outputParquetFile = new File(outputFilePath);
        Path path = new Path(outputParquetFile.toURI().toString());
        return new CsvParquetWriter(
                path, schema, false, CompressionCodecName.SNAPPY
        );
    }

    private MessageType getSchemaForParquetFile() throws IOException {
        File resource = new File(schemaFilePath);
        String rawSchema = new String(Files.readAllBytes(resource.toPath()));
        return MessageTypeParser.parseMessageType(rawSchema);
    }

    private List<List<String>> getDataForFile() {
        List<List<String>> data = new ArrayList<>();

        List<String> parquetFileItem1 = new ArrayList<>();
        parquetFileItem1.add("1");
        parquetFileItem1.add("Name1");
        parquetFileItem1.add("true");

        List<String> parquetFileItem2 = new ArrayList<>();
        parquetFileItem2.add("2");
        parquetFileItem2.add("Name2");
        parquetFileItem2.add("false");

        data.add(parquetFileItem1);
        data.add(parquetFileItem2);

        return data;
    }
}
