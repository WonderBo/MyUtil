/**
 * @description 推排序（最大堆升序排序，最小堆降序排序）
 * 分类 -------------- 内部排序  and 选择排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(nlogn)
 * 最优时间复杂度 ---- O(nlogn)
 * 平均时间复杂度 ---- O(nlogn)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 不稳定
 * 堆排序是不稳定的排序算法，不稳定发生在堆顶元素与array[i]交换的时刻。
 */
package com.cqu.wb.sortAndSearch;

public class HeapSort {

	public void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @param start 数组起始位置
	 * @param end 数组结束位置
	 * @description 从上往下堆调整：递归实现从array[start]逐步向下进行堆调整
	 */
	public void heapAdjustByRecursion(int[] array, int start, int end) {
		int adjustIndex = start;	// 调整指针指向当前结点与其左右孩子三者之中的最大值
		int leftChildIndex = 2 * start + 1;		// 左孩子索引
		int rightChildIndex = 2 * start + 2;	// 右孩子索引
		if(leftChildIndex <= end && array[adjustIndex] < array[leftChildIndex]) {
			adjustIndex = leftChildIndex;
		}
		if(rightChildIndex <= end && array[adjustIndex] < array[rightChildIndex]) {
			adjustIndex = rightChildIndex;
		}
		
		if(adjustIndex != start) {		// maxIndex = start则已经达到最大堆
			swap(array, start, adjustIndex);	// 把当前结点和它的最大(直接)子节点进行交换
			heapAdjustByRecursion(array, adjustIndex, end);	// 递归调用，继续从当前结点向下进行堆调整
		}
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @param start 数组起始位置
	 * @param end 数组结束位置
	 * @description 从上往下堆调整：循环实现从array[start]逐步向下进行堆调整
	 */
	public void heapAdjustByIteration(int[] array, int start, int end) {
		int adjustIndex = start;
		for(int i = start * 2 + 1; i <= end; i = 2 * i + 1) {	// 左右孩子的节点分别为2*i+1,2*i+2
			if(i + 1 <= end && array[i] < array[i + 1]) {		// 选择出左右孩子较小的下标
				i ++;
			}
			if(array[adjustIndex] >= array[i]) {	// 已经为最大堆
				break;
			}
			swap(array, adjustIndex, i);	// 不为最大堆则将子节点上移
			adjustIndex = i;		// 下移调整指针，进行下一轮筛选
		}
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * @description 从下往上建堆：从array[array.length / 1]逐步向上（或者说向前）进行堆调整从而达到建堆
	 */
	public void heapBuild(int[] array) {
		for(int i = array.length >> 1; i >= 0; i--) {
			heapAdjustByRecursion(array, i, array.length - 1);
			// heapAdjustByIteration(array, i, array.length - 1);
		}
	}
	
	/**
	 * 
	 * @param array 排序数组
	 * 1.由输入的无序数组构造一个最大堆，作为初始的无序区
	 * 2.把堆顶元素（最大值）和堆尾元素互换
	 * 3.把堆（无序区）的尺寸缩小1，并调用heapAdjustByRecursion(A, 0)从新的堆顶元素开始进行堆调整
     * 4.重复步骤2，直到堆的尺寸为1
	 */
	public void heapSort(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return;
		}
		
		// 建堆
		heapBuild(array);
		
		for(int i = array.length - 1; i >= 0; i--) {
			swap(array, 0, i);				// 把堆顶（最大）元素放到堆后
			heapAdjustByRecursion(array, 0, i - 1);	// 堆调整
			// heapAdjustByIteration(array, 0, i - 1);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 6, 5, 3, 1, 8, 7, 2, 4, 10, 6 };    // 从小到大插入排序
		HeapSort heapSort = new HeapSort();
		heapSort.heapSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
