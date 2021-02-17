package com.ssafy.edu.hw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_JO_1828 {
	
	static int N;
	static int[][] chem;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		chem = new int[N][2];
		
		for(int i=0; i<N; i++) {
			chem[i][0] = sc.nextInt();
			chem[i][1] = sc.nextInt();
		}
		
		// 낮은 온도를 기준으로 오름차순 정렬
		Arrays.sort(chem, new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b) {
				int n = a[0] - b[0];
				if(n == 0)
					n = a[1] - b[1];
				return n;
			}
		});
		
		int ans = 1;
		int maxRange = chem[0][1];
		
		for(int i=1; i<chem.length; i++) {
			int x = chem[i][0];
			int y = chem[i][1];
			
            if(maxRange < x) {
            	ans += 1;
            	maxRange = y;
            }else
            	maxRange = Math.min(maxRange, y);
		}
		
		System.out.println(ans);
	}
}