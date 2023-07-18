package demo;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Hello {

    public static void main(String[] args) throws IOException {
        User user1 = new User();
        user1.setName("Freewind1");
        user1.setFavoriteNumber(99);
        user1.setFavoriteColor("black");

        User user2 = User.newBuilder()
                .setName("Freewind2")
                .setFavoriteColor("white")
                .build();

        File dataFile = new File("users.avro");
        System.out.println("Write to file: " + dataFile);

        try (DataFileWriter<User> dataFileWriter = new DataFileWriter<>(new SpecificDatumWriter<>(User.class))) {
            dataFileWriter.create(user1.getSchema(), dataFile);
            // NOTE should append user1 too
            dataFileWriter.append(user1);
            dataFileWriter.append(user2);
        }

        System.out.println("Read from file: " + dataFile);
        try (DataFileReader<User> dataFileReader = new DataFileReader<>(dataFile, new SpecificDatumReader<>(User.class))) {
            User user = null;
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves us from
                // allocating and garbage collecting many objects for files with
                // many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        }
    }

}
