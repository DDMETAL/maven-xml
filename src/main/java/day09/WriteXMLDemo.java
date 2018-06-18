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
 * 使用DOM写出XML文档
 * 
 * @author NiCo
 *
 */
public class WriteXMLDemo {
	public static void main(String[] args) {
		/*
		 * 写出XML文档的大致步骤
		 * 1.创建一个Document对象，表示一个空白文档
		 * 2.向Document对象中添加根元素
		 * 3.向根元素中逐级添加子元素，以达到
		 * 		要生成的XML文档的结构内容
		 * 4.创建XmlWriter
		 * 5.将Document对象通过XmlWriter写出成XML文档
		 * 6.关闭XmlWriter
		 */
		
		List<Emp> empList =new ArrayList<Emp>();
		empList.add(new Emp(1,"张三",22,"男",4400));
		empList.add(new Emp(2,"王五",33,"男",5500));
		empList.add(new Emp(3,"李四",44,"男",6600));
		
		try {
			//1
			Document doc=DocumentHelper.createDocument();
			/*
			 * 2
			 * Document提供了添加根元素的方法
			 * Element addElement(String name)
			 * 向当前文档中添加给定名字的根元素，并
			 * 将该元素以一个Element的实例返回，以便于
			 * 对该根元素继续追加子元素等操作
			 * 需要注意的是该方法只能调用一次，因为一个文档
			 * 中只能有一个根元素。
			 */
			Element root=doc.addElement("list");
			
			//3
			for(Emp emp:empList) {
				/*
				 * 将每一个emp实例以一个<emp>标签
				 * 添加到根元素中
				 */
				/*
				 * Element提供了向当前标签中添加
				 * 信息的相关方法
				 * Element addElement(String name)
				 *向当前标签中添加指定名字的子标签
				 * 并将其返回
				 * 
				 * Element addText(String text)
				 * 向当前标签中添加文本信息，返回值还是
				 * 当前标签，这样做的目的也是便于对当前
				 * 标签继续操作
				 * 
				 * Element addAttribute(String name,String value)
				 * 向当前标签中添加属性，返回值还是当前标签
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
				//添加id属性
				empEle.addAttribute("id", emp.getId()+"");
				
			}
			//4
			XMLWriter writer=new XMLWriter(
					new FileOutputStream("myemp.xml"),
					OutputFormat.createPrettyPrint()
					);
			//5
			writer.write(doc);
			System.out.println("写出完毕");
			//6
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
