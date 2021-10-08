XXE test for Castor library
===========================

This directory contains 3 Maven modules with tests for XXE
with the Castor XML unmarshaller.

Adapted from: https://blog.gdssecurity.com/labs/2014/6/13/castor-library-xml-external-entity-xxe-vulnerability.html

To build a test just do ``mvn clean package``.
The test tries to use XXE entity expansion to read ```src/main/resources/secret.txt```
which contains ``super secret secret``.

Castor version 1.2 vulnerable
-----------------------------
Output from ``` java -jar target/xxe-castor.12-xxe-1.0-SNAPSHOT-jar-with-dependencies.jar```

```
ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
Oct 08, 2021 10:07:36 AM com.veracode.xxe.castor.Main main
INFO: Person name: super secret secret
```

Castor version 1.2 not vulnerable with castor.properties
--------------------------------------------------------
Here a ``castor.properties`` is used to disable entity loading.

Output from ``` java -jar target/xxe-castor.12-no-xxe-1.0-SNAPSHOT-jar-with-dependencies.jar```

```
ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
Failed to unmarshal the xml
org.exolab.castor.xml.MarshalException: DOCTYPE is disallowed when the feature "http://apache.org/xml/features/disallow-doctype-decl" set to true.{File: [not available]; line: 2; column: 10}
        at org.exolab.castor.xml.Unmarshaller.convertSAXExceptionToMarshalException(Unmarshaller.java:761)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:727)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:616)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:807)
        at com.veracode.xxe.castor.Main.main(Main.java:21)
Caused by: org.xml.sax.SAXParseException; lineNumber: 2; columnNumber: 10; DOCTYPE is disallowed when the feature "http://apache.org/xml/features/disallow-doctype-decl" set to true.
        at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:204)
        at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:178)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:400)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLScanner.reportFatalError(XMLScanner.java:1471)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl$PrologDriver.next(XMLDocumentScannerImpl.java:898)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:605)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:534)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:888)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:824)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1216)
        at java.xml/com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:635)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:715)
        ... 3 more
Caused by: org.xml.sax.SAXParseException; lineNumber: 2; columnNumber: 10; DOCTYPE is disallowed when the feature "http://apache.org/xml/features/disallow-doctype-decl" set to true.
        at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:204)
        at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:178)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:400)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLScanner.reportFatalError(XMLScanner.java:1471)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl$PrologDriver.next(XMLDocumentScannerImpl.java:898)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:605)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:534)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:888)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:824)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1216)
        at java.xml/com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:635)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:715)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:616)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:807)
        at com.veracode.xxe.castor.Main.main(Main.java:21)
Caused by: org.xml.sax.SAXParseException; lineNumber: 2; columnNumber: 10; DOCTYPE is disallowed when the feature "http://apache.org/xml/features/disallow-doctype-decl" set to true.
        at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:204)
        at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:178)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:400)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLScanner.reportFatalError(XMLScanner.java:1471)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl$PrologDriver.next(XMLDocumentScannerImpl.java:898)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:605)
        at java.xml/com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:534)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:888)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:824)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
        at java.xml/com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1216)
        at java.xml/com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:635)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:715)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:616)
        at org.exolab.castor.xml.Unmarshaller.unmarshal(Unmarshaller.java:807)
        at com.veracode.xxe.castor.Main.main(Main.java:21)
```

Castor version 1.3.3 not vulnerable
-----------------------------------
Version 1.3.3 fixes the vulnerability with a new artifact ( https://mvnrepository.com/artifact/org.codehaus.castor/castor-xml ) and secure defaults.

Output from ``` java -jar target/xxe-castor.13-no-xxe-1.0-SNAPSHOT-jar-with-dependencies.jar```

```
ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
Oct 08, 2021 10:12:44 AM com.veracode.xxe.castor.Main main
INFO: Person name: 
```
