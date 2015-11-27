package j.sort;

import java.util.Comparator;
import java.util.Random;

public class Quicksort {
	public static final Random RND = new Random();

	private static <E> void swap(E[] array, int i, int j) {
		E tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private static <E> int partition(E[] array, int begin, int end, Comparator<? super E> cmp) {
		int index = begin + RND.nextInt(end - begin + 1);
		E pivot = array[index];
		swap(array, index, end);
		for (int i = index = begin; i < end; ++i) {
			if (cmp.compare(array[i], pivot) <= 0) {
				swap(array, index++, i);
			}
		}
		swap(array, index, end);
		return (index);
	}

	private static <E> void qsort(E[] array, int begin, int end, Comparator<? super E> cmp) {
		if (end > begin) {
			int index = partition(array, begin, end, cmp);
			qsort(array, begin, index - 1, cmp);
			qsort(array, index + 1, end, cmp);
		}
	}

	public static <E> void sort(E[] array, Comparator<? super E> cmp) {
		qsort(array, 0, array.length - 1, cmp);
	}

	/**
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	@SuppressWarnings("unchecked")
	public static void sort(Comparable[] data, int low, int high) {
		// ��ŦԪ,һ���Ե�һ��Ԫ��Ϊ��׼���л��� ����
		Comparable pivotKey = data[low];
		// ����ɨ���ָ��i,j;i����߿�ʼ,j���ұ߿�ʼ ����
		int i = low;
		int j = high;
		if (low < high) {
			// ���������˽�������м�ɨ�� ����
			while (i < j) {
				while (i < j && data[j].compareTo(pivotKey) > 0) {
					j--;
				}
				// end while ����
				if (i < j) {
					// ����ŦԪ��С���ƶ������ ����
					data[i] = data[j];
					i++;
				}
				// end if ��
				while (i < j && data[i].compareTo(pivotKey) < 0) {
					i++;
				}
				// end while ����
				if (i < j) {
					// ����ŦԪ�ش���ƶ����ұ� ����
					data[j] = data[i];
					j--;
				}// end if ����
			}
			// end while ����
			// ��ŦԪ���ƶ�����ȷλ�� ����
			data[i] = pivotKey;
			// ǰ����ӱ�ݹ����� ����
			sort(data, low, i - 1);
			// �����ӱ�ݹ����� ����
			sort(data, i + 1, high);
		}// end if ����

	}// end sort ��

	// public static void main(String[] args) {
	// // ��JDK1.5�汾����,��������Ϳ����Զ�װ�� ����
	// // int,double�Ȼ����͵İ�װ����ʵ����Comparable�ӿ� ����
	// Comparable[] c = { 4, 9, 23, 1, 45, 27, 5, 2 };
	// sort(c, 0, c.length - 1);
	// for (Comparable data : c) {System.out.println(data);
	// ��} ����}

	// }
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Comparable[] c = { 4, 9, 23, 1, 45, 27, 5, 2 };
		sort(c, 0, c.length - 1);
		for (Comparable data : c) {
			System.out.println(data);
		}
	}
}
