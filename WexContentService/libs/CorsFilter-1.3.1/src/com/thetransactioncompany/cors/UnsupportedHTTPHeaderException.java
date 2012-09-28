package com.thetransactioncompany.cors;


/**
 * Unsupported HTTP header exception. Thrown to indicate that a custom HTTP
 * request header is not supported by the CORS policy.
 *
 * @author <a href="http://dzhuvinov.com">Vladimir Dzhuvinov</a>
 * @version 1.3.1 (2010-09-27)
 */
public class UnsupportedHTTPHeaderException extends CORSException {

	
	/**
	 * The HTTP header.
	 */
	private HeaderFieldName header = null;
	
	
	/**
	 * Creates a new unsupported HTTP header exception with the specified 
	 * message.
	 *
	 * @param message The message.
	 */
	public UnsupportedHTTPHeaderException(final String message) {
	
		this(message, null);
	}
	
	
	/**
	 * Creates a new unsupported HTTP header exception with the specified 
	 * message and request header.
	 *
	 * @param message       The message.
	 * @param requestHeader The request HTTP header name, {@code null} if 
	 *                      unknown.
	 */
	public UnsupportedHTTPHeaderException(final String message, final HeaderFieldName requestHeader) {
	
		super(message);
		
		header = requestHeader;
	}
	
	
	/**
	 * Gets the request header name.
	 *
	 * @return The request header name, {@code null} if unknown or not set.
	 */
	public HeaderFieldName getRequestHeader() {
	
		return header;
	}
}
