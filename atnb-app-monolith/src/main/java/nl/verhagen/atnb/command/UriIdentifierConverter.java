package nl.verhagen.atnb.command;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UriIdentifierConverter implements IdentifierConverter<URI> {
	private Logger logger = LoggerFactory.getLogger(UriIdentifierConverter.class);
	private final URI baseUri;
	private final List<String> prefixes;


	public UriIdentifierConverter(URI baseUri, List<String> prefixes) {
		this.baseUri = baseUri;
		this.prefixes = prefixes;
	}


	@Override
	public boolean isIdentification(URI uri) {
		if (! uri.toString().startsWith(baseUri.toString())) {
			logger.info("Argument 'uri' with value '" + uri
					+ "' doesn't start with the configured base uri '"
					+ baseUri + "' as expected.");
			return false;
		}
		return true;
	}

	@Override
	public String[] extractIdentification(URI uri) {
		if (! isIdentification(uri)) {
			throw new IllegalArgumentException("Argument 'uri' with value '" + uri 
					+ "' doesn't start with the configured base uri '"
					+ baseUri + "' as expected.");
		}
		return new String[] {
				prefixes.get(0)
				, extractOrganisationIdentification(baseUri)
				, prefixes.get(1)
				, uri.toString().substring(baseUri.toString().length())
				};
	}


	private String extractOrganisationIdentification(URI uri) {
		// Remove '.com' , '.org' or '.de'
		String orgId = uri.getHost().substring(0, uri.getHost().lastIndexOf("."));
		// Remove everything before the last dot
		// - 'www.manning' -> 'manning'
		// - 'some.other.pragprog' -> 'pragprog'
		if (orgId.lastIndexOf(".") > -1) {
			orgId = orgId.substring(orgId.lastIndexOf(".") +1);
		}
		return orgId;
	}

}
