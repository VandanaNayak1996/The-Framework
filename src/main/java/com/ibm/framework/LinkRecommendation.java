package main.java.com.ibm.framework;
import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsOptions;

import main.java.com.ibm.framework.util.Config;

public class LinkRecommendation {
	public List<String> extractLinksFromText(String text) {
	System.out.println("Extracting relevant links and concepts");
	Authenticator authenticator = new IamAuthenticator(Config.getApiKeyForNLU());
	NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2020-06-11", authenticator);

	EntitiesOptions entities = new EntitiesOptions.Builder()
	  .sentiment(true)
	  .limit(1L)
	  .build();
	KeywordsOptions keywords = new KeywordsOptions.Builder()
			.build();
			
	
	ConceptsOptions concepts = new ConceptsOptions.Builder()
			.build();
	Features features = new Features.Builder()
			.keywords(keywords)
			.concepts(concepts)
	  .entities(entities)
	  .build();
	AnalyzeOptions parameters = new AnalyzeOptions.Builder()
			.text(text)
	  .features(features)
	  .build();
	service.setServiceUrl(Config.getNluServiceUrl());
	AnalysisResults results = service.analyze(parameters).execute().getResult();
	List<ConceptsResult> result = results.getConcepts();
	List<String> links = new ArrayList<String>();
	for(int i =0; i< result.size(); i++) {
		ConceptsResult obj = result.get(i);
		if(obj.getRelevance() > 0.7)
			links.add(obj.getDbpediaResource());
	}
	return links;
	}
}
