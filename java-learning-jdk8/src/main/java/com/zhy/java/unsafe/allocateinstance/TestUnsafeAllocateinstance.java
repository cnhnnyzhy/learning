package com.zhy.java.unsafe.allocateinstance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sun.misc.Unsafe;

/**
 * 对Unsafe中的针对Class的操作方法进行测试
 * @author yang.zhang3
 *
 */
public class TestUnsafeAllocateinstance {
	private static Unsafe unsafe;
	@Before
	public void before(){
        try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			unsafe = (Unsafe)theUnsafe.get(Unsafe.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUnsafeAllocateinstance(){
		/**
		 * 使用Unsafe.allocateInstance()极速创建java对象
		 * 1.使用这种方法快速创建对象是在内存中分配一块满足条件的内存地址，其它的不管，因此相当快。
		 * 2.这种方法没有调用构造函数，构造函数中的代码不会执行。因此即使构造函数是私有的，也可以创建，因为Unsafe类的构造函数也是私有的。
		 */
		try {
			A a = (A)unsafe.allocateInstance(A.class);
			System.out.println(a.a());
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUnsafeAllocateinstanceForStatic(){
		/**
		 * 使用Unsafe.allocateInstance()极速创建java对象，测试静态语句块儿是否执行？
		 */
		try {
			MyClass myClass = (MyClass)unsafe.allocateInstance(MyClass.class);
			System.out.println(myClass.a());
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUnsafeDefineClass(){
		/**
		 * 动态加载Java Class
		 * 1.Class文件加密存储，内存里解密并加装。可以用来加密Java程序。
		 * 2.没有使用ClassLoader，要比ClassLoader更好一些。
		 */
		try {
			byte[] content = getClassContent();
			Class c = unsafe.defineClass(null, content, 0, content.length, null, null);
			Object o = c.getMethod("a", null).invoke(c.newInstance(), null);
			System.out.println(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//读取class文件的内容
	private static byte[] getClassContent() throws Exception{
		File f = new File("D:/workspace/java-learning-jdk8/target/classes/com/zhy/java/unsafe/allocateinstance/A.class");
		FileInputStream fis = new FileInputStream(f);
		byte[] content = new byte[(int)f.length()];
		fis.read(content);
		fis.close();
		return content;
	}
	
	
	@Test
	public void testUnsafePutFieldValue(){
		/**
		 * “破解”代码，修改对象中私有变量的值。
		 * 运用反射也可以做到。
		 */
		Guard g = new Guard();
		Field f;
		try {
			f = g.getClass().getDeclaredField("ACCESS_ALLOWED");
			unsafe.putInt(g, unsafe.objectFieldOffset(f), 42);
			boolean result = g.giveAccess();
			System.out.println(result);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testArrayList() throws ClassNotFoundException, IOException{
		List<User> list = new ArrayList<>();
		list.add(new User("a", 1));
		list.add(new User("b", 2));
		
		/*List<User> list2 = new ArrayList<>(list);
		System.out.println(list.size());
		System.out.println(list2.size());
		Collections.copy(list2, list);*/
		
		List<User> list2 = deepCopy(list);
		
		System.out.println(list == list2);
		
		for(int i=0; i<2; i++){
			System.out.println(list.get(i) == list2.get(i));
		}
		
		
	}
	
	public List<User> deepCopy(List<User> src) throws IOException, ClassNotFoundException{   
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();   
        ObjectOutputStream out = new ObjectOutputStream(byteOut);   
        out.writeObject(src);   
       
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
        ObjectInputStream in =new ObjectInputStream(byteIn);   
        List<User> dest = (List<User>)in.readObject();
        return dest;   
	}
}
