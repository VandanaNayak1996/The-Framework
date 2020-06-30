import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechToTextService {

	public SpeechToTextService() {
	}

	public SpeechRecognitionResults speechToText(String filePath, String apiToken, String serviceUrl) {
		Authenticator authenticator = new IamAuthenticator(apiToken);
		SpeechToText service = new SpeechToText(authenticator);
		// service.se
		service.setServiceUrl(serviceUrl);
		File audio = new File(filePath);
		RecognizeOptions options = null;
		try {
			options = new RecognizeOptions.Builder()
					.audio(audio)
					.contentType(HttpMediaType.AUDIO_MP3).interimResults(false)
					.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SpeechRecognitionResults transcript = service.recognize(options).execute().getResult();
		return transcript;
	}
	public String extractTextfromAudio(String file) throws FileNotFoundException { 
		System.out.println("Extracting text from the Audio file");
		SpeechToTextService speechToTextService = new SpeechToTextService();
		SpeechRecognitionResults transcript = speechToTextService.speechToText(
				file,Config.getApiKeyForSpeechToText(),Config.getSpeechToTextServiceUrl());
		String finalText = "";
		List<SpeechRecognitionResult> results = transcript.getResults();
		for(SpeechRecognitionResult result : results) {
			List<SpeechRecognitionAlternative> alternatives = result.getAlternatives();
			for(SpeechRecognitionAlternative alternative : alternatives) { finalText +=
					alternative.getTranscript(); } }
		return finalText;
	}
}
