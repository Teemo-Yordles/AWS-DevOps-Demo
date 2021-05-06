package com.unisys.aos.view.messageprocessor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a static utility class to provide methods to peek
 * XML tags.
 * This class thread-safe.
 * 
 * @author Liu Jie
 * @since 2020-09-07
 *
 */
@Slf4j
public class MessagePeeker {
	// regex pattern that matches contents of <type></type> tags
	// note: nested tags not supported by this regex, xml should be valid and well formed
	private static final Pattern typeTag = 
	    Pattern.compile("<\\s*type\\s*>((?:(?!<\\s*/\\s*type).)+)<\\s*/\\s*type\\s*>",
	            Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
	
	// regex pattern that matches contents of <styp></styp> tags
	// note: nested tags not supported by this regex, xml should be valid and well formed
	private static final Pattern subTypeTag = 
	    Pattern.compile("<\\s*styp\\s*>((?:(?!<\\s*/\\s*styp).)+)<\\s*/\\s*styp\\s*>",
	            Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
	
	// regex pattern that matches contents of <seqn></seqn> tags
	// note: nested tags not supported by this regex, xml should be valid and well formed
	private static final Pattern seqnTag = 
	    Pattern.compile("<\\s*seqn\\s*>((?:(?!<\\s*/\\s*seqn).)+)<\\s*/\\s*seqn\\s*>",
	            Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
	
	// regex pattern that matches contents of <styp></styp> tags
	// note: nested tags not supported by this regex, xml should be valid and well formed
	private static final Pattern resTypeTag = 
	    Pattern.compile("<\\s*styp\\s*>((?:(?!<\\s*/\\s*rtyp).)+)<\\s*/\\s*rtyp\\s*>",
	            Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
       
	/**
	 * Return the contents of the TYPE tag in the XML.
	 * @param xmlStr - XML message to be processed
	 * @return null if not found
	 */
	public static String getType(String xmlStr) {
		return peekTag(typeTag, xmlStr);
	}
	
	/**
	 * Return the contents of the STYP tag in the XML.
	 * @param xmlStr - XML message to be processed
	 * @return null if not found
	 */
	public static String getSubType(String xmlStr) {
		return peekTag(subTypeTag, xmlStr);
	}
	
	/**
	 * Return the contents of the SEQN tag in the XML.
	 * @param xmlStr - XML message to be processed
	 * @return null if not found
	 */
	public static String getSeqn(String xmlStr) {
		return peekTag(seqnTag, xmlStr);
	}
	
	/**
	 * Return the contents of the RTYP tag in the XML.
	 * @param xmlStr - XML message to be processed
	 * @return null if not found
	 */
	public static String getRespType(String xmlStr) {
		return peekTag(resTypeTag, xmlStr);
	}
	
	private static String peekTag(Pattern pattern, String xmlStr) {
		String value = null;
		try {
			Matcher matcher = pattern.matcher(xmlStr);
			if (matcher.find()) {
				value = matcher.group(1);
			}
		}
		catch (Exception e) {
			log.error("Unexpected error in obtaining tag", e);
			// should not occur - invoker to handle error
		}
		if(value!=null){
			return value.trim();
		}else{
			return value;
		}
	}
}
