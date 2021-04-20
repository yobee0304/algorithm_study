# [Professional] 조합

## 1. 문제
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo&categoryId=AWXGKdbqczEDFAUo&categoryType=CODE&problemTitle=5607&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

## 2. 유형
* Dynamic Programming
* Divide & Conquer
* **Fermat little theorem**

## 3. 풀이
* 서론
  * nCr = n! / r! X (n-r)! 으로 계산할 수 있다.
  * 분자를 분모로 나누는 계산 과정에서 시간적으로 큰 코스트가 필요하므로, mod를 활용한 계산에서는 **페르마의 소정리**를 활용하여 코스트를 줄일 수 있다.
  * 페르마의 소정리란?
  <img width="214" alt="스크린샷 2021-04-20 오후 1 58 21" src="https://user-images.githubusercontent.com/52665200/115339814-7a356d00-a1e0-11eb-857b-2f7c12eb3b31.png">

  * 위와 같은 식을 활용하여, 나눗셈을 곱셈으로 치환할 수 있다.

* 풀이
  * 팩토리얼 계산을 위해 DP를 활용한 팩토리얼 배열을 준비한다.
  ~~~java
  		long[] fact = new long[N+1];
			fact[0] = 1;
			for(int i=1; i<=N; i++)
				fact[i] = fact[i-1] * i % mod;
  ~~~
  * 분모 a를 곱셈식으로 바꾸기 위해서는 a^(mod-2)의 값이 필요하다.
  * a^(mod-2)값은 분할 정복을 활용해 빠르게 구한다.
  ~~~java
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
  ~~~

## 4. 후기
dp, 분할 정복등 다양한 알고리즘이 사용되었지만, 이 문제를 풀기 위한 가장 중요한 키는 **페르마의 소정리**였다. 

숫자가 커짐에 따라 나눗셈 연산의 속도가 현저히 느려지는 만큼, 이를 극복하기 위해 꼭 사용해야 했다.

컴퓨터에 연산을 직접적으로 맡기는 경우가 대다수이지만, 이러한 경우는 계산식을 직접 최소화 시켜 적용해야 제한시간 안에 들어올 수 있다.

이러한 기본적인 수식은 알아두면 문제를 푸는데 있어서 큰 도움이 될 것 같다.
