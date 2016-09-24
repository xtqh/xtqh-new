package xtqh.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 功能说明：系统XML的解析类<br>
 * 
 * Last Modified 
 */
public class XmlUtils {
	public static Element[] ZERO_LENGTH_ELEMENT = new Element[0];

	public static final DummyErrorHandler DUMMY_ERROR_HANDLER = new DummyErrorHandler();

	public static class DummyErrorHandler implements ErrorHandler {
		public void fatalError(SAXParseException err) throws SAXException {
			throw err;
		}

		public void error(SAXParseException err) throws SAXException {
			throw err;
		}

		public void warning(SAXParseException err) throws SAXException {
			throw err;
		}
	}

	/**
	 * 功能说明：根据文件生成Document对象
	 * 
	 * @return org.w3c.dom.Document
	 * @param f
	 *            java.io.File
	 */
	public static Document getDocument(File f, boolean validate) {
		Document doc = null;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			// Use validating parser.
			docBuilderFactory.setValidating(validate);
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			docBuilder.setErrorHandler(DUMMY_ERROR_HANDLER);
			doc = docBuilder.parse(f);
			// Normalize text representation
			doc.getDocumentElement().normalize();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return doc;
	}

	public static Document getDocument(File f) {
		return getDocument(f, true);
	}

	public static Document getDocument(InputStream is) {
		return getDocument(new InputSource(is));
	}

	public static Document getDocument(Reader r) {
		return getDocument(new InputSource(r));
	}

	public static Document getDocument(String data) {
		return getDocument(new StringReader(data));
	}

	public static Document getDocument(InputSource is) {
		return getDocument(is, false);
	}

	public static Document getDocument(InputSource is, boolean validate) {
		Document doc = null;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			// Use validating parser.
			docBuilderFactory.setValidating(validate);
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			docBuilder.setErrorHandler(DUMMY_ERROR_HANDLER);
			doc = docBuilder.parse(is);
			// Normalize text representation
			doc.getDocumentElement().normalize();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return doc;
	}

	public static OutputStream writeDocument(Document doc, OutputStream os) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return os;
	}

	/**
	 * 得到XML文件中某个Element的第一个子Element。
	 * 
	 * @return org.w3c.dom.Node
	 * @param node
	 *            org.w3c.dom.Node
	 */
	public static Element getNextChildElement(Node node) {
		Node n = node.getFirstChild();
		while (n != null && n.getNodeType() != Node.ELEMENT_NODE) {
			n = n.getNextSibling();
		}
		return (Element) n;
	}

	/**
	 * 得到XML文件中某个Element的下一个兄弟Element。
	 * 
	 * @param node
	 *            org.w3c.dom.Node
	 * @return org.w3c.dom.Element
	 */
	public static Element getNextSiblingElement(Node node) {
		Node n = node.getNextSibling();
		while (n != null && n.getNodeType() != Node.ELEMENT_NODE) {
			n = n.getNextSibling();
		}
		return (Element) n;
	}

	/**
	 * Insert the method's description here.
	 * 
	 * @return java.lang.String
	 * @param element
	 *            org.w3c.dom.Node
	 * @param attributeName
	 *            java.lang.String
	 */
	public static String getNodeAttributeValue(Node element, String attributeName) {
		Node tmpNode = element.getAttributes().getNamedItem(attributeName);
		String tmp = null;
		if (tmpNode != null)
			tmp = tmpNode.getNodeValue();
		return tmp;
	}

	/**
	 * 得到XML文件中某个Node下的第一个Text子节点的内容。
	 * 
	 * @return java.lang.String
	 * @param node
	 *            org.w3c.dom.Node
	 */
	public static String getTextData(Node node) {
		if (!node.hasChildNodes()) {
			return null;
		}
		Node child = node.getFirstChild();
		while (child != null && child.getNodeType() != Node.TEXT_NODE && child.getNodeType() != Node.CDATA_SECTION_NODE) {
			child = child.getNextSibling();
		}
		if (child == null) {
			return null;
		}

		if (child.getNodeType() == Node.TEXT_NODE) {
			return ((Text) child).getData();
		} else {
			return ((CDATASection) child).getData();
		}

	}

	/**
	 * 得到XML文件中某个Node下的所有child节点，限定名字为给定的名字
	 * 
	 * @param node
	 * @param childNodeName
	 * @return
	 */
	public static List childNodeList(Node node, String childNodeName) {
		if (node == null)
			return null;

		List children = new LinkedList();
		Node childNode = node.getFirstChild();
		if (childNode != null) {
			do {
				if (childNode.getNodeType() == Node.ELEMENT_NODE && (childNodeName == null || childNodeName.equals(childNode.getNodeName()))) {
					children.add(childNode);
				}
			} while ((childNode = childNode.getNextSibling()) != null);
		}

		return children;
	}

	/**
	 * 得到XML文件中某个Node下的所有child节点
	 * 
	 * @param node
	 * @return
	 */
	public static List childNodeList(Node node) {
		if (node == null)
			return null;

		List children = new LinkedList();
		Node childNode = node.getFirstChild();
		if (childNode != null) {
			do {
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					children.add(childNode);
				}
			} while ((childNode = childNode.getNextSibling()) != null);
		}

		return children;
	}

	/**
	 * 得到XML文件中某个Node下的指定名称的child节点，限定名字为给定的名字
	 * 
	 * @param node
	 * @param childNodeName
	 * @return
	 */
	public static Element childNodeByTag(Node node, String childNodeName) {
		if (node == null)
			return null;

		Element childElement = null;
		Node childNode = node.getFirstChild();
		if (childNode != null) {
			do {
				if (childNode.getNodeType() == Node.ELEMENT_NODE && (childNodeName == null || childNodeName.equals(childNode.getNodeName()))) {
					return (Element) childNode;
				}
			} while ((childNode = childNode.getNextSibling()) != null);
		}

		return null;
	}

	/**
	 * 遍历DOM，将内容写到字符串中
	 * 
	 * @param doc
	 * @return
	 * @throws IOException
	 */
	public static String getString(Document doc) throws IOException {
		OutputFormat format = new OutputFormat(doc); // Serialize DOM
		format.setIndent(2);
		StringWriter stringOut = new StringWriter();
		XMLSerializer serial = new XMLSerializer(stringOut, format);
		serial.asDOMSerializer(); // As a DOM Serializer

		serial.serialize(doc.getDocumentElement());
		return stringOut.toString();
	}

	/**
	 * 遍历DOM，将内容写到字符串中
	 * 
	 * @param doc
	 * @return
	 * @throws IOException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 */
	public static String getString(Node node) throws IOException, ParserConfigurationException, FactoryConfigurationError {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		doc.importNode(node, true);

		OutputFormat format = new OutputFormat(doc); // Serialize DOM
		format.setIndent(2);
		StringWriter stringOut = new StringWriter();
		XMLSerializer serial = new XMLSerializer(stringOut, format);
		serial.asDOMSerializer(); // As a DOM Serializer

		serial.serialize((Element) node);

		return stringOut.toString();
	}
}
