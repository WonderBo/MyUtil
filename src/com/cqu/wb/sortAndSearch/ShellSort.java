/**
 * @description 希尔排序（缩小增量排序）
 * 分类 -------------- 内部排序  and 插入排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- 根据增量序列的不同而不同。已知最好的为O(n(logn)^2)
 * 最优时间复杂度 ---- O(n)
 * 平均时间复杂度 ---- 根据增量序列的不同而不同
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 不稳定
 */
package com.cqu.wb.sortAndSearch;

/**
 * 
 * @author 汪波
 *@description 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 *				(1)插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
 *				(2)但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
 *				A.希尔排序的特点是，子序列的构成不是简单的逐段分割，而是将某个相隔某个增量的元素组成一个子序列。
 *				B.因此：希尔排序通过将全部元素分为几个（length/increment个）子序列进行插入排序来提升性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。
 *				然后算法再取越来越小的步长进行排序，算法的最后一步就是直接插入排序，但是到了这步，需排序的数据几乎是已排好的了，此时插入排序较快。
 *				C.希尔排序是不稳定的排序算法，虽然一次插入排序是稳定的，不会改变相同元素的相对顺序，
 *				但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱。				
 */
public class ShellSort {

	/**
	 * 
	 * @param array 排序数组
	 * @param increment	增量
	 * @description 带增量的插入排序，当增量为1时就是直接插入排序
	 */
	public void shellShort(int[] array, int increment) {
		for(int i = increment; i < array.length; i++) {
			int target = array[i];		// 记录要插入的数据  
			int j = i - increment;		// 同子序列前面的元素
			// 在子序列中从后向前遍历，比其大的同子序列元素向后移动，直到找到比其小的元素的位置
			while(j >= 0 && target < array[j]) {
				array[j + increment] = array[j];
				j -= increment;
			}
			array[j + increment] = target;
		}
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @description 缩小增量（插入）排序
	 */
	public void shellSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		int increment = array.length / 2;
		while(increment >= 1) {
			shellShort(array, increment);
			increment /= 2;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4 };    // 从小到大选择排序
		ShellSort shellSort = new ShellSort();
		shellSort.shellSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
