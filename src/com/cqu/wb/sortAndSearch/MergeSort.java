/**
 * @description 归并排序（有序数组的归并）
 * 分类 -------------- 内部排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(nlogn)
 * 最优时间复杂度 ---- O(nlogn)
 * 平均时间复杂度 ---- O(nlogn)
 * 所需辅助空间 ------ O(n)
 * 稳定性 ------------ 稳定
 */
package com.cqu.wb.sortAndSearch;

public class MergeSort {

	public void mergeSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}

		// mergeSortByRecursion(array, 0, array.length - 1);
		mergeSortByIteration(array);
	}

	/**
	 * 
	 * @param array 排序数组
	 * @param left 数组最左下标
	 * @param right 数组最右下标
	 * @description 递归实现的归并排序(自顶向下)
	 * 				递归实现的归并排序是算法设计中分治策略的典型应用，我们将一个大问题分割成小问题分别解决，然后用所有小问题的答案来解决整个大问题。
	 */
	public void mergeSortByRecursion(int[] array, int left, int right) {
		// 当待排序的序列长度为1时，递归开始回溯，进行merge操作（递归边界条件）
		if(left == right) {		
			return;
		}

		// 分治递归
		int middle = (right + left) >> 1;
		mergeSortByRecursion(array, left, middle);
		mergeSortByRecursion(array, middle + 1, right);
		// 合并
		merge(array, left, middle, right);
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @description 非递归(迭代)实现的归并排序(自底向上)
	 * 				非递归(迭代)实现的归并排序首先进行是两两归并，然后四四归并，然后是八八归并，一直下去直到归并了整个数组。
	 */
	public void mergeSortByIteration(int[] array) {
		// 子数组索引,前一个为A[left...middle]，后一个子数组为A[middle+1...right]，子数组容量每次成倍增加
		int left = 0;
		int middle = 0;
		int right = 0;
		
		// 子数组的大小i初始为1（此时有序），每轮翻倍
		for(int i = 1; i < array.length; i *= 2) {
			left = 0;
			// 左子数组尾下标不能越界
			while(left + i - 1 < array.length - 1) {
				middle = left + i - 1;
				// 右子数组大小可能不够，不够则直接取数组尾
				if(middle + i < array.length) {
					right = middle + i;
				} else {
					right = array.length - 1;
				}
				
				merge(array, left, middle, right);	// 合并前两个前两个有序数组为较大有序数组
				// 子数组索引向后移动
				left = right + 1;
			}
		}
	}

	/**
	 * 
	 * @param array 排序数组
	 * @param left 合并左数组最左下标
	 * @param middle 合并左数组最右下标（middle + 1：合并右数组最左下标）
	 * @param right 合并右数组最右下标
	 * @description 依次比较并合并两个已排好序的数组A[left...mid]和A[mid+1...right]
	 * 				归并排序算法主要依赖归并(Merge)操作。归并操作指的是将两个已经排序的序列合并成一个序列的操作
	 * 				1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
	 * 				2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
	 *              3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
	 *              4.重复步骤3直到某一指针到达序列尾
	 *              5.将另一序列剩下的所有元素直接复制到合并序列尾
	 */
	public void merge(int[] array, int left, int middle, int right) {
		int leftIndex = left;	// 合并前左数组下标
		int rightIndex = middle + 1;	// 合并前右数组下标
		int[] tempArray = new int[right - left + 1];	// 合并临时数组（辅助空间O(n)）
		int tempIndex = 0;	// 合并临时数组下标

		// 当左数组和右数组都没遍历完时，需要比较左右数组的元素值，取较小值放入临时数组
		while (leftIndex <= middle && rightIndex <= right) {
			if(array[leftIndex] <= array[rightIndex]) {
				tempArray[tempIndex ++] = array[leftIndex ++];
			} else {
				tempArray[tempIndex ++] = array[rightIndex ++];
			}
		}

		// 如果右数组已经遍历完，直接将左数组其余值拷贝进临时数组
		while(leftIndex <= middle) {
			tempArray[tempIndex ++] = array[leftIndex ++];
		}

		// 如果左数组已经遍历完，直接将右数组其余值拷贝进临时数组
		while(rightIndex <= right) {
			tempArray[tempIndex ++] = array[rightIndex ++];
		}

		// 用临时数组（排好序）的元素覆盖掉左右数组的元素
		for(int i = 0; i < tempArray.length; i++) {
			array[left + i] = tempArray[i];
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4 };    // 从小到大归并排序
		MergeSort mergeSort = new MergeSort();
		mergeSort.mergeSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
