# 최소 스패닝 트리

## 문제
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb&categoryId=AV_mSnmKUckDFAWb&categoryType=CODE&problemTitle=3124&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&

## 유형
* Kruskal's Algorithm
* Union-Find

## 풀이
* 주어진 간선 정보를 가지고 모든 정점을 이었을 때, 가중치가 최소가 되도록 선택해야 한다.
* 먼저 가중치가 작은 간선을 우선적으로 선택할 수 있도록 우선순위큐(Priority Queue)를 사용한다.
* 간선을 선택하고 이어진 정점들의 상태는 union-find를 통해 확인한다.
~~~java
	private static void union(int start, int end) {
		start = find(start);
		end = find(end);
		
		if(start < end)
			parent[end] = start;
		else
			parent[start] = end;
	}
	
	private static int find(int num) {
		if(num == parent[num])
			return num;
		else
			return parent[num] = find(parent[num]);
	}
~~~
* 간선을 선택했을 때, 두 정점이 서로 다른 그룹에 있다면 두 정점은 연결한다.
~~~java
	        	if(!checkSameGroup(e.getStart(), e.getEnd())) {
	        		union(e.getStart(), e.getEnd());
	        		w_sum += e.getWeight();
	        		v_cnt++;
	        	}
~~~
* 모든 정점이 간선으로 연결되면, 간선 선택을 멈추고 답을 출력한다.
~~~java
        		if(v_cnt == V-1) {
        			break;
        		}
~~~

## 후기
우선순위큐를 사용할 때, 우선순위의 기준을 compareTo로 적절하게 선정해주는 것이 중요했다.

그리고, 항상 입출력의 범위값에 주의해야한다.

이번 문제도 출력값의 범위를 제대로 파악하지 못해서 int로 설정해서 시간이 오래 걸렸다.

작은 실수지만, 실전에서는 많은 시간을 허비할 수 있음을 항상 경계하자!
