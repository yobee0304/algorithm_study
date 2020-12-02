from queue import Queue

def solution(n, edges):
    graph = [[] for i in range(n+1)]
    for i in range(len(edges)):
        n1, n2 = edges[i]
        graph[n1].append(n2)
        graph[n2].append(n1)

    print(graph)
    q = Queue()
    # 지름 찾기 1 : 지름의 한 끝점 찾기
    q.put(1)
    rad, endPoint = bfs(graph, q, n)
    print(rad, endPoint)

    # 2 : 진짜 지름 찾기
    q = Queue()
    q.put(endPoint[0])
    rad, endPoint = bfs(graph, q, n)
    print(rad, endPoint)

    if len(endPoint) > 1:
        return rad

    # 3
    q = Queue()
    q.put(endPoint[0])
    rad, endPoint = bfs(graph, q, n)
    print(rad, endPoint)

    if len(endPoint) > 1:
        return rad
    else:
        return rad - 1

def bfs(graph, q, n):
    rad, endpoint = 0, []
    # visit를 배열로 만들어서 처리하니까 속도가 빨라짐
    # 기존에 빈배열에 추가하는식으로 하는거보다!
    visit = [False for i in range(n+1)]
    while True:
        L = q.qsize()
        for i in range(L):
            top = q.get()
            visit[top] = True
            candidates = graph[top]
            for node in candidates:
                if not visit[node]:
                    q.put(node)

        if q.empty():
            break
        else:
            rad += 1
            endpoint = q.queue.copy()

    return rad, endpoint

if __name__ == "__main__":
    n = 5
    es = [[1, 2], [2, 3], [3, 4], [4, 5]]
    print(solution(n, es))