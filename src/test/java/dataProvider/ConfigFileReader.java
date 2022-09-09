package dataProvider;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

    public class ConfigFileReader {

        private Properties properties;
        private final String propertyFilePath= "configs//Configuration.properties";

        public ConfigFileReader(){
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(propertyFilePath));
                properties = new Properties();
                try {
                    properties.load(reader);

                    System.setProperty("movieName",properties.getProperty("movieName"));
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
            }
        }
        public String getmoviename() {
                String movieName = properties.getProperty("movieName");
                if (movieName != null)
                    return movieName;
                else
                    throw new RuntimeException("moviename not specified in the Configuration.properties file.");
            }
        }


