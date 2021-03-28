package algo0326;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_17472 {
	
	static int N, M;
	static int[][] map;
	static List<Point> island = new ArrayList<>();
	static List<List<Point>> islGroup = new ArrayList<>();
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent;
	
	static int[] dirY = {0, 0, 1, -1};
	static int[] dirX = {1, -1, 0, 0};
	
	static class Point{
		int x;
		int y;
		
		public Point(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int p1;
		int p2;
		int w;
		
		public Edge(int p1, int p2, int w) {
			this.p1 = p1;
			this.p2 = p2;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w > o.w ? 1 : -1;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1)
					island.add(new Point(i, j));
			}
		}
		
		// 그룹 나누기
		IslandGrouping();
		parent = new int[islGroup.size()+1];
		for(int i=0; i<parent.length; i++)
			parent[i] = i;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		// 각 그룸에서 가능한 다리들 확인하기
		for(int i=0; i<islGroup.size(); i++) {
			List<Point> list = islGroup.get(i);
			
			for(int j=0; j<list.size(); j++) {
				isBridge(list.get(j), i+1);
			}
		}
		
		// 주어진 다리들로 섬 연결하기
		int ans = 0;
		int v_cnt = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int p1 = find(e.p1);
			int p2 = find(e.p2);
			if(p1 == p2) continue;
			System.out.println(e.p1 + ", " + e.p2 + ": " + e.w);
			union(p1, p2);
			v_cnt++;
			ans += e.w;
			
			if(v_cnt == islGroup.size()-1)
				break;
		}
		
		System.out.println(v_cnt == islGroup.size()-1 ? ans : -1);
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

	private static boolean isCheck(int y, int x) {
		if(0 <= y && y < N && 0<= x && x < M)
			return true;
		return false;
	}
}
