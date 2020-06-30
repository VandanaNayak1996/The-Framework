package main.java.com.ibm.framework;
import java.io.FileNotFoundException;
import java.util.List;

import main.java.com.ibm.framework.util.Config;

public class EducationHelp {
	
	public static void main(String[] args) {
		 String videoFilePath = Config.getVideoFilePath();
	     String audioDilePath = Config.getAudioFilePath();
	     ExtractAudioContentFromVideo a = new ExtractAudioContentFromVideo();
	     a.extractAudioFromVideo(videoFilePath, audioDilePath);
	     SpeechToTextService b = new SpeechToTextService();
	     String text = "";
	     try {
			text = b.extractTextfromAudio(audioDilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    LinkRecommendation c = new LinkRecommendation();
	    List<String> links = c.extractLinksFromText(text);
	    System.out.println("Relevant recommended links are:" + links);
	    
	}

}
