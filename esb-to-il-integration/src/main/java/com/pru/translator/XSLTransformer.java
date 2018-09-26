package com.pru.translator;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSLTransformer {
	private final static Logger logger = LoggerFactory.getLogger(XSLTransformer.class);

	public String transform(String xslFileName, String source) {
		String result = null;
		try (StringReader reader = new StringReader(source); StringWriter writer = new StringWriter()) {

			StreamSource xslCode = new StreamSource(
					XSLTransformer.class.getClassLoader().getResourceAsStream(xslFileName));
			StreamSource input = new StreamSource(reader);
			StreamResult output = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(xslCode);
			t.transform(input, output);
			result = writer.toString();
		} catch (TransformerException | IOException e) {
			logger.error("Error while loading  xslFile: {}", e);
		}
		return result;
	}

}
