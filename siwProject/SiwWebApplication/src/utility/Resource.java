package utility;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import servlet.ServletUpload;

public class Resource {
	
	
	public List<String> getImageInsertion(int id_item) {
		List<String>images=new LinkedList<String>();
	      File folder = new File(ServletUpload.uploadPathFolder+"insertion_"+id_item+"/");
	      File[] listOfFiles = folder.listFiles();
	      if(listOfFiles!=null)
	          for (int i = 0; i < listOfFiles.length; i++) {
	            if (listOfFiles[i].isFile()) {
	              
//	              System.out.println("File " + listOfFiles[i].getName());
	              images.add(listOfFiles[i].getName());
	            } else if (listOfFiles[i].isDirectory()) {
//	              System.out.println("Directory " + listOfFiles[i].getName());
	            }
	          }
		return images;
	}

}
