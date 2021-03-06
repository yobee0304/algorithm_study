package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D4_3234 {
	
	static int T, N, ans;
	static int[] w, c;
	static boolean[] v;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			w = new int[N];
			c = new int[N];
			v = new boolean[N];
			ans = 0;
			
			for(int i=0; i<N; i++) {
				w[i] = sc.nextInt();
			}
			
			perm(0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void perm(int cnt) {
		// permutation이 끝낫다면
	    // dfs를 통해 양팔 저울에 추를 놓기
		if(cnt == N) {
//			System.out.println(Arrays.toString(c));
			dfs(0, 0, 0);
			
			return;
		}
		
		// boolean 배열 v를 통해 이미 선택한 추는 다시 선택하지 않는다.
		for(int i=0; i<N; i++) {
			if(v[i]) continue;
			v[i] = true;
			 // 선택한 추를 c배열에 넣기
			c[cnt] = w[i];
			perm(cnt+1);
			v[i] = false;
		}
	}

	private static void dfs(int cnt, int left, int right) {
		// 모든 추를 놓았을 
		if(cnt == N) {
			if(left >= right) {
//				System.out.println(left + " " + right);
				ans++;
			}
			return;
		}
		
		// 오른쪽이 큰 경우는 있을 수 없다
		if(left < right)
			return;
		
		// 추를 왼쪽에 놓는 경우
		dfs(cnt+1, left+c[cnt], right);
		// 추를 오른쪽에 놓는 경우
		dfs(cnt+1, left, right+c[cnt]);
	}
}
