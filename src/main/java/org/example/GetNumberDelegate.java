package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetNumberDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("number", getNumber());
    }

    public static Integer getNumber(){
        try{
            URL url = new URL("http://www.randomnumberapi.com/api/v1.0/random");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String value = bufferedReader.readLine();
            bufferedReader.close();
            return Integer.valueOf(value.replace("[","").replace("]",""));
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
