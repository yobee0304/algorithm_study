# 다리 만들기 2

## 1. 문제
https://www.acmicpc.net/problem/17472

## 2. 유형
* BFS/DFS
* Union-Find
* Kruskal Algorithm

## 3. 풀이
* 이 문제를 푸는 과정을 크게 3단계로 나눌 수 있다.

1) 각각의 섬에 대한 지역에 넘버링으로 섬들을 구분하기
~~~java
	private static void IslandGrouping() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int cnt = 1;
		
		for(int i=0; i<island.size(); i++) {
			Point p = island.get(i);
			if(visited[p.y][p.x]) continue;
			
			List<Point> list = new ArrayList<>();
			q.add(p);
			while(!q.isEmpty()) {
				Point top = q.poll();
				list.add(new Point(top.y, top.x));
				visited[top.y][top.x] = true;
				map[top.y][top.x] = cnt; 
				
				for(int j=0; j<4; j++) {
					if(isCheck(top.y + dirY[j], top.x + dirX[j]) && map[top.y + dirY[j]][top.x + dirX[j]] == 1 && !visited[top.y + dirY[j]][top.x + dirX[j]])
						q.add(new Point(top.y + dirY[j], top.x + dirX[j]));
				}
			}
			cnt++;
			islGroup.add(list);
		}
	}
~~~
* 각각의 섬을 구분하기 위해서 BFS 알고리즘을 활용했다.
* 섬을 각각 넘버링 해주는 이유는 2단계에서 섬을 잇는 다리를 구할 때, 서로 다른 숫자가 만나는 것을 기준으로 하기 때문이다.

2) 각각의 섬들을 잇는 다리를 모두 구하기
~~~java
	private static void isBridge(Point point, int p) {	
		for(int i=0; i<4; i++) {
			int length = 0;
			int y = point.y;
			int x = point.x;
			
			while(true) {
				if(!isCheck(y + dirY[i], x + dirX[i]))
					break;
					
				if(map[y + dirY[i]][x + dirX[i]] > 0) {
					if(length > 1)
						pq.add(new Edge(p, map[y + dirY[i]][x + dirX[i]], length));
					break;
				}
				
				y += dirY[i];
				x += dirX[i];
				length++;
			}
		}
	}
~~~
* 두 섬을 직선으로 잇는 다리를 모두 구한다.
* 4방향(상, 하, 좌, 우)을 확인해서 자신과 다른 섬을 만날 때까지 반복하며 확인하는 식으로 다리를 놓을 수 있는지 판단한다.
* 이렇게 구한 다리는 크루스칼 알고리즘에서 사용하기 위해 우선순위큐(Priority Queue)에 넣어둔다.

3) 크루스칼 알고리즘으로 다리를 이용해 섬 연결하기
~~~java
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int p1 = find(e.p1);
			int p2 = find(e.p2);
      // 이미 같은 그룹이면 continue
			if(p1 == p2) continue;
      // 섬을 연결
			union(p1, p2);
			v_cnt++;
			ans += e.w;
			
      // 모든 섬이 연결되면 반복문 종료
			if(v_cnt == islGroup.size()-1)
				break;
		}
    
    private static void union(int p1, int p2) {
      p1 = find(p1);
      p2 = find(p2);

      if(p1 < p2)
        parent[p2] = p1;
      else
        parent[p1] = p2;
    }

    private static int find(int p1) {
      if(parent[p1] == p1)
        return p1;
      else
        return parent[p1] = find(parent[p1]);
    }
~~~
* 2단계에서 구한 다리들을 크루스칼 알고리즘을 통해 선택하여 섬들을 연결한다.
* 길이가 짧은 다리를 우선적으로 선택하며, 이미 연결된 섬들을 다시 연결하지는 않는다.

## 4. 후기
최근에 크루스칼 알고리즘과 DFS/BFS 탐색 알고리즘을 주로 공부해 왔는데, 이 둘을 한 번에 적용해 볼 수 있는 재미있는 문제였다.

처음에는 굉장히 복잡해 보여 접근하기 힘들었지만, 단계별로 나누어 구현할 것을 대략적으로 스케치한 후 구현을 하는 것이 큰 도움이 되었다.

무작정 구현하기 보다는, 이렇게 제대로된 설계도를 가지고 문제를 푸는 습관을 들여야겠다.
