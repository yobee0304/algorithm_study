package swea;

import java.util.Arrays;
import java.util.Scanner;

// 페르마의 소정리
public class Solution_D3_5607 {
	
	static int T, N, R;
	static final long mod = 1234567891;
	static long ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			R = sc.nextInt();
			
			long[] fact = new long[N+1];
			fact[0] = 1;
			for(int i=1; i<=N; i++)
				fact[i] = fact[i-1] * i % mod;
			
			long bot = fact[R] * fact[N-R] % mod;
			long reBot = pow(bot, mod - 2);
			ans = fact[N] * reBot % mod;
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static long pow(long n, long upper) {
		long tmp;
		// 지수가 0
		if(upper == 0)	return 1;
		// 지수가 1
		if(upper == 1)	return n;
		
		if(upper % 2 == 0) {
			tmp = pow(n, upper/2) % mod;
			return tmp * tmp % mod;
		}else {
			tmp = pow(n, upper-1) % mod;
			return n * tmp % mod;
		}
	}
}
