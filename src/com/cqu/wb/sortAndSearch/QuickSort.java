/**
 * @description 快速排序
 * 分类 ------------- 内部排序  and 交换排序
 * 数据结构 --------- 数组
 * 最差时间复杂度 ---- 每次选取的基准都是最大（或最小）的元素，导致每次只划分出了一个分区，需要进行n-1次划分才能结束递归，时间复杂度为O(n^2)
 * 最优时间复杂度 ---- 每次选取的基准都是中位数，这样每次都均匀的划分出两个分区，只需要logn次划分就能结束递归，时间复杂度为O(nlogn)
 * 平均时间复杂度 ---- O(nlogn)
 * 所需辅助空间 ------ 主要是递归造成的栈空间的使用(用来保存left和right等局部变量)，取决于递归树的深度，一般为O(logn)，最差为O(n)       
 * 稳定性 ---------- 不稳定
 * （1）快速排序是不稳定的排序算法，不稳定发生在patition时基准元素与array[smallEndIndex+1]交换的时刻。
 * （2）Java系统提供的Arrays.sort函数。对于基础类型，底层使用快速排序。对于非基础类型，底层使用归并排序。这是考虑到排序算法的稳定性。
 * 对于基础类型，相同值是无差别的，排序前后相同值的相对位置并不重要，所以选择更为高效的快速排序，尽管它是不稳定的排序算法；
 * 而对于非基础类型，排序前后相等实例的相对位置不宜改变，所以选择稳定的归并排序。
 */
package com.cqu.wb.sortAndSearch;

public class QuickSort {

	public void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @param start 数组头下标
	 * @param end 数组尾下标
	 * @return 基准下标
	 * @description 在数组中选择最后一个数作为基准，把数组中的数据分为两序列，比基准小的移到数组左边，反之大的移到右边（单指针实现）
	 */
	public int patition(int[] array, int start, int end) {
		//smallEndIndex: 由于不使用其余数据结构，因此需要一边界位置指针指示较大子数组与较小子数组的分界。该指针指向较小数组的末元素，初始为startIndex -1
		int smallEndIndex = start - 1;
		for(int i = start; i < end; i++) {
			if(array[i] <= array[end] ) {
				smallEndIndex ++;
				if(smallEndIndex != i) {
					swap(array, smallEndIndex, i);
				}
			}
		}
		// 交换基准与最左较大数的位置，该操作很有可能把后面元素的稳定性打乱，所以快速排序是不稳定的排序算法
		swap(array, ++ smallEndIndex, end);
		
		// 返回基准的具体下标
		return smallEndIndex;
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @param start 数组头下标
	 * @param end 数组尾下标
	 * @return 基准下标
	 * @description 在数组中选择最后一个数作为基准，把数组中的数据分为两序列，比基准小的移到数组左边，反之大的移到右边（双指针实现）
	 * 				注意：end指针先动还是start指针先动取决于基准数位置。因为在最后两个指针相遇的时候，要交换基准数到相遇的位置。若基准数在数组尾，则要求
	 * 				指针相遇位置为大数，而start指针寻找大数，因此start指针先动。
	 */
	public int patition2(int[] array, int start, int end) {
		int pivotIndex = end;	// 基准下标
		
		// 循环交换直到两指针重合
		while(start < end) {
			// 从前向后遍历数组，直到找到一个大于基准的数（需要判断end > start，因为循环内部end和start都在变化）
			while(start < end && array[start] <= pivotIndex) {
				start ++;
			}
			// 从后向前遍历数组，直到找到一个小于基准的数（需要判断end > start，因为循环内部end和start都在变化）
			while(end > start && array[end] >= pivotIndex) {
				end --;
			}
			// 把大数交换到右边，把小数交换到左边
			swap(array, start, end);
		}
		// 把基准交换到中间
		swap(array, end, pivotIndex);
		
		// 返回基准下标
		return end;
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @param start 数组头下标
	 * @param end 数组尾下标
	 * @description 分治法（递归）实现快速排序
	 */
	public void quickSort(int[] array, int start, int end) {
		// 递归结束条件（也可以判断后面方法入参）
		if(start >= end) {
			return;
		}
		
		// 分治递归
		int pivotIndex = patition(array, start, end);
		quickSort(array, start, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, end);
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @description 快速排序
	 * 1.从序列中挑出一个元素，作为"基准"(pivot).
	 * 2.把所有比基准值小的元素放在基准前面，所有比基准值大的元素放在基准的后面（相同的数可以到任一边），这个称为分区(partition)操作。
     * 3.对每个分区递归地进行步骤1~2，递归的结束条件是序列的大小是0或1，这时整体已经被排好序了。
	 */
	public void quickSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		quickSort(array, 0, array.length - 1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4, 10, 6 };    // 从小到大插入排序
		QuickSort quickSort = new QuickSort();
		quickSort.quickSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
