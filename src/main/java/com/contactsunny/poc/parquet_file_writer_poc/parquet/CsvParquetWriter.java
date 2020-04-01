package com.contactsunny.poc.parquet_file_writer_poc.parquet;

import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;

import java.io.IOException;
import java.util.List;

public class CsvParquetWriter extends ParquetWriter<List<String>> {

    public CsvParquetWriter(
            Path file,
            MessageType schema,
            boolean enableDictionary,
            CompressionCodecName codecName
    ) throws IOException {

        super(file, new CsvWriteSupport(schema), codecName, DEFAULT_BLOCK_SIZE, DEFAULT_PAGE_SIZE, enableDictionary, false);
    }
}
