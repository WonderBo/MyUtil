package com.cqu.wb.minpath;

import java.util.ArrayList;
import java.util.Scanner;

public class MinPath {
	//字符串转换为数组形式，保存邻接矩阵信息
	//输出表示邻接矩阵的数组
	public ArrayList<ArrayList<Integer>> StrToDouArr(){
		System.out.println("请输入所求最短路径图的邻接矩阵，以& 表示两顶点不相邻，以 # 为结束： ");
		ArrayList<ArrayList<Integer>> arrL = new ArrayList<ArrayList<Integer>>();	
		for(;;){
			ArrayList<Integer> arrS = new ArrayList<Integer>();
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			if(!str.equals("#")){
				String[] strArr = str.split("\\s+");	//以单个或多个空格分割
				for(int i = 0; i<strArr.length; i++){
					if(!strArr[i].equals("&")){
						arrS.add(Integer.parseInt(strArr[i]));
					}else{
						arrS.add(1000);	
					}
				}
				arrL.add(arrS);
			}else{
				break;
			}
		}
		return arrL;
	}

	//Dijkstra算法求某个顶点到其余各点最短路径
	//输入邻接矩阵数组和出发点
	public void dijAlg(ArrayList<ArrayList<Integer>> arrL , int startV){
		int numV = arrL.size();
		ArrayList<Integer> minPathArr = new ArrayList<Integer>();	//路径长度数组
		ArrayList<String> routeArr = new ArrayList<String>();		//路径数组
		//初始化
		for(int i=0; i<numV; i++){
			routeArr.add(startV + " To " + i);
			minPathArr.add(0);
		}
		boolean[] temp = new boolean[numV];
		for(int i=0; i<numV; i++){
			temp[i] = false;
		}
		//确定起点
		minPathArr.set(startV, 0);
		temp[startV] = true;
		//确定其它v-1个顶点
		for(int i=0; i<numV-1; i++){
			//确定最小邻接顶点
			int nearV = -1;
			int minP = Integer.MAX_VALUE;
			for(int j=0; j<numV; j++){
				if(arrL.get(startV).get(j) < minP && temp[j] == false){
					minP = arrL.get(startV).get(j);
					nearV = j;
				}
			}
			minPathArr.set(nearV, minP);
			temp[nearV] = true;
			//经过邻接顶点的间接距离与原直接距离取较小值
			for(int j=0; j<numV; j++){
				int indirectP = arrL.get(startV).get(nearV) + arrL.get(nearV).get(j);
				if(indirectP < arrL.get(startV).get(j) && temp[j] == false){
					arrL.get(startV).set(j, indirectP);
					routeArr.set(j, routeArr.get(nearV) + " To " + j);
				}
			}
		}
		//输出最短距离路径列表
		System.out.println();
		System.out.println("--最短距离路径列表（Dijkstra算法）--");
		System.out.println("起始顶点      结束顶点      最短距离      最短距离路径");
		for(int i=0; i<numV; i++){
			System.out.println(startV +"         "+  i +"         "+ minPathArr.get(i)+"         "+ routeArr.get(i) );
		}
		System.out.println();
	}

	//Floyd算法求所有顶点到其余各点最短路径
	//输入邻接矩阵数组
	public void floAlg(ArrayList<ArrayList<Integer>> arrL){
		int length = arrL.size();
		int[][] pathArr = new int[length][length];	//后继点矩阵数组

		ArrayList<ArrayList<String>> pathRoute = new ArrayList<ArrayList<String>>();	//最短距离线路数组
		for(int i=0; i<length; i++){
			ArrayList<String> arrTemp = new ArrayList<String>();
			for(int j=0; j<length; j++){
				arrTemp.add(i + " To ");
			}
			pathRoute.add(arrTemp);
		}
		//初始化后继点矩阵
		for (int i = 0; i < length; i++) {     
			for (int j = 0; j < length; j++) {      
				pathArr[i][j] = j;      
			}       
		}
		//经过中间顶点的间接距离与原直接距离取较小值，同时更新后继点矩阵
		for(int k = 0 ; k < length ; k++){           
			for (int i = 0; i < length; i++) {      
				for (int j = 0; j < length; j++) {       
					if((arrL.get(i).get(j) > arrL.get(i).get(k) + arrL.get(k).get(j))){          
						pathArr[i][j] = k;       
						arrL.get(i).set(j , arrL.get(i).get(k) + arrL.get(k).get(j));                
					} 
				}         
			}           
		}
		//输出最短距离路径列表
		System.out.println();
		System.out.println("--最短距离路径列表（Floyd算法）--");
		System.out.println("起始顶点      结束顶点      最短距离      最短距离路径");
		for(int i = 0; i < length; i++){
			for(int j = 0; j < length; j++){
				//输出最短路径
				while(pathArr[i][j] != j){
					int temp = pathArr[i][j];
					pathRoute.get(i).set(j, pathRoute.get(i).get(j) + temp + " To " );
					pathArr[i][j] = pathArr[temp][j];
				}
				pathRoute.get(i).set(j, pathRoute.get(i).get(j) + j);
				if(arrL.get(i).get(j) < 1000){
					System.out.println(i +"         "+  j +"         "+ arrL.get(i).get(j)+"         "+ pathRoute.get(i).get(j));
				}
				else{
					System.out.println(i +"         "+  j +"         "+ "无"+"         "+"无");
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//测试程序
		MinPath mp = new MinPath();
		ArrayList<ArrayList<Integer>> inArr = mp.StrToDouArr();
		System.out.println("请选择最短路径算法：   1：Dijkstra算法；    2：Floyd算法");
		Scanner scan_2 = new Scanner(System.in);
		String num = scan_2.next();
		if(num.equals("1")){
			System.out.print("请输入起始顶点序号为：   ");
			int startV = scan_2.nextInt();

			mp.dijAlg(inArr, startV);
		}else if(num.equals("2")){
			mp.floAlg(inArr);
		}else{
			System.out.println("输入有误！");
		}
	}
}