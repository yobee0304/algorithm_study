package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_3124 {
	
	static int T, V, E;
	static int[] parent;
	
	static class edge implements Comparable<edge>{
		private int start;
		private int end;
		private int weight;
			
		public edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(edge o) {
			return this.weight > o.weight ? 1 : -1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		PriorityQueue<edge> pq = new PriorityQueue<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String str = br.readLine();
	        StringTokenizer st = new StringTokenizer(str);
	        V = Integer.parseInt(st.nextToken());
	        E = Integer.parseInt(st.nextToken());
	        
	        parent = new int[V+1];
	        for(int i=0; i<V+1; i++)
	        	parent[i] = i;
	        
	        int w_sum = 0;
	        int v_cnt = 0;
	        
	        for(int i=0; i<E; i++) {
	        	int A, B, C;
	        	str = br.readLine();
	            st = new StringTokenizer(str);
	            A = Integer.parseInt(st.nextToken());
	            B = Integer.parseInt(st.nextToken());
	            C = Integer.parseInt(st.nextToken());
	            
	            pq.add(new edge(A, B, C));
	        }
	        
	        while(!pq.isEmpty()) {
	        	edge e = pq.poll();
	        	System.out.println(e);
	        	if(!checkSameGroup(e.getStart(), e.getEnd())) {
	        		union(e.getStart(), e.getEnd());
	        		w_sum += e.getWeight();
	        		v_cnt++;
	        	}
	        	
        		if(v_cnt == V-1) {
        			break;
        		}
	        }
	        
	        System.out.println("#" + t + " " + w_sum);
	        pq.clear();
		}
	}

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

	private static boolean checkSameGroup(int start, int end) {
		if(find(start) == find(end))
			return true;
		else
			return false;
	}
}
