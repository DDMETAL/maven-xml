package day09;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * ʹ��DOMд��XML�ĵ�
 * 
 * @author NiCo
 *
 */
public class WriteXMLDemo {
	public static void main(String[] args) {
		/*
		 * д��XML�ĵ��Ĵ��²���
		 * 1.����һ��Document���󣬱�ʾһ���հ��ĵ�
		 * 2.��Document��������Ӹ�Ԫ��
		 * 3.���Ԫ�����������Ԫ�أ��Դﵽ
		 * 		Ҫ���ɵ�XML�ĵ��Ľṹ����
		 * 4.����XmlWriter
		 * 5.��Document����ͨ��XmlWriterд����XML�ĵ�
		 * 6.�ر�XmlWriter
		 */
		
		List<Emp> empList =new ArrayList<Emp>();
		empList.add(new Emp(1,"����",22,"��",4400));
		empList.add(new Emp(2,"����",33,"��",5500));
		empList.add(new Emp(3,"����",44,"��",6600));
		
		try {
			//1
			Document doc=DocumentHelper.createDocument();
			/*
			 * 2
			 * Document�ṩ����Ӹ�Ԫ�صķ���
			 * Element addElement(String name)
			 * ��ǰ�ĵ�����Ӹ������ֵĸ�Ԫ�أ���
			 * ����Ԫ����һ��Element��ʵ�����أ��Ա���
			 * �Ըø�Ԫ�ؼ���׷����Ԫ�صȲ���
			 * ��Ҫע����Ǹ÷���ֻ�ܵ���һ�Σ���Ϊһ���ĵ�
			 * ��ֻ����һ����Ԫ�ء�
			 */
			Element root=doc.addElement("list");
			
			//3
			for(Emp emp:empList) {
				/*
				 * ��ÿһ��empʵ����һ��<emp>��ǩ
				 * ��ӵ���Ԫ����
				 */
				/*
				 * Element�ṩ����ǰ��ǩ�����
				 * ��Ϣ����ط���
				 * Element addElement(String name)
				 *��ǰ��ǩ�����ָ�����ֵ��ӱ�ǩ
				 * �����䷵��
				 * 
				 * Element addText(String text)
				 * ��ǰ��ǩ������ı���Ϣ������ֵ����
				 * ��ǰ��ǩ����������Ŀ��Ҳ�Ǳ��ڶԵ�ǰ
				 * ��ǩ��������
				 * 
				 * Element addAttribute(String name,String value)
				 * ��ǰ��ǩ��������ԣ�����ֵ���ǵ�ǰ��ǩ
				 */
				Element empEle=root.addElement("emp");
				
				Element nameEle=empEle.addElement("name");
				nameEle.addText(emp.getName());
				
				Element ageEle=empEle.addElement("age");
				ageEle.addText(Integer.toString(emp.getAge()));
				
				Element genderEle=empEle.addElement("gender");
				genderEle.addText(emp.getGender());
				
				Element salaryEle=empEle.addElement("salary");
				salaryEle.addText(Integer.toString(emp.getSalary()));
				//���id����
				empEle.addAttribute("id", emp.getId()+"");
				
			}
			//4
			XMLWriter writer=new XMLWriter(
					new FileOutputStream("myemp.xml"),
					OutputFormat.createPrettyPrint()
					);
			//5
			writer.write(doc);
			System.out.println("д�����");
			//6
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
