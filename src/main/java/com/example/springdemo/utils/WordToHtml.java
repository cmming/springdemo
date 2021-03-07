/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.fit.pdfdom.PDFDomTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * 类的描述 .
 * https://blog.csdn.net/tongkp/article/details/102586493
 * https://www.jianshu.com/p/106676169943
 * https://www.cnblogs.com/chengpanpan/p/6750029.html
 * @version 2.0.0 2021-03-04 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@Component
@Slf4j
public class WordToHtml {

    public static InputStream docToHtml(InputStream in) throws Exception {
        HWPFDocument wordDocument = new HWPFDocument(in);
        org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                String type = pictureType.name();
                return "data:image/" + type + ";base64," + new String(Base64.encodeBase64(content));
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        StreamResult streamResult = new StreamResult(outStream);
        serializer.transform(domSource, streamResult);
        outStream.close();
        return new ByteArrayInputStream(outStream.toByteArray());
    }

    public static InputStream docToHtml(Map<String, Object> request, InputStream in) throws Exception {
        String fileName = (String) request.get("name");
        if (StringUtils.isEmpty(fileName)) {
            return in;
        }
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String prevName = fileName.substring(0, fileName.lastIndexOf("."));
        if ("html".equalsIgnoreCase(extensionName)) {
            return in;
        }
        if (extensionName.equalsIgnoreCase("doc")) {
            request.put("name", prevName + ".html");
            return docToHtml(in);
        }
        if (extensionName.equalsIgnoreCase("docx")) {
            request.put("name", prevName + ".html");
            return docxToHtml(in, (String) request.get("filePath"));
        }
        return in;
    }



    public static InputStream docxToHtml(InputStream in, String filePath) {
        XWPFDocument document = null;
        try {
            //1:加载文档到XWP
            document = new XWPFDocument(in);
            List<XWPFPictureData> list = document.getAllPictures();
            //3：转换XWPFDocument to XHTML
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XHTMLConverter.getInstance().convert(document, out, null);
            String s = new String(out.toByteArray());
            s = setImg(s, list);
            return new ByteArrayInputStream(s.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return in;
    }

    public static void pdf2Html(String inputPath, String outputPath){
        PDDocument pdf = null;
        try {
            pdf = PDDocument.load(new File(inputPath));
            Writer output = null;
            output = new PrintWriter(outputPath, "utf-8");
            new PDFDomTree().writeText(pdf, output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static String setImg(String html, List<XWPFPictureData> list) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("img");
        if (elements != null && elements.size() > 0 && list != null) {
            for (Element element : elements) {
                String src = element.attr("src");
                for (XWPFPictureData data : list) {
                    if (src.contains(data.getFileName())) {
                        String type = src.substring(src.lastIndexOf(".") + 1);
                        String base64 = "data:image/" + type + ";base64," + new String(Base64.encodeBase64(data.getData()));
                        element.attr("src", base64);
                        break;
                    }
                }
            }
        }
        return doc.toString();
    }


    public static void inputStreamToFile(InputStream inputStream, String newPath) {
        OutputStream outputStream = null;
        try {
            File file = new File(newPath);
            outputStream = new FileOutputStream(file);

            int bytesWritten = 0;
            int byteCount = 0;

            byte[] bytes = new byte[1024];

            while ((byteCount = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, bytesWritten, byteCount);
            }
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
