package data_driven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

    public String readPropertiesFile(String variable) throws IOException
    {
        FileInputStream fileObj = new FileInputStream("src/main/resources/values.properties");

        Properties proObj = new Properties();
        proObj.load(fileObj);

        String choiceValue = (String)proObj.getProperty(variable);
        System.out.println(choiceValue);
        return choiceValue;
    }
}
