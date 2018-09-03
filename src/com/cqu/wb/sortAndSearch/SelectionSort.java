/**
 * @description 选择排序（简单选择排序）
 * 分类 -------------- 内部排序  and 选择排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(n^2)
 * 最优时间复杂度 ---- O(n^2)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 不稳定
 */
package com.cqu.wb.sortAndSearch;

public class SelectionSort {

	public void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @description (1)工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
	 * 				然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
	 * 				(2)选择排序与冒泡排序的区别：冒泡排序通过依次交换相邻两个顺序不合法的元素位置，从而将当前最小（大）元素放到合适的位置；
	 * 				而选择排序每遍历一次都记住了当前最小（大）元素的位置，最后仅需一次交换操作即可将其放到合适的位置。
	 * 				(3)注：选择排序是不稳定的排序算法，不稳定发生在最小元素与array[i]交换的时刻。
	 */
	public void selectionSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		int minIndex = 0;
		for(int i = 0; i < array.length - 1; i++) {		// i为已排序序列的末尾，只需要比较n-1次
			minIndex = i;
			for(int j = i + 1; j < array.length; j++) {		// 未排序序列，从i+1开始比较，因为minIndex默认为i了，i就没必要比
				// 找出未排序序列中最小值的下标
				if(array[j] < array[minIndex]) {		
					minIndex = j;
				}
			}
			
			if(minIndex != i) {		// 如果minIndex不为i，说明找到了更小的值，交换之
				swap(array, i, minIndex);	// 放到已排序序列的末尾，该操作很有可能把稳定性打乱，所以选择排序是不稳定的排序算法
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4 };    // 从小到大选择排序
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.selectionSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
