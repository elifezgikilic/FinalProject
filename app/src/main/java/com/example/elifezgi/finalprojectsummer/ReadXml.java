package com.example.elifezgi.finalprojectsummer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by ElifEzgi on 4.7.2017.
 */

    public class ReadXml extends Activity{
        TextView tv1, tv ;
        Button btnRead;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_tab);
            tv1=(TextView)findViewById(R.id.textView1);
            tv=(TextView)findViewById(R.id.textView);
            btnRead=(Button)findViewById(R.id.btnRead);
            btnRead.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    try {
                        InputStream is = getAssets().open("file.xml");

                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(is);

                        Element element=doc.getDocumentElement();
                        element.normalize();

                        NodeList nList = doc.getElementsByTagName("student");

                        for (int i=0; i<nList.getLength(); i++) {

                            Node node = nList.item(i);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element2 = (Element) node;
                                tv1.setText(tv1.getText()+"\nName : " + getValue("name", element2)+"\n");
                                tv1.setText(tv1.getText()+"Surname : " + getValue("surname", element2)+"\n");
                                tv1.setText(tv1.getText()+ "Student Id : " + getValue("studentId", element2)+"\n");
                                tv1.setText(tv1.getText()+"-----------------------------------");
                            }
                        }

                    } catch (Exception e) {e.printStackTrace();}

                }

            });}


        private static String getValue(String tag, Element element) {
            NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        }

    }


