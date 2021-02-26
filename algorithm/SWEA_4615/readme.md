# 재미있는 오셀로 게임

## 1. 문제
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWQmA4uK8ygDFAXj&categoryId=AWQmA4uK8ygDFAXj&categoryType=CODE&problemTitle=4615&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1


## 2. 유형
* 완전 탐색

## 3. 풀이
* N by N 크기의 바둑판을 생성한 뒤, 처음부터 존재하는 바둑돌을 우선 둡니다.
~~~Java
			map[N/2-1][N/2-1] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
			map[N/2][N/2] = 2;
~~~

* 바둑돌을 둘 때마다, 8방향을 확인하며 바꿀 수 있는 바둑돌들을 모두 변경합니다.
~~~Java
private static void change(int y, int x) {
		// 8방향 확인
		for(int i=0; i<8; i++) {
			int dol = map[y][x];
			int dY = dirY[i];
			int dX = dirX[i];
			int endY = y;
			int endX = x;
			
                       // 자신과 똑같은 바둑돌이 나올 때 까지 반복
			while(0 <= endY+dY && endY+dY < N && 0 <= endX+dX && endX+dX < N && map[endY+dY][endX+dX] != 0) {
				endY += dY;
				endX += dX;
				
				if(map[endY][endX] == map[y][x])
					break;
			}
			
                       // 만약 마지막 돌이 같은 돌이 아니면 이번 방향은 Pass
			if(map[endY][endX] != dol)
				continue;
			
			int nowY = y + dY;
			int nowX = x + dX;
			
                       // 같은 돌이 나올때 까지 다른 돌들을 반대 색깔로 변경
			while(0 <= nowY && nowY < N && 0 <= nowX && nowX < N && map[nowY][nowX] != dol && map[nowY][nowX] != 0) {
				map[nowY][nowX] = dol;
				nowY += dY;
				nowX += dX;
			}
		}
	}
~~~

## 4. 후기
완전 탐색으로 8방향을 모두 확인하되, 탐색을 멈추는 조건 및 멈춘 곳 까지 돌을 반대로 변경하는 로직이 중요한 문제였습니다.

여기서 탐색을 멈추는 조건 설정을 잘못하여 일부 테스트 케이스가 통과되지 못했습니다.

무슨 문제를 풀던 기저 조건을 확실하게 해야할 것 같습니다!
