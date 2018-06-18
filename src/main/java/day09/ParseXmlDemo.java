package day09;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM4J解析XML文档
 * @author NiCo
 *
 */
public class ParseXmlDemo {
	public static void main(String[] args) {
		/*
		 * 解析XML文档大致流程：
		 * 1：创建SAXreader
		 * 2：使用SAXReader读取XML文档并返回
		 * 	   一个Document对象.
		 * 	 这一步也是DOM解析耗时耗资源的地方
		 * 3: 通过Document对象获取根元素
		 * 4：通过根元素按照XML文档结构逐级获取
		 * 		子元素，以达到遍历XML文档数据的目的
		 */
		/*
		 * 将emplist.xml文档中的每个员工信息
		 * 都解析为一个emp实例，并存入到一个集合中
		 */
		try {
			//1
			SAXReader reader = new SAXReader();
			
			//2
			Document doc=reader.read(new File("emplist.xml"));
			
			/*
			 * 3
			 * Element的每一个实例用于表示一个
			 * XML文档中一个元素（一对标签）
			 * 
			 * Element提供了用于获取其表示的元素
			 * 相关信息的方法
			 * 
			 * String getName()
			 * 获取当前元素的名字（标签名）
			 * 
			 * Element element(String name)
			 * 获取当前元素中指定名字的子元素
			 * 
			 * List elements()
			 * 获取当前元素中所有子元素
			 * 
			 * List elements(String name)
			 * 获取当前元素中所有同名子元素
			 * 
			 * String getText()
			 * 获取当前元素中间的文本
			 * 
			 * String elementText(String name)
			 * 获取当前标签下指定名字的子标签中间的文本
			 * 
			 * Attribute attribute(String name)
			 * 获取当前标签中指定名字的属性
			 */
			Element root=doc.getRootElement();
			//用来保存XML文档中解析出来的所有员工信息
			List<Emp> empList=new ArrayList<Emp>();
			
			/*
			 * 获取<list>标签中的所有子标签<emp>,并将
			 * 这些<emp>标签中的员工信息以若干emp实例
			 * 表示并存入empList集合
			 */
			List<Element> list=root.elements();
			for(Element empEle:list) {
				//获取员工名字
				Element nameEle=empEle.element("name");
				String name=nameEle.getTextTrim();
				//System.out.println(name);
				
				//获取年龄
				int age=Integer.parseInt(empEle.elementText("age"));
				//System.out.println(age);
				
				//获取性别
				String gender=empEle.elementText("gender");
				//System.out.println(gender);
				
				//获取工资
				int salary=Integer.parseInt(empEle.elementText("salary"));
				//System.out.println(salary);
				
				/*
				 * 获取id属性
				 * Attribute的每一个实例用于表示一个元素
				 * 中的一个属性
				 * 常用方法：
				 * String getName()
				 * 获取属性的名字
				 * 
				 * String getValue()
				 * 获取属性的值
				 * 
				 */
				Attribute attr=empEle.attribute("id");
				int id=Integer.parseInt(attr.getValue());
				//System.out.println(id);
				
				Emp emp=new Emp(id,name,age,gender,salary);
				empList.add(emp);
			}
			System.out.println("解析完毕");
			for(Emp emp:empList) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
