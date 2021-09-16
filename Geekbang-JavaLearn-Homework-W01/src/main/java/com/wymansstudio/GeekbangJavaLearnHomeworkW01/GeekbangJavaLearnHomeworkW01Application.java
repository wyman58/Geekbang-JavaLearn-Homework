package com.wymansstudio.GeekbangJavaLearnHomeworkW01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class GeekbangJavaLearnHomeworkW01Application extends ClassLoader{

	public static void main(String[] args) {

		//SpringApplication.run(GeekbangJavaLearnHomeworkW01Application.class, args);
		//System.out.println(GeekbangJavaLearnHomeworkW01Application.class.getClassLoader().toString());
		//System.out.println(GeekbangJavaLearnHomeworkW01Application.class.getClassLoader().getParent().toString());
		//System.out.println(GeekbangJavaLearnHomeworkW01Application.class.getClassLoader().getParent().getParent().toString());
		//GeekbangJavaLearnHomeworkW01Application app = new GeekbangJavaLearnHomeworkW01Application();

		try{
			Object hello = new GeekbangJavaLearnHomeworkW01Application().findClass("Hello").newInstance();
			Class clazz = hello.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){
				System.out.println(method.getName());
				for(Parameter p : method.getParameters()){
					System.out.println(p.getName());
				}
				method.invoke(hello);
			}

		}catch(ClassNotFoundException e){

		}catch (IllegalAccessException e){

		}catch (InstantiationException e){

		}catch (IllegalArgumentException e){

		}catch (InvocationTargetException e){

		}
	}


	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> cls = null;
		try{
			//Load the encrypted class file here
			byte[] array = Files.readAllBytes(Paths.get("src/main/resources/Hello.xlass"));

			//print the encrypted class file in hex
			StringBuilder hex = new StringBuilder(array.length * 2);
			for (byte b : array) {
				hex.append(String.format("%02x", b));
			}
			System.out.println("Encrypted Byte:");
			System.out.println(hex.toString());

			//decrypt and print the decrypted class file in hex
			int i = 255;
			int index = 0;
			hex = new StringBuilder(array.length * 2);
			for (byte b : array) {
				byte newByte = (byte) ((byte) i - b);
				hex.append(String.format("%02x", newByte));
				array[index++] = newByte;
			}
			System.out.println("Decrypted Byte:");
			System.out.println(hex.toString());


			cls = defineClass(name, array, 0, array.length);
			System.out.println(cls.getClassLoader().toString());

		}catch (IOException e){

		}
		return  cls;
	}
}
