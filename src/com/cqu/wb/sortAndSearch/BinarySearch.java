/**
 * @description 二分查找，三分查找，二分法部分其他用法
 */
package com.cqu.wb.sortAndSearch;

public class BinarySearch {

	/**
	 * 
	 * @param array 已排序数组（无重复数字）
	 * @param x 查找数字
	 * @return 查找数字下标或者-1
	 * @description 循环实现二分法【将数组划分为三部分】在已排序数组（无重复数字）中查找数字
	 */
	public int binarySearchByIteration(int[] array, int x) {
		// 输入验证
		if(array == null || array.length == 0) {
			return -1;
		}
		
		int leftIndex = 0;	// 数组左下标
		int rightIndex = array.length;	// 数组右下标
		int middle = 0;	// 数组中间下标
		// 循环直到右下标移动到左下标的左边（需要保留‘=’，不然后一个数字[左右下标重合时]无法比较）
		while(leftIndex <= rightIndex) {
			middle = (leftIndex + rightIndex) >> 1;
		
			if(array[middle] == x) {
				return middle;
			} else if(array[middle] > x) {
				rightIndex = middle - 1;	// 注意对middle +/- 1，否则最后一步将陷入死循环
			} else {
				leftIndex = middle + 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @param array 已排序数组（无重复数字）
	 * @param x 查找数字
	 * @return 查找数字下标或者-1
	 * @description 递归实现二分法【将数组划分为三部分】在已排序数组（无重复数字）中查找数字
	 */
	public int binarySearchByRecursion(int[] array, int x) {
		// 输入验证
		if(array == null || array.length == 0) {
			return -1;
		}
		
		return binarySearchByRecursionCore(array, 0, array.length - 1, x);
	}
	
	public int binarySearchByRecursionCore(int[] array, int left, int right, int x) {
		// 递归结束条件1（不存在）
		if(left > right) {
			return -1;
		}
		
		int middle = (right + left) >> 1;
		if(array[middle] == x) {
			return middle;	// 递归结束条件2（存在且已找到）
		} else if(array[middle] > x) {
			return binarySearchByRecursionCore(array, left, middle - 1, x);		// 注意对middle +/- 1，否则最后一步将陷入死循环
		} else {
			return binarySearchByRecursionCore(array, middle + 1, right, x);
		}
	}
	
	/**
	 * 
	 * @param array 已排序数组（有重复数字）
	 * @param x 查找数字
	 * @return 查找数字第一次出现下标或者-1
	 * @description 循环实现二分法【将数组划分为两部分】在已排序数组（有重复数字）中查找数字
	 */
	public int binarySearchForFirstTime(int[] array, int x) {
		// 输入验证
		if(array == null || array.length == 0) {
			return -1;
		}
		
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		int middleIndex = 0;
		// 循环直到右下标与左下标重合（重合元素若为x，则为第一次出现的位置）
		while(leftIndex < rightIndex) {
			middleIndex = (leftIndex + rightIndex) >> 1;
			if(array[middleIndex] >= x) {
				rightIndex = middleIndex;	// 注意‘=’归纳在左部分子数组
			} else {
				leftIndex = middleIndex + 1;	// 注意对middle +/- 1，否则最后一步将陷入死循环
			}
		}
		
		if(array[leftIndex] != x) {
			return -1;
		} else {
			return leftIndex;
		}
	}
	
	/**
	 * 
	 * @param array 已排序数组（有重复数字）
	 * @param x 查找数字
	 * @return 查找数字最后次出现下标或者-1
	 * @description 循环实现二分法【将数组划分为两部分】在已排序数组（有重复数字）中查找数字
	 */
	public int binarySearchForLastTime(int[] array, int x) {
		// 输入验证
		if(array == null || array.length == 0) {
			return -1;
		}
		
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		int middleIndex = 0;
		// 循环直到右下标与左下标重合（重合元素若为x，则为最后次出现的位置）
		while(leftIndex < rightIndex) {
			middleIndex = (leftIndex + rightIndex) >> 1;
			if(array[middleIndex] <= x) {	// 注意‘=’归纳在右部分子数组
				leftIndex = middleIndex;
			} else {
				rightIndex = middleIndex - 1;	// 注意对middle +/- 1，否则最后一步将陷入死循环
			}
		}
		
		if(array[rightIndex] != x) {
			return -1;
		} else {
			return rightIndex;
		}
	}
	
	/**
	 * 
	 * @param array 半有序数组
	 * @return 极点值
	 * @description 三分法求极点，但是要注意最后范围剩下2或3个数字的时候
	 */
	public int tripleSearchForPole(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return -1;
		}
		
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		int midIndex = 0;
		int midmidIndex = 0;
		
		// 循环直到右下标与左下标重合，重合点即为极点
		while(leftIndex < rightIndex) {
			midIndex = (leftIndex + rightIndex) >> 1;
			midmidIndex = (midIndex + rightIndex) >> 1;
			if(array[midIndex] > array[midmidIndex]) {			// 当array[midIndex] > array[midmidIndex]，缩小右边范围
				rightIndex = midmidIndex - 1;
			} else if(array[midIndex] < array[midmidIndex]) {	// 当array[midIndex] < array[midmidIndex]，缩小左边范围
				leftIndex = midIndex + 1;
			} else {					// 当array[midIndex] = array[midmidIndex]这时范围可能剩下2或3个数或多个，分递增，递减，极点三种情况缩小范围
				if(array[leftIndex] > array[rightIndex]) {
					rightIndex --;
				} else {
					leftIndex ++;
				}
			}
		}
		
		return array[leftIndex];
	}
	
	/**
	 * 
	 * @param m 乘数
	 * @param n 被乘数
	 * @return 积
	 * @description 快乘法：位运算+二分法循环实现以加法计算乘法(从底向上累加)时间复杂度O(log n)
	 */
	public int multiplictionByAdd(int m, int n) {
		int result = 0;
		while(n > 0) {
			if((n & 1) == 1) {
				result += m;
			}
			
			m += m;
			n = n >> 1;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param base 底数
	 * @param index 指数
	 * @return 乘方
	 * @description 快乘方：位运算+二分法循环实现以乘法计算乘方(从底向上累乘)时间复杂度O(log index)
	 */
	public int powerByMultipliction(int base, int index) {
		int result = 1;
		while(index > 0) {
			if((index & 1) == 1) {
				result *= base;
			}
			base *= base;
			index = index >> 1;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param p 底
	 * @param n 最大指数
	 * @return 和
	 * @description 递归二分法实现等比数列求和(1 + p^1 + p^2 + … + p^n)
	 */
	public int sumOfGeometricSequence(int p, int n) {
		if(n == 0) {
			return 1;
		}
		
		if((n & 1) == 1) {	// 等比数列元素为偶数个
			return sumOfGeometricSequence(p, n >> 1) * (1 + powerByMultipliction(p, (n >> 1) + 1));
		} else {
			return sumOfGeometricSequence(p, (n >> 1) -1) * (1 + powerByMultipliction(p, (n >> 1) + 1)) + powerByMultipliction(p, n >> 1);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 1, 2, 3, 5, 7, 8, 9, 14 };
		BinarySearch binarySearch = new BinarySearch();
		System.out.println("二分法在无重复有序数组中查询数字：");
		System.out.println(binarySearch.binarySearchByIteration(array, 7));
		System.out.println(binarySearch.binarySearchByRecursion(array, 7));
		
		int[] array2 = { 1, 2, 3, 7, 7, 7, 9, 14 };
		System.out.println("二分法在有重复有序数组中查询数字：");
		System.out.println(binarySearch.binarySearchForFirstTime(array2, 7));
		System.out.println(binarySearch.binarySearchForLastTime(array2, 7));
		
		int[] array3 = { -2, -1, 1, 2, 7, 8, 8, 7, 5, 1, -2 };
		System.out.println("三分法在半有序数组中查询极点数字：");
		System.out.println(binarySearch.tripleSearchForPole(array3));
		
		System.out.println("二分法部分其他用法：");
		System.out.println(binarySearch.multiplictionByAdd(10, 5));
		System.out.println(binarySearch.powerByMultipliction(2, 10));
		System.out.println(binarySearch.sumOfGeometricSequence(2, 3));
	}

}
