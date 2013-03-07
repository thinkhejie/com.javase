package j.se.jmx.mbean.hello;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.ReflectionException;

/**
* @author Administrator
*
*/
public class DynamicHello implements DynamicMBean {
	/* （非 Javadoc）
	  * @see javax.management.DynamicMBean#getAttribute(java.lang.String)
	  */
	private int times;

	/* （非 Javadoc）
	  * @see com.joy_cz.jmx.HelloMBean#getTimes()
	  */
	public int getTimes() {
		// TODO 自动生成方法存根
		return times;
	}

	/* （非 Javadoc）
	  * @see com.joy_cz.jmx.HelloMBean#sayHello()
	  */
	public void sayHello() {
		// TODO 自动生成方法存根
		System.out.println("say hello to you ~");
		times++;
	}

	/* （非 Javadoc）
	  * @see com.joy_cz.jmx.HelloMBean#setTimes(int)
	  */
	public void setTimes(int t) {
		// TODO 自动生成方法存根
		times = t;
	}

	@Override
	public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
		// TODO 自动生成方法存根
		return null;
	}

	/* （非 Javadoc）
	  * @see javax.management.DynamicMBean#getAttributes(java.lang.String[])
	  */
	@Override
	public AttributeList getAttributes(String[] attributes) {
		// TODO 自动生成方法存根
		return null;
	}

	/* （非 Javadoc）
	  * @see javax.management.DynamicMBean#getMBeanInfo()
	  */
	@Override
	public MBeanInfo getMBeanInfo() {
		// TODO 自动生成方法存根
		return null;
	}

	/* （非 Javadoc）
	  * @see javax.management.DynamicMBean#invoke(java.lang.String, java.lang.Object[], java.lang.String[])
	  */
	@Override
	public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
		// TODO 自动生成方法存根
		return null;
	}

	/* （非 Javadoc）
	  * @see javax.management.DynamicMBean#setAttribute(javax.management.Attribute)
	  */
	@Override
	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
		// TODO 自动生成方法存根
	}

	/* （非 Javadoc）
	  * @see javax.management.DynamicMBean#setAttributes(javax.management.AttributeList)
	  */
	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		// TODO 自动生成方法存根
		return null;
	}
}
