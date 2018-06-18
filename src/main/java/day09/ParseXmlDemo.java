package day09;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ʹ��DOM4J����XML�ĵ�
 * @author NiCo
 *
 */
public class ParseXmlDemo {
	public static void main(String[] args) {
		/*
		 * ����XML�ĵ��������̣�
		 * 1������SAXreader
		 * 2��ʹ��SAXReader��ȡXML�ĵ�������
		 * 	   һ��Document����.
		 * 	 ��һ��Ҳ��DOM������ʱ����Դ�ĵط�
		 * 3: ͨ��Document�����ȡ��Ԫ��
		 * 4��ͨ����Ԫ�ذ���XML�ĵ��ṹ�𼶻�ȡ
		 * 		��Ԫ�أ��Դﵽ����XML�ĵ����ݵ�Ŀ��
		 */
		/*
		 * ��emplist.xml�ĵ��е�ÿ��Ա����Ϣ
		 * ������Ϊһ��empʵ���������뵽һ��������
		 */
		try {
			//1
			SAXReader reader = new SAXReader();
			
			//2
			Document doc=reader.read(new File("emplist.xml"));
			
			/*
			 * 3
			 * Element��ÿһ��ʵ�����ڱ�ʾһ��
			 * XML�ĵ���һ��Ԫ�أ�һ�Ա�ǩ��
			 * 
			 * Element�ṩ�����ڻ�ȡ���ʾ��Ԫ��
			 * �����Ϣ�ķ���
			 * 
			 * String getName()
			 * ��ȡ��ǰԪ�ص����֣���ǩ����
			 * 
			 * Element element(String name)
			 * ��ȡ��ǰԪ����ָ�����ֵ���Ԫ��
			 * 
			 * List elements()
			 * ��ȡ��ǰԪ����������Ԫ��
			 * 
			 * List elements(String name)
			 * ��ȡ��ǰԪ��������ͬ����Ԫ��
			 * 
			 * String getText()
			 * ��ȡ��ǰԪ���м���ı�
			 * 
			 * String elementText(String name)
			 * ��ȡ��ǰ��ǩ��ָ�����ֵ��ӱ�ǩ�м���ı�
			 * 
			 * Attribute attribute(String name)
			 * ��ȡ��ǰ��ǩ��ָ�����ֵ�����
			 */
			Element root=doc.getRootElement();
			//��������XML�ĵ��н�������������Ա����Ϣ
			List<Emp> empList=new ArrayList<Emp>();
			
			/*
			 * ��ȡ<list>��ǩ�е������ӱ�ǩ<emp>,����
			 * ��Щ<emp>��ǩ�е�Ա����Ϣ������empʵ��
			 * ��ʾ������empList����
			 */
			List<Element> list=root.elements();
			for(Element empEle:list) {
				//��ȡԱ������
				Element nameEle=empEle.element("name");
				String name=nameEle.getTextTrim();
				//System.out.println(name);
				
				//��ȡ����
				int age=Integer.parseInt(empEle.elementText("age"));
				//System.out.println(age);
				
				//��ȡ�Ա�
				String gender=empEle.elementText("gender");
				//System.out.println(gender);
				
				//��ȡ����
				int salary=Integer.parseInt(empEle.elementText("salary"));
				//System.out.println(salary);
				
				/*
				 * ��ȡid����
				 * Attribute��ÿһ��ʵ�����ڱ�ʾһ��Ԫ��
				 * �е�һ������
				 * ���÷�����
				 * String getName()
				 * ��ȡ���Ե�����
				 * 
				 * String getValue()
				 * ��ȡ���Ե�ֵ
				 * 
				 */
				Attribute attr=empEle.attribute("id");
				int id=Integer.parseInt(attr.getValue());
				//System.out.println(id);
				
				Emp emp=new Emp(id,name,age,gender,salary);
				empList.add(emp);
			}
			System.out.println("�������");
			for(Emp emp:empList) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
