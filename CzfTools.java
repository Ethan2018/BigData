package com.sunnyinfo.Utils;

public class CzfTools {

	/**
	 * �������������ֵ
	 * @param a  
	 * @param b
	 * @param c
	 * @return ���ֵ
	 */
	public static int maxOfThreeNumbers(int a, int b, int c) {
		int max = a > b ? a : b;
		max = max > c ? max : c;
		return max;
	}
	
	/**
	 * ����������ֵ���±�
	 * @param array  ����
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
				System.out.println("���������Ԫ�ص��±���:" + i);
				System.out.println("����������Ԫ����:" + array[i]);
			}
		}
	}
	
	/**
	 * �������еڶ����Ԫ�غ��±�
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
				System.out.println("�����еڶ���Ԫ�ص��±���:" + i);
				System.out.println("�ڶ����Ԫ����:" + array[i]);
			}
		}
	}
	/**
	 * ð������
	 * @param array ��Ҫ���������
	 * @return ����õ�����
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
	 * ѡ������
	 * @param array ��Ҫ���������
	 * @return ����õ�����
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
	 * ����������ͨ����һ�� ���Ч��
	 * @param array ��Ҫ���ҵ�����
	 * @param key   ���ؼ��ֲ���
	 * @return  �����ҵ��������±�
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
	 * ���ֲ���
	 * @param array ��������
	 * @param key   �ؼ���
	 * @return      �ؼ������ڵ������±�
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
	 * ���鷴ת
	 * @param array ԭ����
	 * @return  ��ת�������
	 */
	public static int[] reverse(int[] array) {
		
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			swap(array, i, j);
		}
		return array;
	}
	
	/**
	 * ����
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
	 * ģ��trim����,ȥ���ַ������˵Ŀո�
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
	 * �ַ�����ת, ���ַ����е�ָ�����ֽ��з�ת
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
	 * ���ַ������ַ�������Ȼ˳������
	 * @param str
	 * @return
	 */
	public static String stringSort(String str) {
		char[] arr = str.toCharArray();
		charArrayBubble(arr);
		return new String(arr);
	}
	
	/**
	 * ��ȡһ���ַ�������һ���ַ����г��ֵĴ���
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
	
}
