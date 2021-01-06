package componentTests.networkHTTPTests;

import android.util.JsonWriter;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MemoryImpl implements Memory{
    private final File file;

    public MemoryImpl(String fileName) throws FileNotFoundException{
        this.file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(this.file);
    }


    @Override
    public void save(String einString) throws IOException {
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(this.file);
        }catch (FileNotFoundException e){
        //nothing to do
        }

        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF(einString);

        fos.close();

    }

    @Override
    public String restore() throws IOException {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(this.file);
        }catch (FileNotFoundException e){
            // nothing to do
        }

        DataInputStream dis = new DataInputStream(fis);

        String gelesenerString = dis.readUTF();

        fis.close();

        return gelesenerString;
    }







}
