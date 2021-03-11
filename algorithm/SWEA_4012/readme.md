# 요리사

## 1. 문제
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH

## 2. 유형
* 완전 탐색

## 3. 풀이
* 먼저 음식 두 가지를 만들기 위해 재료를 A에 들어갈 재료와 B에 들어갈 재료로 나눈다.
  * 이 때, **조합**을 사용해서 재료를 나눈다.
~~~java
private static void comb(int cnt, int next) {
		for(int i=next; i<N; i++) {
			C[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
~~~
  * A는 N Combiantion N/2로 먼저 구한다.

* A에 들어갈 재료를 모두 구하면, 나머지 재료를 통해 B에 들어갈 재료를 구한다.
~~~java
boolean[] check = new boolean[N];
int[] another = new int[N/2];
// A에 들어간 재료는 check에 true로 표시하기
for(int i=0; i<N/2; i++) {
    check[C[i]] = true;
}
			
// comb로 뽑은 숫자가 아닌 나머지 숫자를 another 배열에 넣기
int idx=0;
for(int i=0; i<N; i++) {
    if(!check[i])
      another[idx++] = i;
}
~~~

* 재료를 나눴다면, 입력받은 이차원 배열의 값을 통해 A,B음식의 시너지 값을 구한다.
  * 예시) [0, 1, 3]으로 음식을 만들 경우, A음식 = M[0][1] + M[0][3] + M[1][0] + M[1][3] + M[3][0] + M[3][1]로 구하면 된다.

* 구한 A와 B값들 중, 가장 작은 값을 출력한다.

## 4. 후기

N개의 재료를 2개의 그룹으로 나누는 점이 가장 중요한 포인트였다.

그리고 모든 경우의 수를 확인해야 했기 때문에, 조합을 사용해서 완전 탐색을 진행할 수 있다는 점만 유의한다면 쉽게 풀 수 있는 문제라고 생각한다.
