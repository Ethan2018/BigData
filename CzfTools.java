package com.sunnyinfo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

public class CzfTools {

	/**
	 * 求三个数的最大值
	 * @param a  
	 * @param b
	 * @param c
	 * @return 最大值
	 */
	public static int maxOfThreeNumbers(int a, int b, int c) {
		int max = a > b ? a : b;
		max = max > c ? max : c;
		return max;
	}
	
	/**
	 * 求数组的最大值和下标
	 * @param array  数组
	 */
	public static void getArrayMaxValue(int[] array) {
		int max = array[0];
		for (int i : array) {
			if (i > max) {
				max = i;
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == max) {
				System.out.println("数组中最大元素的下标是:" + i);
				System.out.println("数组中最大的元素是:" + array[i]);
			}
		}
	}
	
	/**
	 * 求数组中第二大的元素和下标
	 * @param array
	 */
	public static void getSecondMax(int[] array) {
		
		int max, secondMax;
		if (array[0] > array[1]) {
			max = array[0];
			secondMax = array[1];
		} else {
			max = array[1];
			secondMax = array[0];
		}
		for (int i = 2; i < array.length; i++) {
			if (array[i] >= max) {
				secondMax = max;
				max = array[i];
			} else if (array[i] > secondMax) {
				secondMax = array[i];
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == secondMax) {
				System.out.println("数组中第二大元素的下标是:" + i);
				System.out.println("第二大的元素是:" + array[i]);
			}
		}
	}
	/**
	 * 冒泡排序
	 * @param array 需要排序的数组
	 * @return 排序好的数组
	 */
	public static int[] bubbleSort(int[] array) {
		
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
				}
			}
		}
		return array;
	}
	
	/**
	 * 选择排序
	 * @param array 需要排序的数组
	 * @return 排序好的数组
	 */
	public static int[] selectedSort(int[] array) {
		
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					swap(array, i, j);
				}
			}
		}
		return array;
	}
	
	/**
	 * 从左往右普通查找一个 提高效率
	 * @param array 需要查找的数组
	 * @param key   按关键字查找
	 * @return  返回找到的数组下标
	 */
	public static int search(int[] array, int key) {
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 二分查找
	 * @param array 升序数组
	 * @param key   关键字
	 * @return      关键字所在的数组下标
	 */
	public static int binarySearch(int[] array, int key) {
		
		int low = 0;
		int high = array.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) >> 1;
			if (array[mid] == key) {
				return mid;
			} else if (array[mid] > key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}
	
	/**
	 * 数组反转
	 * @param array 原数组
	 * @return  反转后的数组
	 */
	public static int[] reverse(int[] array) {
		
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			swap(array, i, j);
		}
		return array;
	}
	
	/**
	 * 交换
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void swap(int[] array, int i, int j) {
		array[i] = array[i] ^ array[j];
		array[j] = array[i] ^ array[j];
		array[i] = array[i] ^ array[j];
	}
	
	/**
	 * 模拟trim方法,去除字符串两端的空格
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		int start = 0;
		int end = str.length() - 1;
		while (str.charAt(start) == ' ') {
			start++;
		}
		while (str.charAt(end) == ' ') {
			end--;
		}
		return str.substring(start, end + 1);
	}
	
	/**
	 * 字符串反转, 将字符串中的指定部分进行反转
	 * 
	 */
	public static void charArrayReverse(char[] arr, int start, int end) {
		
		for (int i = start, j = end; i < j; i++, j--) {
			charSwap(arr, i, j);	
		}
	}
	public static void charSwap(char[] arr, int i, int j) {
		char ch = arr[i];
		arr[i] = arr[j];
		arr[j] = ch;
	}
	public static String stringReverse(String str, int start, int end) {
		char[] arr = str.toCharArray();
		charArrayReverse(arr, start, end);
		return new String(arr);
	}
	public static void charArrayBubble(char[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					charSwap(array, j, j + 1);
				}
			}
		}
	}
	
	/**
	 * 对字符串中字符进行自然顺序排序
	 * @param str
	 * @return
	 */
	public static String stringSort(String str) {
		char[] arr = str.toCharArray();
		charArrayBubble(arr);
		return new String(arr);
	}
	
	/**
	 * 获取一个字符串在另一个字符串中出现的次数
	 * @param str
	 * @param key
	 * @return
	 */
	public static int getSubCount(String str, String key) {
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(key, index)) != -1) {
			index += key.length();
			count++;
		}
		return count;
	}
	
	/**
	 * AES加密
	 * @param str
	 * @return
	 */
	public static String aesEncrypt(String str) {
		byte[] byteArr = str.getBytes();
		StringBuilder sb = new StringBuilder();
		int tmp = 0123;
		for (int i = 0; i < byteArr.length; i++) {
			tmp ^= byteArr[i];
			String hexStr = Integer.toHexString(tmp);
			if (hexStr.length() < 2) {
				sb.append("0").append(hexStr);
			} else {
				sb.append(hexStr);
			}
		}
		return sb.toString();
	}
	
	/**
	 * AES解密
	 * @param str
	 * @return
	 */
	public static String aesDecipher(String str) {
		byte[] decipher = new byte[str.length() / 2];
		decipher[0] = (byte)(0123 ^ Integer.parseInt(str.substring(0 * 2, 0 * 2 + 2), 16));
		for (int i = 0; i < decipher.length - 1; i++) {
			decipher[i + 1] = (byte)(Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16) ^ Integer.parseInt(str.substring((i + 1) * 2, (i + 1) * 2 + 2), 16));
		}
		String strDecipher = new String(decipher);
		return strDecipher;
	}
	
	/**
	 * 字符串转16进制字符串
	 * @param str
	 * @return
	 */
	public static String string2Hex(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder();
		byte[] hexStr = str.getBytes();
		for (int i = 0; i < hexStr.length; i++) {
			int heigh = (hexStr[i] & 0xF0) >> 4;//高位值
			sb.append(chars[heigh]);//高位值对应的代表符号
			int low = (hexStr[i] & 0x0F);//低位值
			sb.append(chars[low]);//低位值对应的代表符号
		}
		return sb.toString();
	}
	/**
	 * 还原
	 * @param str
	 * @return
	 */
	public static String hex2Str(String str) {
		int length = str.length() / 2;
		char[] chars = str.toCharArray();
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
		   int position = i * 2;
		   bytes[i] = (byte) (charToByte(chars[position]) << 4 | charToByte(chars[position + 1]));
		}
		String strDecipher = new String(bytes);
		return strDecipher;
	}
	
	
	private static int charToByte(char c) {
		String radix16Symbol = "0123456789ABCDEF";
		  return (byte) radix16Symbol.indexOf(c);
	}
	
	/**
	 * 低字节高字节反转转化为16进制
	 * @param str
	 * @return
	 */
	public static String str2Reverse(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder();
		byte[] hexStr = str.getBytes();
		for (int i = 0; i < hexStr.length; i++) {
			int low = (hexStr[i] & 0x0F);//低位值
			sb.append(chars[low]);//低位值对应的代表符号
			int heigh = (hexStr[i] & 0xF0) >> 4;//高位值
			sb.append(chars[heigh]);//高位值对应的代表符号
		}
		return sb.toString();
	}
	/**
	 * 还原
	 * @param str
	 * @return
	 */
	public static String hex2ReverseStr(String str) {
		int length = str.length() / 2;
		char[] chars = str.toCharArray();
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			int position = i*2;
			bytes[i] = (byte) (charToByte(chars[position]) | charToByte(chars[position + 1]) << 4);
		}
		String strDecipher = new String(bytes);
		return strDecipher;
	}
	
	
	
	/**
	 * 0x9C加密
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		byte[] strByteArr = str.getBytes();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strByteArr.length; i++) {
			int temp = strByteArr[i] ^ 0x9C;
			String hexStr = Integer.toHexString(temp);
			if (hexStr.length() < 2) {
				stringBuilder.append("0").append(hexStr);
			} else {
				stringBuilder.append(hexStr);
			}
		}
		return stringBuilder.toString();
	}
	/**
	 * 0x9C解密
	 * @param str
	 * @return
	 */
	public static String decipher(String str) {
		byte[] decipher = new byte[str.length() / 2];
		//char[] chars = stringBuilder.toString().toCharArray();
		for (int i = 0; i < decipher.length; i++) {
			decipher[i] = (byte)(0x9C ^ Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16));
			//int position = 2 * i;
			//decipher[i] = (byte) (charToByte(chars[position]) << 4 | charToByte(chars[position + 1]));
		}
		String strDecipher = new String(decipher);
		return strDecipher;
	}
	
	
	/**
	 * 删除空目录
	 * @param dir
	 */
	public static void deleteEmptyDir(String dir) {
		boolean success = (new File(dir)).delete();
		if (success) {
			System.out.println("Successfully deleted empty directory: " + dir);
		} else {
			System.out.println("Failed to delete empty directory: " + dir);
		}
	}
	
	
	
	
	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		if (file.isDirectory()) {
			String[] children = file.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return file.delete();
	}
	
	/**
	 * 递归实现查找当前目录下的所有文件,包括子目录
	 * @param file
	 */
	public static void searchFiles(File file, ArrayList<File> list) {
		if (file.isFile()) {
			System.out.println(file);
			return;
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				searchFiles(file2, list);
			} else {
				String name = file2.getName();
				if (name.endsWith(".txt") && !name.equals("zong.txt")) {
					list.add(file2);
				}
				//System.out.println(file2);
			}
		}
	}
	
	
	public static void searchFiles(File file) {
		if (file.isFile()) {
			System.out.println(file);
			return;
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				searchFiles(file2);
			} else {
				System.out.println(file2);
			}
		}
	}
	/**
	 * 序列化对象
	 * @param file   写入的磁盘文件对象
	 * @param t    需要序列化的类
	 */
	public static <T> void objSerializing(File file, T t) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.writeObject(t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 对象的反序列化
	 * @param file   序列化生成的字节码文件
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> void deSerializing(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object obj = ois.readObject();
		T t = (T)obj;
		System.out.println(t);
		ois.close();
	}
	
	
	/**
	 * 记录一个程序的使用次数，超过5次提示去注册
	 * @param file
	 * @throws IOException
	 */
	public static void fileUsedNo(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileInputStream fis = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fis);
		
		int count = 1;
		String value = properties.getProperty("time");
		if (value != null) {
			count = Integer.parseInt(value);
		}
		if (count > 5) {
			System.out.println("Please Register!");
		}
		count++;
		properties.setProperty("time", count + "");
		FileOutputStream fos = new FileOutputStream(file);
		properties.store(fos, "Number of times tested");
		fis.close();
		fos.close();
	}
}
