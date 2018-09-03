/**
 * @description 冒泡排序
 * 分类 -------------- 内部排序  and 交换排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- 数组为反序时,对于bubbleSort2改进算法，O(n^2)
 * 最优时间复杂度 ---- 数组为正序时，O(n)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 稳定
 */
package com.cqu.wb.sortAndSearch;

public class BubbleSort {

	public void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**
	 * 
	 * @param array 排序数组
	 * 1.比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。
	 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
	 * 3.针对所有的元素重复以上的步骤，除了最后一个。
	 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
	 */
	public void bubbleSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}

		for(int i = 0; i < array.length - 1; i++) {				// 每次最大元素就像气泡一样"浮"到数组的最后（i:排序遍历数组次数）
			for(int j = 0; j < array.length - 1 - i; j++) {		// 依次比较相邻的两个元素,使较大的那个向后移（j:数组下标）
				if(array[j] > array[j + 1]) {					// 如果条件改成A[i] >= A[i + 1],则变为不稳定的排序算法
					swap(array, j, j + 1);
				}
			}
		}
	}

	/**
	 * 
	 * @param array 排序数组
	 * @description 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可
	 */
	public void bubbleSort2(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}

		int i = array.length - 1;
		while(i > 0) {	
			int pos = 0;	// 每趟开始时,无记录交换
			for(int j = 0; j < i; j++) {		
				if(array[j] > array[j + 1]) {
					pos = j;	// 记录交换的位置
					swap(array, j, j + 1);
				}
			}
			i = pos;	// 为下一趟排序作准备
		}
	}

	/**
	 * 
	 * @param array 排序数组
	 * @description 传统冒泡排序中每一趟排序操作只能找到一个最大值或最小值,
	 * 				我们考虑利用在每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值(最大者和最小者) , 从而使排序趟数几乎减少了一半。
	 */
	public void bubbleSort3(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		// 初始化边界
		int left = 0;
		int right = array.length - 1;
		
		while(left < right) {
			// 前半轮(正向冒泡),将最大元素放到后面
			for(int i = left; i < right; i++) {
				if(array[i] > array[i + 1]) {
					swap(array, i, i + 1);
				}
			} 
			right --;	// 修改right值, 前移一位
			
			// 后半轮(反向冒泡),将最小元素放到前面
			for(int i = right; i > left; i--) {
				if(array[i - 1] > array[i]) {
					swap(array, i - 1, i);
				}
			}
			left ++;	// 修改left值,后移一位
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4 };    // 从小到大冒泡排序
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.bubbleSort3(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
