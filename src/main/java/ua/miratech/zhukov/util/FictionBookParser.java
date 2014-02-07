package ua.miratech.zhukov.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FictionBookParser {

	private String xmlValue;
	private XPath xpath;

	public FictionBookParser(byte[] bytes) {
		try {
			xmlValue = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XPathFactory xpathFactory = XPathFactory.newInstance();
		xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(new XFDLNamespaceContext());
	}

	public String getTitle() {
		InputSource source = new InputSource(new StringReader(xmlValue));
		String title = null;
		try {
			title = xpath.evaluate("/df:FictionBook/df:description/df:title-info/df:book-title", source);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return title;
	}

	public String getAuthor() {
		InputSource source1 = new InputSource(new StringReader(xmlValue));
		InputSource source2 = new InputSource(new StringReader(xmlValue));
		String firstName = null;
		String lastName = null;
		try {
			firstName = xpath.evaluate("/df:FictionBook/df:description/df:title-info/df:author/df:first-name", source1);
			lastName = xpath.evaluate("/df:FictionBook/df:description/df:title-info/df:author/df:last-name", source2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstName + " " + lastName;
	}

	public String getLanguage() {
		InputSource source = new InputSource(new StringReader(xmlValue));
		String lang = null;
		try {
			lang = xpath.evaluate("/df:FictionBook/df:description/df:title-info/df:lang", source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lang;
	}

	public String getISBN() {
		InputSource source = new InputSource(new StringReader(xmlValue));
		String lang = null;
		try {
			lang = xpath.evaluate("/df:FictionBook/df:description/df:publish-info/df:isbn", source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lang;
	}

	public String getAnnotation() {
		InputSource source = new InputSource(new StringReader(xmlValue));
		String annotation = null;
		try {
			annotation = xpath.evaluate("/df:FictionBook/df:description/df:title-info/df:annotation", source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return annotation;
	}

	public List<String> getGenres() {
		List<String> genres = new ArrayList<>();
		InputSource source = new InputSource(new StringReader(xmlValue));
		try {
			NodeList nl = (NodeList) xpath.evaluate("/df:FictionBook/df:description/df:title-info/df:genre",
					source, XPathConstants.NODESET);
			for (int i = 0; i < nl.getLength(); i++) {
				genres.add(nl.item(i).getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genres;
	}

	public NodeList getContent() {
		InputSource source = new InputSource(new StringReader(xmlValue));
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);
			return document.getElementsByTagName("section");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private class XFDLNamespaceContext implements NamespaceContext {

		@Override
		public String getNamespaceURI(String prefix) {
			switch (prefix) {
				case "df": return "http://www.gribuser.ru/xml/fictionbook/2.0";
			}
			return null;
		}

		@Override
		public String getPrefix(String namespaceURI) {
			return null;
		}

		@Override
		public Iterator getPrefixes(String namespaceURI) {
			return null;
		}

	}

}
