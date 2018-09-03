/**
 * @description 插入排序（直接插入排序）
 * 分类 ------------- 内部排序  and 插入排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- 最坏情况为输入序列是反序排列的,此时时间复杂度O(n^2)
 * 最优时间复杂度 ---- 最好情况为输入序列是正序排列的,此时时间复杂度O(n)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 稳定
 */
package com.cqu.wb.sortAndSearch;

public class InsertionSort {

	/**
	 * 
	 * @param array 排序数组
	 * 1.从第一个元素开始，该元素可以认为已经被排序
	 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描
	 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
	 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
	 * 5.将新元素插入到该位置后
	 * 6.重复步骤2~5
	 * @description (1)对于未排序数据(右手抓到的牌)，在已排序序列(左手已经排好序的手牌)中从后向前扫描，找到相应位置并插入。
	 * 				插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
	 * 				因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
	 * 				(2)插入排序不适合对于数据量比较大的排序应用。但是，如果需要排序的数据量很小，比如量级小于千，那么插入排序还是一个不错的选择。
	 * 				 插入排序在工业级库中也有着广泛的应用，在STL的sort算法和stdlib的qsort算法中，都将插入排序作为快速排序的补充，
	 * 				用于少量元素的排序（通常为8个或以下）。
	 */
	public void insertionSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		for(int i = 1; i < array.length; i++) {		// 类似抓扑克牌排序（假设第一个数位置时正确的；要往后移，必须要假设第一个）
			int target = array[i];					// 右手抓到一张扑克牌
			int j = i - 1;							// 拿在左手上的牌总是排序好的
			while(j >= 0 && array[j] > target) {	// 将抓到的牌与手牌从右向左进行比较，如果该手牌比抓到的牌大，就将其右移，直到该手牌小于或等于抓到的牌
				array[j + 1] = array[j];
				j --;
			}
			array[j + 1] = target;	// 将抓到的牌插入到该手牌右边(相等元素的相对次序未变，所以插入排序是稳定的)
		}
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @description (1)由于数组左边部分已经为排序，在查找插入位置时使用二分查找的方式（二分插入排序）
	 * 				(2)当n较大时，二分插入排序的比较次数比直接插入排序的最差情况好得多，但比直接插入排序的最好情况要差，
	 * 				所以当元素初始序列已经接近升序时，直接插入排序比二分插入排序比较次数少。二分插入排序元素移动次数与直接插入排序相同，依赖于元素初始序列
	 */
	public void insertionSort2(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		for(int i = 1; i < array.length; i++) {
			int target = array[i];
			int left = 0;
			int right = i - 1;
			int middle = 0;
			// 采用二分法定位插入的位置（最终状态为：right|插入位置|left）
			while(left <= right) {
				middle = (left + right) >> 1;
				if(array[middle] > target) {
					right = middle - 1;		// 必须减1，否则可能陷入死循环
				} else {
					left = middle + 1;		// 必须加1，否则可能陷入死循环
				}
			}
			
			// 将欲插入位置右边的数据全部向右移动一个单位
			for(int j = i - 1; j >= left; j--) {
				array[j + 1] = array[j];
				j --;
			}
			array[left] = target;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4 };    // 从小到大插入排序
		InsertionSort insertionSort = new InsertionSort();
		insertionSort.insertionSort2(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
