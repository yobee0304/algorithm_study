import java.util.Scanner;

public class Solution_D3_4615 {
	
	static int T, N, M;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			
			map[N/2-1][N/2-1] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
			map[N/2][N/2] = 2;
			
			// 1 : 흑돌 / 2 : 백돌
			for(int i=0; i<M; i++) {
				int x = sc.nextInt()-1;
				int y = sc.nextInt()-1;
				map[y][x] = sc.nextInt();
				change(y, x);
			}
			
			int black = 0;
			int white = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 1)
						black++;
					else if(map[i][j] == 2)
						white++;
				}
			}
			
			System.out.println("#" + t + " " + black + " " + white);
		}
	}

	static int[] dirY = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dirX = {-1, 0, 1, 1, 1, 0, -1, -1};

	private static void change(int y, int x) {
		// 8방향 확인
		for(int i=0; i<8; i++) {
			int dol = map[y][x];
			int dY = dirY[i];
			int dX = dirX[i];
			int endY = y;
			int endX = x;
			
			while(0 <= endY+dY && endY+dY < N && 0 <= endX+dX && endX+dX < N && map[endY+dY][endX+dX] != 0) {
				endY += dY;
				endX += dX;
				
				if(map[endY][endX] == map[y][x])
					break;
			}
			
			if(map[endY][endX] != dol)
				continue;
			
			int nowY = y + dY;
			int nowX = x + dX;
			
			while(0 <= nowY && nowY < N && 0 <= nowX && nowX < N && map[nowY][nowX] != dol && map[nowY][nowX] != 0) {
				map[nowY][nowX] = dol;
				nowY += dY;
				nowX += dX;
			}
		}
	}
}
