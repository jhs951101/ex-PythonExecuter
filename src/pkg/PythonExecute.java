package pkg;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PythonExecute {
	
	public String executePython(String folder, String filename, String[] arguments) {
		String result = "Error: Python Error";
		ByteArrayOutputStream outputStream = null;
		
		try {
            String order = "python";
            String fileroot = folder + "/" + filename;
            
			CommandLine commandLine = CommandLine.parse(order);
			commandLine.addArgument(fileroot);
			
			for(int i=0; i<arguments.length; i++)
				commandLine.addArgument(arguments[i]);

	        outputStream = new ByteArrayOutputStream();
	        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
	        DefaultExecutor executor = new DefaultExecutor();
	        executor.setStreamHandler(pumpStreamHandler);
	        
	        int success = executor.execute(commandLine);
	        
	        if(success == 0) {
	        	result = outputStream.toString();
	        	System.out.println("output: " + result);
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		result = outputStream.toString();
    	System.out.println("output: " + result);
		
		return result;
    }

    public static void main(String[] args)  {
    	PythonExecute t = new PythonExecute();
    	String[] arguments = new String[1];
    	arguments[0] = "C:/Projects/JAVA Projects/TestPythonExecute/Learning_Data_Face.xml";
    	t.executePython("C:/Projects/JAVA Projects/TestPythonExecute", "FaceRecignition.py", arguments);
    }
}