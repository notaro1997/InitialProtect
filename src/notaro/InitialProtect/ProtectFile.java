package notaro.InitialProtect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProtectFile {

	public static InitialProtect plugin;
	public ProtectFile(InitialProtect instance){
		plugin = instance;
	}

	public File ProtectFile;
	public ArrayList<String> SafePlayers;

	public ProtectFile(File file){
		this.ProtectFile = file;
		this.SafePlayers = new ArrayList<String>();

		if(!this.ProtectFile.exists()){
			try{
				this.ProtectFile.createNewFile();
			}catch(IOException IOError){
				IOError.printStackTrace();		
			}
		}
	}
	public void loadData(){

		try {
			DataInputStream input = new DataInputStream(new FileInputStream(this.ProtectFile));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while ((line = reader.readLine()) != null){
				if(!this.contains(line)){
					this.SafePlayers.add(line);
				}
			}
			reader.close();
			input.close();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public void saveData(){
		try {
			FileWriter stream = new FileWriter(this.ProtectFile);
			BufferedWriter output = new BufferedWriter(stream);

			for (String value : this.SafePlayers){	
				output.write(value);
				output.newLine();
			}	
			output.close();
			stream.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	public boolean contains(String value){
		return this.SafePlayers.contains(value);
	}

	public void add(String value){
		if (!this.contains(value)){
			this.SafePlayers.add(value);
		}
	}

	public void remove(String value){
		this.SafePlayers.remove(value);
	}

	public ArrayList<String> getValues(){
		return this.SafePlayers;
	}
}
