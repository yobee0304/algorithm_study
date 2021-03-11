package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_4012 {
	
	static int T, N;
	static int ans;
	static int[] C;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			C = new int[N/2];
			ans = Integer.MAX_VALUE;
			
			// input synergy
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			comb(0, 0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void comb(int cnt, int next) {
		if(cnt == N/2) {
			boolean[] check = new boolean[N];
			int[] another = new int[N/2];
			for(int i=0; i<N/2; i++) {
				check[C[i]] = true;
			}
			
			// comb로 뽑은 숫자가 아닌 나머지 숫자를 another 배열에 넣기
			int idx=0;
			for(int i=0; i<N; i++) {
				if(!check[i])
					another[idx++] = i;
			}
			
			int A = 0;
			int B = 0;
			// A음식 값 구하기
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<N/2; j++) {
					if(i == j) continue;
					A += map[C[i]][C[j]];
				}
			}
			// B음식 값 구하기
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<N/2; j++) {
					if(i == j) continue;
					B += map[another[i]][another[j]];
				}
			}
			
			ans = Integer.min(ans, Math.abs(A-B));
			
			return;
		}
		
		for(int i=next; i<N; i++) {
			C[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
}
