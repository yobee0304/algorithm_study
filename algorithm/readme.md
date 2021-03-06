# 1. 문제
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw&categoryId=AWAe7XSKfUUDFAUw&categoryType=CODE&problemTitle=3234&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

# 2. 유형
* dfs
* 완전 탐색(Brute Force)

# 3. 풀이
* 무게가 1,2,4인 추들이 있다면 추를 놓을 수 있는 순서의 경우의 수는 다음과 같다.
  * [1, 2, 4]
  * [1, 4, 2]
  * [2, 1, 4]
  * [2, 4, 1]
  * [4, 1, 2]
  * [4, 2, 1]
  * 이와 같은 순서는 **순열(Permutation)** 을 통해 구할 수 있다.
~~~ Java
private static void perm(int cnt) {
	    // permutation이 끝낫다면
	    // dfs를 통해 양팔 저울에 추를 놓기
		if(cnt == N) {
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
~~~

* 구한 추의 순서를 **DFS** 를 사용해서 순서대로 놓는다.
  * 이 때, 왼쪽이 항상 오른쪽보다 크거나 같아야한다.
~~~Java
private static void dfs(int cnt, int left, int right) {
		// 모든 추를 놓았을 
		if(cnt == N) {
			if(left >= right) {
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
~~~

# 4. 후기

N의 범위가 1이상 9이하로 비교적 작았기 때문에 순열로 쉽게 경우의 수를 구할 수 있었던 문제다.

또한, 추를 오른쪽에 놓을 때와 왼쪽에 놓을 때를 모두 확인해야 했다.

이 때, 오른쪽은 왼쪽보다 무게가 무거워질 수 없다는 조건을 추를 놓을 때마다 확인해야 한다는 점을 주의해야 한다.

보통 이러한 기저조건은 알고리즘의 속도에만 영향을 주지만, 이번 문제는 문제의 결과에도 직접적인 영향을 주었기 때문이다.
