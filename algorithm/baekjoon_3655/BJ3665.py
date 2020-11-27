
def solution(n, nodes, m, changes):
    ans, q = [], []

    graph = [[] for i in range(n+1)]
    topology = [0 for i in range(n+1)]

    # 주어진 우선순위를 기반으로 그래프 그리기
    for i in range(n):
        for j in range(i+1, n):
            graph[nodes[i]].append(nodes[j])

    # 변경된 우선 순위 반영하기
    for i in range(m):
        node1, node2 = changes[i]
        if node2 in graph[node1]:
            node1, node2 = node2, node1
        graph[node1].append(node2)
        graph[node2].remove(node1)

    # 해당 노드를 선택하기 위해 필요한 노드의 개수를 배열에 저장
    for i in range(1, n+1):
        for j in range(len(graph[i])):
            topology[graph[i][j]] += 1

    # 현재 선택할 수 있는 노드를 큐에 넣기
    for i in range(1, n+1):
        if topology[i] == 0:
            q.append(i)

    while q:
        top = q[0]
        q.pop(0)
        ans.append(top)

        target = graph[top]
        # 선택된 노드가 가리키는 다른 노드의 숫자를 줄인다
        for t in target:
            topology[t] -= 1
            # 해당 노드를 선택할 수 있으면 선택
            if topology[t] == 0:
                q.append(t)

    # ans의 길이가 n보다 작다면 사이클이 존재하는 경우
    # 우선순위를 정할 수 없음!
    if len(ans) < n:
        return False
    else:
        return ans

if __name__ == "__main__":
    whole_cnt = int(input())
    for _ in range(whole_cnt):
        node = int(input())
        graph = [[] for i in range(node+1)]
        node_list = list(map(int, input().split(' ')))

        case = int(input())
        change = []
        for i in range(case):
            change.append(list(map(int, input().split(' '))))

        ans = solution(node, node_list, case, change)
        if ans is False:
            print("IMPOSSIBLE")
        else:
            tmp = ""
            for i in range(node):
                tmp += str(ans[i]) + " "
            print(tmp)
