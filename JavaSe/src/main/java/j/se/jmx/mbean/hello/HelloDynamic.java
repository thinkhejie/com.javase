package j.se.jmx.mbean.hello;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

/**
 * @author 
 * @version 1.0
 
 * 动态MBean是在运行期才定义它的属性和方法，也就是说它有什么属性和方法是可以动态改变的。
 
 * 动态MBean主要利用一些辅助类（构造函数类MBeanConstructorInfo、属性类MBeanAttributeInfo、方法类MBeanOperationInfo）来完成这个功能，
  
 * 所有的动态MBean必须实现DynamicMBean接口。DynamicMBean写好后，使用方法和第一篇文章中普通的MBean一样。

　    给出一个动态MBean的实例，这个实例最初动态构了一个Name属性及一个print方法，当我们执行它的print方法之后，又给此MBean新增了一个print1方法。
 */
public class HelloDynamic implements DynamicMBean {
	//这是我们的属性名称
	private String name;
	private MBeanInfo mBeanInfo = null;
	private String className;
	private String description;
	private MBeanAttributeInfo[] attributes;
	private MBeanConstructorInfo[] constructors;
	private MBeanOperationInfo[] operations;
	MBeanNotificationInfo[] mBeanNotificationInfoArray;

	public HelloDynamic() {
		init();
		buildDynamicMBean();
	}

	private void init() {
		className = this.getClass().getName();
		description = "Simple implementation of a dynamic MBean.";
		attributes = new MBeanAttributeInfo[1];
		constructors = new MBeanConstructorInfo[1];
		operations = new MBeanOperationInfo[1];
		mBeanNotificationInfoArray = new MBeanNotificationInfo[0];
	}

	private void buildDynamicMBean() {
		//设定构造函数
		Constructor[] thisconstructors = this.getClass().getConstructors();
		constructors[0] = new MBeanConstructorInfo("HelloDynamic(): Constructs a HelloDynamic object", thisconstructors[0]);
		//设定一个属性
		attributes[0] = new MBeanAttributeInfo("Name", "java.lang.String", "Name: name string.", true, true, false);
		//operate method 我们的操作方法是print
		MBeanParameterInfo[] params = null;//无参数
		operations[0] = new MBeanOperationInfo("print", "print(): print the name", params, "void", MBeanOperationInfo.INFO);
		mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
	}

	//动态增加一个print1方法
	private void dynamicAddOperation() {
		init();
		operations = new MBeanOperationInfo[2];//设定数组为两个
		buildDynamicMBean();
		operations[1] = new MBeanOperationInfo("print1", "print1(): print the name", null, "void", MBeanOperationInfo.INFO);
		mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
	}

	@Override
	public Object getAttribute(String attribute_name) {
		if (attribute_name != null)
			return null;
		if (attribute_name.equals("Name"))
			return name;
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		if (attribute == null)
			return;
		String Name = attribute.getName();
		Object value = attribute.getValue();
		try {
			if (Name.equals("Name")) {
				// if null value, try and see if the setter returns any exception
				if (value == null) {
					name = null;
					// if non null value, make sure it is assignable to the attribute
				} else if ((Class.forName("java.lang.String")).isAssignableFrom(value.getClass())) {
					name = (String) value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AttributeList getAttributes(String[] attributeNames) {
		if (attributeNames == null)
			return null;
		AttributeList resultList = new AttributeList();
		// if attributeNames is empty, return an empty result list
		if (attributeNames.length == 0)
			return resultList;
		for (int i = 0; i < attributeNames.length; i++) {
			try {
				Object value = getAttribute(attributeNames[i]);
				resultList.add(new Attribute(attributeNames[i], value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		if (attributes == null)
			return null;
		AttributeList resultList = new AttributeList();
		// if attributeNames is empty, nothing more to do
		if (attributes.isEmpty())
			return resultList;
		// for each attribute, try to set it and add to the result list if successfull
		for (Iterator i = attributes.iterator(); i.hasNext();) {
			Attribute attr = (Attribute) i.next();
			try {
				setAttribute(attr);
				String name = attr.getName();
				Object value = getAttribute(name);
				resultList.add(new Attribute(name, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public Object invoke(String operationName, Object params[], String signature[]) throws MBeanException, ReflectionException {
		// Check for a recognized operation name and call the corresponding operation
		if (operationName.equals("print")) {
			//具体实现我们的操作方法print 
			System.out.println("Hello, " + name + ", this is HellDynamic!" + " operationName value is : " + operationName);
			dynamicAddOperation();
			return null;
		} else if (operationName.equals("print1")) {
			System.out.println("这是动态增加的一方法print1" + " operationName value is : " + operationName);
			return null;
		} else {
			// unrecognized operation name:
			throw new ReflectionException(new NoSuchMethodException(operationName), "Cannot find the operation " + operationName + " in " + className);
		}
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		//return this.getMBeanInfo();
		return mBeanInfo;
	}
}
