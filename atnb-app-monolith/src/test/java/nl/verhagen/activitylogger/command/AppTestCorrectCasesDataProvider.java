package nl.verhagen.activitylogger.command;

public class AppTestCorrectCasesDataProvider {

	/**
	 * Each test case requires these arguments:
	 * <code>
	 * - arguments - the arguments passed in to the main(String[] args) method
	 * - expIdentifier - expected identifier
	 * - expCommand - expected command name
	 * - expRemainer - expected remainer from the argument, after the identifier and command have been extracted from it
	 * </code>
	 */
	public static Object[] provideCorrectCases() {
		return new Object[] {
				new Object[] {
						"article.com-dzone.articles.api-and-api-management-overview"
						, "article.com-dzone.articles.api-and-api-management-overview"
						, "start"
						, null
				}
				, new Object[] {
						"article https://dzone.com/articles/api-and-api-management-overview[An Overview of APIs and API Management] tags:[api,open-api,apigee]"
						, "article"
						, "start"
						, "https://dzone.com/articles/api-and-api-management-overview[An Overview of APIs and API Management] tags:[api,open-api,apigee]"
				}
				, new Object[] {
						"location"
						, "location"
						, "start"
						, null
				}
				, new Object[] {
						"article jump"
						, "article"
						, "start"
						, "jump"
				}
				/*
				, new Object[] {
						""
						, ""
						, ""
						, ""
				}
				, new Object[] {
						""
						, ""
						, ""
						, ""
				}
//*/
		};
	}
}
